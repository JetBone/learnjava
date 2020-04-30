# Nested Monitor Lockout

nested monitor lockout 有点像 deadlock ，但是略有区别，有点不好解释，直接上代码

```java
//lock implementation with nested monitor lockout problem

public class Lock{
  protected MonitorObject monitorObject = new MonitorObject();
  protected boolean isLocked = false;

  public void lock() throws InterruptedException{
    synchronized(this){
      while(isLocked){
        synchronized(this.monitorObject){
            this.monitorObject.wait();
        }
      }
      isLocked = true;
    }
  }

  public void unlock(){
    synchronized(this){
      this.isLocked = false;
      synchronized(this.monitorObject){
        this.monitorObject.notify();
      }
    }
  }
}
```

如上 lock() 方法所示，代码最外部的 synchronized 将自己作为 monitor，内部又调用 synchronized 使用monitorObject 作为 monitor，但是内部只释放了 monitorObject 的锁，没有释放外部的锁，也就是说当前线程处于 waiting 状态，但是永远也没有其它线程来唤醒它，因为其它线程无法进入 unlock() 方法。同样 lock() 方法也被 block。

## A More Realistic Example

上面只是一个非常简答的例子，很容易一下看出问题，下面举一个比较容易犯错真实例子

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
            // 释放了 queueObject 的锁，但是没有释放外部的 this
            queueObject.wait();
          }catch(InterruptedException e){
            waitingThreads.remove(queueObject);
            throw e;
          }
        }
      }
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
      QueueObject queueObject = waitingThreads.get(0);
      synchronized(queueObject){
        queueObject.notify();
      }
    }
  }
}

public class QueueObject {}
```

在 lock() 方法中，synchronized 外层使用 this，内存是 queueObject，很显然内部调用 wait() 方法，就会出现出现 lockout 问题

## Deadlock and Lockout

二者结果上是基本相同的，但是产生的条件截然相反，Deadlock 是因为多个线程获取锁的顺序不同，而 Lockout 是多个线程获取锁的顺序相同。

前者的解决方式已经在之前的文章有提到，后者则需要注意写代码的方式。

