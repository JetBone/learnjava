# Non-blocking Algorithms

Non-blocking 算法允许多线程访问共享变量的时候不被 block。

## Blocking Concurrency Algorithms

一个 Blocking Algorithm 首先是个算法，同时：

- 执行一个被线程请求的操作，或者
- 阻塞（block）这个操作，直到该操作可以安全的被执行

之前讲到的所有东西都是 Blocking Concurrency Algorithm，比较典型的是 Blocking Queue，当里面是空的时候，想要获取数据的操作将会被阻塞，当里面是满的时候，想要存数据的操作将会被阻塞。

## Non-blocking Concurrency Algorithms

一个 Non-blocking concurrency algorithm 首先是一种算法，同时：

- 执行一个被线程请求的操作，或者
- 通知请求操作的线程，当前这个这个操作无法被执行。

Java 同样也包含许多 non-blocking 数据结构，AtomicBoolean, AtomicInteger, AtomicLong 和 AtomicReference 都是 non-blocking 的数据结构。

## Non-blocking vs Blocking Algorithms

二者之间的主要区别其实就在第二点操作，同样是执行一个线程操作，如果发现当前操作不能执行，blocking algorithm 会阻塞当前线程，直到可以执行，而 non-blocking algorithm 会立刻告诉线程当前操作不能执行。

## Non-blocking Concurrent Data Structures

在多线程的场景下，线程之间一般都会用一种数据结构来沟通，这些数据结构可以是一些非常简单的数据类型，比如 queues，maps，stacks 等等。为了保证在多线程场景下正确使用，这些数据结构必须通过某种 concurrent algorithm 来保证。被这些算法支持的数据结构就称之为 concurrent data structure。

如果一个 concurrent data structure 使用的是 blocking algorithm，那么这个数据结构就称之为 blocking, concurrent data structure。

如果一个 concurrent data structure 使用的是 non-blocking algorithm，那么这个数据结构就称之为 non-blocking, concurrent data structure。

有关 blocking algorithm 已经讲了很多了，其中里面使用的很多数据结构比如 BlockingQueue 其实就算是一种 blocking, concurrent data structure，所以这里不再描述。下面主要讲解 non-blocking, concurrent data structure。

## Volatile Variables

Java volatile variables 是一种直接从 main memory 读写的变量，之前文章有做描述。

Volatile variables 本事是 non-blocking 的，对这个变量的写操作是原子性（atomic）的，无法被中断。但是，read-update-write 操作组合起来不是原子性的，并且在多线程场景下会造成 race condition

```java
volatile myVar = 0;

...
int temp = myVar;
temp++;
myVar = temp;
``` 

首先 volatile 变量 myVar 从 main memory 中读取出来，并赋予给一个变量 temp，然后这个 temp 变量加 1，然后将 temp 变量在赋予给 myVar 变量，意味着这个值也会直接写入到 main memory 中。

如果有两个变量同时执行这三个操作，那么最终结果可能不是两个线程各 +1 变成 2，而是两个线程都向内存写入结果 1，结果就是 1。

事实上，我们并不会这样写代码，更常见的方式是：

```java
myVal++;
```

当执行的时候，
