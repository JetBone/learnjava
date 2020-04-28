# Starvation and Fairness

如果一个线程总是获取不到 CPU 的资源，因为 CPU 资源总是被其它线程占用，那么这种情况就是 starvation，这个线程将会 starved to death。

解决这个问题的方法被称为 fairness，所有线程将会公平的获取到 CPU 资源

## Cause of Starvation in Java 

造成 starvation 现象有如下三种情况：

1. 一个具有较高优先级的线程总是获取到 CPU 资源。
2. 由于总是有其它线程获取到资源，导致当前线程总是处于 block 的状态（非酋）
3. notify() 方法唤醒的线程一只不是自己（非酋）

第一个情况很好解释，如果有一个高优先级线程执行时间长，频率高，那么第优先级的线程可能将永远获取不到 CPU 资源。

第二个情况是当线程处于被唤醒的状态，与其它多个线程一同竞争一个锁的时候，由于 synchronized 代码块是不能保证锁的分配顺序（随机的），也即是说理论上有可能，这个锁总是被其它线程获取。

第三个情况是线程处于 waiting 状态，等待被唤醒。同样 notify() 方法也不能保证被唤醒的线程顺序（随机唤醒），所以理论上，这个线程可能永远处于 waiting 状态。

## Implementing Fairness in Java

我们无法 100% 确保公平性，但是我们可以提高公平性。

首先看一下正常的 synchronized 代码

```java
public class Synchronizer{

  public synchronized void doSynchronized(){
    //do a lot of work which takes a long time
  }

}
```

如果有多个线程执行 doSynchronized() 方法，会有发生 starvation 的可能性。

### Using Locks Instead of Synchronized Blocks

为了提供公平性，我们要始终自定义的 lock 来替代 synchronized 操作

```java
public class Synchronizer{
  Lock lock = new Lock();

  public void doSynchronized() throws InterruptedException{
    this.lock.lock();
      //critical section, do a lot of work which takes a long time
    this.lock.unlock();
  }

}
```

如上述代码所示，我们使用自己定义的 Lock 类，调用 lock 的 lock() 和 unlock() 方法实现和 synchronized 相同的机制，并且还能自定义线程获取锁的顺序。

具体实现如下

```java
public class Lock{
  private boolean isLocked      = false;
  private Thread  lockingThread = null;

  public synchronized void lock() throws InterruptedException{
    while(isLocked){
      wait();
    }
    isLocked      = true;
    lockingThread = Thread.currentThread();
  }

  public synchronized void unlock(){
    if(this.lockingThread != Thread.currentThread()){
      throw new IllegalMonitorStateException("Calling thread has not locked this lock");
    }
    isLocked      = false;
    lockingThread = null;
    notify();
  }
}
```

上述代码首先实现了自己的 lock，并且 lock 和 unlock 也完成了 wait 和 notify 操作，不过实际上，这并没有实现 fairness，因为调用 notify 的时候，仍然不知道哪一个线程将会获取到资源。所以我们需要在此基础上在进行改进。

### A Fair Lock

接下来，我们需要在 wait 和 notify 操作部分来修改，增加 fairness

```java
public class FairLock {
    private boolean           isLocked       = false;
    private Thread            lockingThread  = null;
    private List<QueueObject> waitingThreads =
            new ArrayList<QueueObject>();

  public void lock() throws InterruptedException{
    QueueObject queueObject           = new QueueObject();
    boolean     isLockedForThisThread = true;
    synchronized(this){
        waitingThreads.add(queueObject);
    }

    while(isLockedForThisThread){
      synchronized(this){
        isLockedForThisThread =
            isLocked || waitingThreads.get(0) != queueObject;
        if(!isLockedForThisThread){
          isLocked = true;
           waitingThreads.remove(queueObject);
           lockingThread = Thread.currentThread();
           return;
         }
      }
      try{
        queueObject.doWait();
      }catch(InterruptedException e){
        synchronized(this) { waitingThreads.remove(queueObject); }
        throw e;
      }
    }
  }

  public synchronized void unlock(){
    if(this.lockingThread != Thread.currentThread()){
      throw new IllegalMonitorStateException("Calling thread has not locked this lock");
    }
    isLocked      = false;
    lockingThread = null;
    if(waitingThreads.size() > 0){
      waitingThreads.get(0).doNotify();
    }
  }
}
```

```java
public class QueueObject {

  private boolean isNotified = false;

  public synchronized void doWait() throws InterruptedException {
    while(!isNotified){
        this.wait();
    }
    this.isNotified = false;
  }

  public synchronized void doNotify() {
    this.isNotified = true;
    this.notify();
  }

  public boolean equals(Object o) {
    return this == o;
  }
}
```

首先我们可以看到 lock() 方法已经不再被 synchronized 修饰，取而代之的是内部部分代码进行 synchronized。

其次这里我们为每个进入的线程都创建了单独的 QueueObject 实例，这样调用 notify() 只会唤醒一个进程。另外 unlock 方法每次只会唤醒队列中的第一个元素，保证了 fairness。

QueueObject 内部将 signal 保存到了本地，防止信号丢失。

最后的 doWait 方法使用 try-catch 包起来，防止出现异常。
