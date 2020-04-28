# Thread Signaling

线程信号（Thread Signaling）支持线程之间互相发送 signals。比如，线程 A 可以等待接收到线程 B 给它的信号后在开始运行。

## Signaling via Shared Object

Signaling 的一种简单实现方式是使用共享对象，将信号保存为一个共享对象里的一个变量。两个线程必须引用同一个对象，才能互相检测到信号。

```java
public class MySignal{

  protected boolean hasDataToProcess = false;

  public synchronized boolean hasDataToProcess(){
    return this.hasDataToProcess;
  }

  public synchronized void setHasDataToProcess(boolean hasData){
    this.hasDataToProcess = hasData;  
  }

}
```

## Busy Wait

假设线程 B 需要等待 A 发送信号给它，那么代码如下：

```java
protected MySignal sharedSignal = ...

...

while(!sharedSignal.hasDataToProcess()){
  //do nothing... busy waiting
}
```

这里 B 循环判断是否有接收到信号，如果没有，则一只保持循环空转状态。这种方式叫做 Busy Waiting，这里的 busy 是即使这个线程什么都没有做，但是也会占用CPU资源不断的循环代码。

## wait(), notify(), notifyAll()

如上所述，Busy Waiting 并不是一个非常好的例子，因为对性能优化不够好。

java 内部提供了三个方法实现了上述功能

具体使用就不做阐述了。。。

## Missed Signals

如果在调用 notify() 或 notifyAll() 方法的时候，没有任何线程处于 waiting 的状态，那么 signal 将会被丢失。正常来讲，线程会先调用 notify() 方法然后调用 wait() 方法，这看起来是没有问题的，但是如果没有线程被唤醒，那么所有 waiting 的线程将永远处于 waiting 状态。

为了避免这种情况的发生，可以将信号保存到一个变量当中，这样每次进行 wait 操作的时候都进行一次判断

```java
public class MyWaitNotify2{

  MonitorObject myMonitorObject = new MonitorObject();
  boolean wasSignalled = false;

  public void doWait(){
    synchronized(myMonitorObject){
      if(!wasSignalled){
        try{
          myMonitorObject.wait();
         } catch(InterruptedException e){...}
      }
      //clear signal and continue running.
      wasSignalled = false;
    }
  }

  public void doNotify(){
    synchronized(myMonitorObject){
      wasSignalled = true;
      myMonitorObject.notify();
    }
  }
}
```

## Spurious Wakeups

在一些不可预测的情况下，有些线程可能会在没有被其它线程调用 notify() 或者 notifyAll() 方法的时候被唤醒，这种情况下也会造成一些问题

```java
public class MyWaitNotify3{

  MonitorObject myMonitorObject = new MonitorObject();
  boolean wasSignalled = false;

  public void doWait(){
    synchronized(myMonitorObject){
      while(!wasSignalled){
        try{
          myMonitorObject.wait();
         } catch(InterruptedException e){...}
      }
      //clear signal and continue running.
      wasSignalled = false;
    }
  }

  public void doNotify(){
    synchronized(myMonitorObject){
      wasSignalled = true;
      myMonitorObject.notify();
    }
  }
}
```

## Don't call wait() on constant String's or global objects

不要使用 String 或者全局对象作为 monitor object

JVM 一般会将相同的 String 转换为同一个 对象，所以当你在两个对象里定义了两个相同的 String 比如 ""，实际上这两个 String 是同一个对象，具体请自行了解 JVM 内存模型。此时使用 "".wait() 或者 notify() 方法，可能会唤起其它线程，产生意想不到的情况。
