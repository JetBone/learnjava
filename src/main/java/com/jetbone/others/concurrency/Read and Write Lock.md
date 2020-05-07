# Read / Write Lock

读写锁是一种更负责的 Lock 类的实现。

假设你有一部分资源被多线程调用，但是实际上，读取的频率远远大于写的频率，如果使用原来的 Lock，那么同时只能有一个线程进行读或者写的操作，效率将会特别低，但是实际上读操作是不需要加锁的，只有写的时候才需要加锁。

Java 5 提供的 java.util.concurrent 包内已经有了读写锁的实现，不过我们可以简单的了解一下理论

## Read / Write Lock Java Implementation

首先要明确一下读写锁需要实现的功能：

- 当没有线程已经获得了写锁，或者没有线程请求获取写锁，那么线程可以获取读锁。
- 当没有线程拥有写锁或者读锁，那么线程可以获取写锁。

通俗的解释一下上面两句话，如果一个线程想要读取资源，只要没有其它线程在对这个资源进行修改（写操作），那么不管来多少个线程，都是可以读的，可以并行操作。

如果一个线程想要修改资源（写操作），那么必须等待其它所有对这个资源读和写的操作全部停止，自己才能进行写操作，并且在自己进行写操作的时候，其它线程将被block，直到自己释放锁。

根据上述的描述，我们有如下实现：

> 见同目录下 readwritelock 包下的 ReadWriteSimpleLock 类

```java
public class ReadWriteLock{

  private int readers       = 0;
  private int writers       = 0;
  private int writeRequests = 0;

  public synchronized void lockRead() throws InterruptedException{
    while(writers > 0 || writeRequests > 0){
      wait();
    }
    readers++;
  }

  public synchronized void unlockRead(){
    readers--;
    notifyAll();
  }

  public synchronized void lockWrite() throws InterruptedException{
    writeRequests++;

    while(readers > 0 || writers > 0){
      wait();
    }
    writeRequests--;
    writers++;
  }

  public synchronized void unlockWrite() throws InterruptedException{
    writers--;
    notifyAll();
  }
}
```

ReadWriteLock 类有四个方法，分别是对 read 加锁和解锁，对 write 加锁和解锁

这里需要注意解锁方法里使用的都是 notifyAll() 方法，如果使用 notify() 方法，如果唤醒的是一个 read 线程，但是有另外一个 write 线程之前进来修改了 writeRequests，那么这个被唤醒的 read 线程仍然会进入 waiting，但是由于没有唤醒其它线程，那么此时此刻，将没有任何一个线程执行下去。

## Read / Write Lock Reentrance

在有了简单的读写锁实现后，需要考虑的是读写锁的 reentrance 问题，可以简单构想一个场景：

线程 A 获得了读锁，此时线程 B 来获取 写锁，但是发现线程 A 正在读，所以等待线程 A 释放读锁，如果线程 A 想重复获取读锁，发现有线程正在等待获取写锁，就会被 block，那么就会死锁。

## Read Reentrance

在拥有读锁的前提下获取读锁，及 read reentrance，我们需要实现一下功能：

- 当一个线程已经获取读锁的时候，可以继续获取读锁（即使有写请求）

有关代码实现，如下：

> 见同目录下 readwritelock 包下的 ReadReentranceLock 类

```java
public class ReadWriteLock{

  private Map<Thread, Integer> readingThreads =
      new HashMap<Thread, Integer>();

  private int writers        = 0;
  private int writeRequests  = 0;

  public synchronized void lockRead() throws InterruptedException{
    Thread callingThread = Thread.currentThread();
    while(! canGrantReadAccess(callingThread)){
      wait();                                                                   
    }

    readingThreads.put(callingThread,
       (getAccessCount(callingThread) + 1));
  }


  public synchronized void unlockRead(){
    Thread callingThread = Thread.currentThread();
    int accessCount = getAccessCount(callingThread);
    if(accessCount == 1){ readingThreads.remove(callingThread); }
    else { readingThreads.put(callingThread, (accessCount -1)); }
    notifyAll();
  }


  private boolean canGrantReadAccess(Thread callingThread){
    if(writers > 0)            return false;
    if(isReader(callingThread) return true;
    if(writeRequests > 0)      return false;
    return true;
  }

  private int getReadAccessCount(Thread callingThread){
    Integer accessCount = readingThreads.get(callingThread);
    if(accessCount == null) return 0;
    return accessCount.intValue();
  }

  private boolean isReader(Thread callingThread){
    return readingThreads.get(callingThread) != null;
  }

}
```

## Write Reentrance

在拥有写锁的前提下获取写锁，及 write reentrance， 需要实现如下功能：

- 如果当前线程已经获取了写锁，可以继续获取写锁

> 见同目录下 readwritelock 包下的 WriteReentranceLock 类

```java
public class ReadWriteLock{

    private Map<Thread, Integer> readingThreads =
        new HashMap<Thread, Integer>();

    private int writeAccesses    = 0;
    private int writeRequests    = 0;
    private Thread writingThread = null;

  public synchronized void lockWrite() throws InterruptedException{
    writeRequests++;
    Thread callingThread = Thread.currentThread();
    while(! canGrantWriteAccess(callingThread)){
      wait();
    }
    writeRequests--;
    writeAccesses++;
    writingThread = callingThread;
  }

  public synchronized void unlockWrite() throws InterruptedException{
    writeAccesses--;
    if(writeAccesses == 0){
      writingThread = null;
    }
    notifyAll();
  }

  private boolean canGrantWriteAccess(Thread callingThread){
    if(hasReaders())             return false;
    if(writingThread == null)    return true;
    if(!isWriter(callingThread)) return false;
    return true;
  }

  private boolean hasReaders(){
    return readingThreads.size() > 0;
  }

  private boolean isWriter(Thread callingThread){
    return writingThread == callingThread;
  }
}
```

## Read to Write Reentrance

在拥有读锁的前提下获取写锁，及 read to write reentrance, 需要实现如下功能：

- 只有当前只有一个线程在读，并且这个线程就是自己的时候，才能获取写锁

> 见同目录下 readwritelock 包下的 ReadWriteReentrance 类

```java
public class ReadWriteLock{

    private Map<Thread, Integer> readingThreads =
        new HashMap<Thread, Integer>();

    private int writeAccesses    = 0;
    private int writeRequests    = 0;
    private Thread writingThread = null;

  public synchronized void lockWrite() throws InterruptedException{
    writeRequests++;
    Thread callingThread = Thread.currentThread();
    while(! canGrantWriteAccess(callingThread)){
      wait();
    }
    writeRequests--;
    writeAccesses++;
    writingThread = callingThread;
  }

  public synchronized void unlockWrite() throws InterruptedException{
    writeAccesses--;
    if(writeAccesses == 0){
      writingThread = null;
    }
    notifyAll();
  }

  private boolean canGrantWriteAccess(Thread callingThread){
    if(isOnlyReader(callingThread))    return true;
    if(hasReaders())                   return false;
    if(writingThread == null)          return true;
    if(!isWriter(callingThread))       return false;
    return true;
  }

  private boolean hasReaders(){
    return readingThreads.size() > 0;
  }

  private boolean isWriter(Thread callingThread){
    return writingThread == callingThread;
  }

  private boolean isOnlyReader(Thread thread){
      return readers == 1 && readingThreads.get(callingThread) != null;
      }
  
}
```

## Write to Read Reentrance

在拥有写锁的前提下获取读锁，及 write to read reentrance，需要实现如下功能：

- 如果当前线程已经获取了写锁，那么也可以获取读锁

> 见同目录下 readwritelock 包下的 WriteReadReentrance 类

```java
public class ReadWriteLock{

    private boolean canGrantReadAccess(Thread callingThread){
      if(isWriter(callingThread)) return true;
      if(writingThread != null)   return false;
      if(isReader(callingThread)  return true;
      if(writeRequests > 0)       return false;
      return true;
    }

}
```

## Fully Reentrant ReadWriteLock

将上面四个情况结合起来

> 见同目录下 readwritelock 包下的 FinalReadWirteLock 类

```java
public class ReadWriteLock{

  private Map<Thread, Integer> readingThreads =
       new HashMap<Thread, Integer>();

   private int writeAccesses    = 0;
   private int writeRequests    = 0;
   private Thread writingThread = null;


  public synchronized void lockRead() throws InterruptedException{
    Thread callingThread = Thread.currentThread();
    while(! canGrantReadAccess(callingThread)){
      wait();
    }

    readingThreads.put(callingThread,
     (getReadAccessCount(callingThread) + 1));
  }

  private boolean canGrantReadAccess(Thread callingThread){
    if( isWriter(callingThread) ) return true;
    if( hasWriter()             ) return false;
    if( isReader(callingThread) ) return true;
    if( hasWriteRequests()      ) return false;
    return true;
  }


  public synchronized void unlockRead(){
    Thread callingThread = Thread.currentThread();
    if(!isReader(callingThread)){
      throw new IllegalMonitorStateException("Calling Thread does not" +
        " hold a read lock on this ReadWriteLock");
    }
    int accessCount = getReadAccessCount(callingThread);
    if(accessCount == 1){ readingThreads.remove(callingThread); }
    else { readingThreads.put(callingThread, (accessCount -1)); }
    notifyAll();
  }

  public synchronized void lockWrite() throws InterruptedException{
    writeRequests++;
    Thread callingThread = Thread.currentThread();
    while(! canGrantWriteAccess(callingThread)){
      wait();
    }
    writeRequests--;
    writeAccesses++;
    writingThread = callingThread;
  }

  public synchronized void unlockWrite() throws InterruptedException{
    if(!isWriter(Thread.currentThread()){
      throw new IllegalMonitorStateException("Calling Thread does not" +
        " hold the write lock on this ReadWriteLock");
    }
    writeAccesses--;
    if(writeAccesses == 0){
      writingThread = null;
    }
    notifyAll();
  }

  private boolean canGrantWriteAccess(Thread callingThread){
    if(isOnlyReader(callingThread))    return true;
    if(hasReaders())                   return false;
    if(writingThread == null)          return true;
    if(!isWriter(callingThread))       return false;
    return true;
  }


  private int getReadAccessCount(Thread callingThread){
    Integer accessCount = readingThreads.get(callingThread);
    if(accessCount == null) return 0;
    return accessCount.intValue();
  }


  private boolean hasReaders(){
    return readingThreads.size() > 0;
  }

  private boolean isReader(Thread callingThread){
    return readingThreads.get(callingThread) != null;
  }

  private boolean isOnlyReader(Thread callingThread){
    return readingThreads.size() == 1 &&
           readingThreads.get(callingThread) != null;
  }

  private boolean hasWriter(){
    return writingThread != null;
  }

  private boolean isWriter(Thread callingThread){
    return writingThread == callingThread;
  }

  private boolean hasWriteRequests(){
      return this.writeRequests > 0;
  }

}
```

当然，这并不是读写锁的全部，使用 notifyAll() 方法没有解决锁的 starvation and fairness 问题，所以还有很大的改进空间，不过这里不再做阐述。

## Calling unlock() From a finally-clause

使用自定义的 lock() 方法需要使用 try-catch 代码块进行异常捕获，并且在 finally 代码块中调用 unlock() 方法。
