# Thread Pools

当你需要多个线程在同一段时间内运行多个任务，那么线程池将会是很好的选择。

每一个启动线程的动作都会有部分性能损失，并且每个需要给每个线程分配栈内存。

对于上述方式，线程池提供了更优雅的方式来解决问题，真正需要执行的代码称之为 task，将 task 分配给线程池，之后线程池会分配一个空闲的线程来执行这个 task，不需要为每个 task 启动一个新的线程。task 被执行完成后，线程也不会被关闭，而是等待线程池分配新的任务。

tasks 会被插入到 blocking queue 当中，线程从 blocking queue 中来获取 task，其它没有获取到 task 的空闲线程将继续处于 waiting 状态。

Java 5 在 java.util.concurrent 包中提供了线程池的实现，所以我们没必要自己实现线程池，不过我们可以简单的了解一下线程池的基础理论。

接下来是一个非常简单的线程池的实现：

```java
public class ThreadPool {

    private BlockingQueue taskQueue = null;
    private List<PoolThread> threads = new ArrayList<PoolThread>();
    private boolean isStopped = false;

    public ThreadPool(int noOfThreads, int maxNoOfTasks){
        taskQueue = new BlockingQueue(maxNoOfTasks);

        for(int i=0; i<noOfThreads; i++){
            threads.add(new PoolThread(taskQueue));
        }
        for(PoolThread thread : threads){
            thread.start();
        }
    }

    public synchronized void  execute(Runnable task) throws Exception{
        if(this.isStopped) throw
            new IllegalStateException("ThreadPool is stopped");

        this.taskQueue.enqueue(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for(PoolThread thread : threads){
           thread.doStop();
        }
    }

}
```

```java
public class PoolThread extends Thread {

    private BlockingQueue taskQueue = null;
    private boolean       isStopped = false;

    public PoolThread(BlockingQueue queue){
        taskQueue = queue;
    }

    public void run(){
        while(!isStopped()){
            try{
                Runnable runnable = (Runnable) taskQueue.dequeue();
                runnable.run();
            } catch(Exception e){
                //log or otherwise report exception,
                //but keep pool thread alive.
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        this.interrupt(); //break pool thread out of dequeue() call.
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}
```

线程池主要包含两个部分，一部分是线程池类，一部分是线程池内的线程类。

两个类通过共享一个 queue 来完成 task 的提交和获取操作，线程池负责 enqueue，线程负责 dequeue。

当线程池调用了 stop() 方法之后，会遍历调用里面所有线程的 doStop() 方法，doStop() 方法里调用了 interrupt() 方法，这个方法会停止线程，即使线程处于 waiting 状态。 
