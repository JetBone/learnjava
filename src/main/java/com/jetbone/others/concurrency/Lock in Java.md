# Lock in Java

锁（lock）是 java 实现 synchronization 的核心机制，当然 lock 的实现要比 synchronized 复杂的多，但是根本上，lock 的实现也是基于 synchronized 实现的。

从 java 5 开始，java 就提供了 java.util.concurrent.locks 包，这个包下有各种各样的 lock 的实现，所以我们没必要自己再去实现锁了，只需要知道如何使用即可。

下面简单描述一下 lock 的演变和遇到的问题，方便之后对 lock 进行更深入的了解

## A simple Lock

首先来看一个简单的例子

```java
public class Counter{

  private int count = 0;

  public int inc(){
    synchronized(this){
      return ++count;
    }
  }
}
```

上面是个非常简单的 synchronized 的使用，将自己作为 monitor，保证了线程安全。

不过我们可以使用 Lock 类来替代 synchronized

```java
public class Counter{

  private Lock lock = new Lock();
  private int count = 0;

  public int inc(){
    lock.lock();
    int newCount = ++count;
    lock.unlock();
    return newCount;
  }
}
```

这里我们新建了一个 Lock 类，调用 lock() 和 unlock() 方法来进行加锁和解锁

下面是一个简单的 Lock 的实现

```java
public class Lock{

  private boolean isLocked = false;

  public synchronized void lock()
  throws InterruptedException{
    while(isLocked){
      wait();
    }
    isLocked = true;
  }

  public synchronized void unlock(){
    isLocked = false;
    notify();
  }
}
```

可以看到虽然我们在外层抛弃了 synchronized 修饰符，实际上在 Lock 类内部本质还是使用 synchronized 关键字来实现锁。

被 while 包起来的被成为自旋锁（spin lock），可以防止线程意外唤醒。通过 spin lock, wait() 和 notify() 三个方式的组合，完成了一个 Lock 类的全部功能。

##  Lock Reentrance

在 java 中，被 synchronized 包含的代码块是可以反复进入的，可以看下面的代码

```java
public class Reentrant{

  public synchronized outer(){
    inner();
  }

  public synchronized inner(){
    //do something
  }
}
```

注意上述代码，outer() 方法内部调用了 inner() 方法，两个方法都使用 synchronized 关键字进行了修饰，锁包含的锁也是相同的。

如果一个线程调用了 outer()，就会进入 inner() 方法，但是在进入 inner() 方法的时候，会检查是否拥有当前锁，此时会发现本事已经拥有了 inner() 的锁，不需要再次获取了，所以可以执行 inner() 方法。

当这个线程执行完 inner() 方法后，正常是要释放 inner() 方法的锁的，但是因为外部的 outer() 方法与 inner() 方法的锁一样，outer() 还没有结束，所以自然是不能释放锁的。

上述两个现象就是 lock reentrance，synchronized 关键字自己会处理这种问题，但是在自己实现 Lock 类的时候，一定要考虑到上述两个情况，否则前者造成获取自己已经获取到的锁，自己被自己 block，造成死锁，后者提前释放锁，必然会造成代码错误。

可以简单看一下下面的错误代码

```java
public class Reentrant2{

  Lock lock = new Lock();

  public outer(){
    lock.lock();
    inner();
    lock.unlock();
  }

  public synchronized inner(){
    lock.lock();
    //do something
    lock.unlock();
  }
}
```

上述是使用自己定制的 Lock 的类来进行加锁和解锁

下面是 Lock 类的部分实现

```java
public class Lock{

  boolean isLocked = false;

  public synchronized void lock()
  throws InterruptedException{
    while(isLocked){
      wait();
    }
    isLocked = true;
  }

  ...
}
```

可以很明显的发现，如果在 outer() 里调用 inner()，会调用两次 lock.lock() 方法，第二次调用的时候，isLocked 判断为 true，则进入 wait()，那么相当于自己被自己 block，产生死锁。

为了解决 lock reentrance 问题，我们简单的修改一下 Lock 类

```java
public class Lock{

  boolean isLocked = false;
  Thread  lockedBy = null;
  int     lockedCount = 0;

  public synchronized void lock()
  throws InterruptedException{
    Thread callingThread = Thread.currentThread();
    // 这里除了判断 isLocked，还要判断锁是否已经被当前线程获取
    while(isLocked && lockedBy != callingThread){
      wait();
    }
    isLocked = true;
    // 一旦加锁成功，一定要将锁的次数增加，之后解锁的时候，如果次数大于1，则说明不能真的释放锁
    lockedCount++;
    lockedBy = callingThread;
  }


  public synchronized void unlock(){
    // 解锁的时候要判断是否是当前获得锁的线程进行操作，然后要对加锁的数量进行减1
    if(Thread.curentThread() == this.lockedBy){
      lockedCount--;
      // 只有加锁的次数为0，则说明真的需要释放锁了。
      if(lockedCount == 0){
        isLocked = false;
        notify();
      }
    }
  }

  ...
}
```

从上面的代码可以看出，在 spin lock 中多了一个判断，同时加锁成功后，又一个count++的操作，解锁过程有着类似的相反操作，解决了 reentrance 问题。

## Lock Fairness

上述代码中并没有考虑到线程获得锁的公平性，这一部分可以参考之前写的 Starvation and Fairness 文章和相关代码。

## Calling unlock() From a finally-clause

在实际上代码当中，我们要注意把 unlock() 方法的调用写在 try-catch-finally 代码块中的 finally 中，确保产生异常后可以释放锁。

