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

