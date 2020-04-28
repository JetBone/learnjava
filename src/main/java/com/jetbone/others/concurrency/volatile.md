# Java Volatile Keyword

## 介绍

volatile 关键字是用来修饰变量的，凡是被 volatile 修饰的变量，java 会保证始终保存在 main memory 里（being stored in main memory）

就是说，所有要读取这个关键字的线程，都会从 main memory（就是内存条）中直接读取，而不是从 cpu cache 中读取（具体请自行了解内存模型）

当然 volatile 关键字不仅仅有这一个功能，具体分析见之后的 section

## Variable Visibility Problems

这个问题就是上述所说的直接从 main memory 中读取数据，避免从 cpu cache中读取数据可以有效的解决之前写的 race condition 问题，这里不做过多描述

## The Java volatile Visibility Guarantee

volatile 关键字是 java 用来解决变量可视的问题的。

为了保证变量的改动会对所有线程即使可见，一个被 volatile 修饰的变量，一旦被一个线程修改，那么改动会立刻更新到 main memory 当中。

当然其它线程会直接从 main memory 当中读取变量的值，以此来实现变量可见。

当然还是有局限性存在的，假设，一个线程T1写入一个变量counter，另外一个线程T2读取counter，那么使用volatile修饰这个counter变量已经足够完成任务了

但是如果两个线程 T1 和 T2 都想写 counter，那么单独靠 volatile 关键字是完全不够的。

### Full volatile Visibility Guarantee

事实上，volatile 所保证的变量可见远不止自己所描述的那么简单，实际操作过程当中，有如下操作结果：

- 如果 Thread A 写入一个 volatile 变量，紧接着 Thread B 线程立刻读取这个变量。那么在 A 写入 volatile 变量前的所有对于 A 可见的变量（就是 A 自己包含的各种变量），都将随同 volatile 变量一起写入 main memory，也就是对 B 可见（当然 B 也要包含这些变量对吧，所以这里只是理论上的可见）
- 如果 Thread A 读取一个 volatile 变量，那么对于所有对 A 可见的变量（就是 A 自己包含的变量），会随着读取 volatile 变量，一同从 main memory 再重新读取一遍

volatile 不仅仅影响被自己修饰的变量，根据类的操作方式也会将自己的部分属性传染给周围变量，并且这个传染方式跟代码里变量写的顺序有关。
这应该是 JVM 的一种优化吧，可以这么理解：

- 反正都要把变量写入 main memory 写入了，不如把做这个操作前刚刚更新的所有变量一起更新好了，还没有更新的变量就不更新了。
- 反正都从 main memory 中读取变量了，不如把所有变量都读取一遍。

## Instruction Reordering Challenges

JVM 和 CPU 其实本身会自动调整代码中指令的执行顺序来提升性能，举个例子：

```
int a = 1;
int b = 1;

a++;
b++;
```

上述代码也许会被自动调整顺序

```
int a = 1;
a++;

int b = 1;
b++;
```

根据我们之前提到的，写入 volatile 变量也会导致其它变量一同写入，但不是全部，而是取决与代码顺序，所以如果这里 JVM 和 CPU 自己调整代码顺序，必然会对其造成影响。

## The Java volatile Happens-Before Guarantee

针对上述排序问题，volatile 有这么几个规定来解决问题：

- 对于发生在 volatile 写操作前面的其它变量读写操作，将不允许排序到 volatile 写操作的后面。但是对于一开始就在 volatile 写操作后面的读写操作，允许排序到前面。
- 对于发生在 volatile 读操作后面的其它变量读写操作，将不允许排序到 volatile 读操作的前面。但是对于一开始就在 volatile 读操作前面的读写操作，允许排序到后面。

## volatile is Not Always Enough

volatile 关键字并不能完全的解决问题。

对于一个写，多个读的情况下，volatile可以完美解决问题，但是对于多个写，如果一些线程写入的时候并没有依据main memory 最新的值来更新，那么就会出现问题。

另外，如果多个线程同时写，即使使用了 volatile 进行修饰变量，那么在非常非常短的时间段内，我们仍然无法避免资源竞争，如果两个线程同时读取到了相同的数值，进行相同的操作，再写会 main memory，那么 volatile 并不能解决任何问题。

## When is volatile Enough?

如上所述，当有两个线程同时进行读写一个变量的操作的时候，volatile 并不能解决问题，这个时候需要使用 synchronized 进行控制。

## 性能问题

直接从 main memory 中读写数据是非常消耗性能的，并且不允许排序有的时候也会造成一点点性能损失，所以非必须情况下，最好不要使用 volatile



