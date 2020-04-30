# Slipped Condition

slipped condition 是指当一个线程依据一个判断条件执行之后的代码，但是这个条件却被另外一个线程修改了，那么之前那个线程相当于在一个错误的条件下执行错误的代码

```java
public class Lock {

    private boolean isLocked = true;

    public void lock(){
      synchronized(this){
        while(isLocked){
          try{
            this.wait();
          } catch(InterruptedException e){
            //do nothing, keep waiting
          }
        }
      }

      synchronized(this){
        isLocked = true;
      }
    }

    public synchronized void unlock(){
      isLocked = false;
      this.notify();
    }

}
```

上面修改 isLocked 和 最一开始判断 isLocked 是处于两个 synchronized 代码块当中，假设第一个线程进来发现 isLocked 是 false，于是就走向下一个 synchronized 代码块。

但是此时此刻第一个线程释放了 this 锁，很有可能在它来不及进入第二个 synchronized 代码块去修改 isLocked 的时候，第二个线程进入了前一个 synchronized 代码块，那么此时判断 isLocked 也为 false，就会出问题。

所以我们需要保证整个检查和修改的动作是原子性（atomically）的，修改很简单，将 isLocked = true 移动到前一个 synchronized 代码块当中。

```java
public class Lock {

    private boolean isLocked = true;

    public void lock(){
      synchronized(this){
        while(isLocked){
          try{
            this.wait();
          } catch(InterruptedException e){
            //do nothing, keep waiting
          }
        }
        isLocked = true;
      }
    }

    public synchronized void unlock(){
      isLocked = false;
      this.notify();
    }

}
```

## A More Realistic Example

上述只是一个非常简单的例子，很容易发现的错误，下面举一个比较有可能发生的例子

```java
//Fair Lock implementation with nested monitor lockout problem

public class FairLock {
  private boolean           isLocked       = false;
  private Thread            lockingThread  = null;
  private List<QueueObject> waitingThreads =
            new ArrayList<QueueObject>();

  public void lock() throws InterruptedException{
    QueueObject queueObject = new QueueObject();

    synchronized(this){
      waitingThreads.add(queueObject);

      while(isLocked || waitingThreads.get(0) != queueObject){

        synchronized(queueObject){
          try{
            // 首先这里出现了 lockout 错误
            queueObject.wait();
          }catch(InterruptedException e){
            waitingThreads.remove(queueObject);
            throw e;
          }
        }
      }
      // 这里将代码移除了 while 判断，
      waitingThreads.remove(queueObject);
      isLocked = true;
      lockingThread = Thread.currentThread();
    }
  }

  public synchronized void unlock(){
    if(this.lockingThread != Thread.currentThread()){
      throw new IllegalMonitorStateException(
        "Calling thread has not locked this lock");
    }
    isLocked      = false;
    lockingThread = null;
    if(waitingThreads.size() > 0){
      QueueObject queueObject = waitingThread.get(0);
      synchronized(queueObject){
        queueObject.notify();
      }
    }
  }
}

public class QueueObject {}
```

上面代码是之前产生 lockout 错误的代码，正常情况下，我们会为了解决这个问题，将 wait 操作移出 synchronized(this) 代码块，所以有如下代码

```java
//Fair Lock implementation with slipped conditions problem

public class FairLock {
  private boolean           isLocked       = false;
  private Thread            lockingThread  = null;
  private List<QueueObject> waitingThreads =
            new ArrayList<QueueObject>();

  public void lock() throws InterruptedException{
    QueueObject queueObject = new QueueObject();

    // 这里没有问题，进入队列需要原子性操作
    synchronized(this){
      waitingThreads.add(queueObject);
    }

    boolean mustWait = true;
    while(mustWait){
      
      synchronized(this){
        mustWait = isLocked || waitingThreads.get(0) != queueObject;
      }
      synchronized(queueObject){
        if(mustWait){
          try{
            queueObject.wait();
          }catch(InterruptedException e){
            waitingThreads.remove(queueObject);
            throw e;
          }
        }
      }
    }

    synchronized(this){
      waitingThreads.remove(queueObject);
      isLocked = true;
      lockingThread = Thread.currentThread();
    }
  }
}
```
