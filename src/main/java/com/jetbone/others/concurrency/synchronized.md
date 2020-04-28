# Java Synchronized Block

## 介绍

java synchronized 关键字是用来保证线程安全的，凡是被 synchronized 修改时的代码块，同一时间，只能由一个线程访问

## 不足以及代替品

### 读写性能

如果有两个线程只想读取一块被 synchronized 修饰后的代码，那么同一时间也只能有个一个线程能读取，对于大量读操作来讲很不友好，降低性能

我们可以使用读写锁 Read/Write Lock，Java已经提供了 ReadWriteLock 类

### 针对性控制

如果我们允许很多线程访问一个资源，但是唯独针对某一个线程禁止访问，synchronized 也是无法办到的

我们可以使用Semaphore去实现这个功能，java已经提供了 Semaphore 类供我们使用

## 性能

虽然java本身针对 synchronized 已经进行了很大的优化，但是如果对性能要求很高的话，那么 synchronized 还是有不少性能损耗的。

针对这方面，在加 synchronized 关键字前，一定要确保代码块足够小，只包含自己需要加锁的代码。

## Synchronized Block Reentrance

当一个线程进入一个被 synchronized 修饰的代码块的时候，就代表着这个线程拥有了这个代码块的监视类的锁，然后其它线程获取不到这个锁，自然就无法进入这个代码块了。

如果在这个代码块里，执行某个语句再次进入了被同一个锁控制的代码块，那么由于当前线程已经获取到了这个锁，所以自然不会被block，也不会需要重复获取锁，直接进入代码块进行执行，这就是 reentrance

正常情况下这都是可行的，但是如果是自己来实现一个锁的功能，这部分需要着重考虑，否则会产生自己等待释放已经被自己获取的锁，单线程死锁问题

## Synchronized Blocks in Cluster Setups

需要注意的是，Synchronized 只会 block 同一个 JVM 中的线程，所以在多集群的情况下，需要使用其它工具
