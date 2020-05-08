# Blocking Queues

Blocking queue 是一种 queue，就是当你从里面取数据的时候（dequeue），如果里面是空的，则会被 block，当你想往里面放数据（enqueue）的时候，如果里面满了，也会被 block。

Java 5 提供的 java.util.concurrent 包里有许多 Blocking queue 的实现，我们这里只简单的讲解一下 blocking queue 的基础理论。

## Blocking Queue Implementation

具体实现如下：

> 见同目录下 blockingqueue 包下的 BlockingQueue 类

```java
public class BlockingQueue {

  private List queue = new LinkedList();
  private int  limit = 10;

  public BlockingQueue(int limit){
    this.limit = limit;
  }


  public synchronized void enqueue(Object item)
  throws InterruptedException  {
    while(this.queue.size() == this.limit) {
      wait();
    }
    this.queue.add(item);
    if(this.queue.size() == 1) {
      notifyAll();
    }
  }


  public synchronized Object dequeue()
  throws InterruptedException{
    while(this.queue.size() == 0){
      wait();
    }
    if(this.queue.size() == this.limit){
      notifyAll();
    }

    return this.queue.remove(0);
  }

}
```

这里一定要注意 enqueue() 和 dequeue() 方法里的 notifyAll() 方法的使用，如果不添加 notifyAll() 方法，可能会导致一线线程 waiting forever。
