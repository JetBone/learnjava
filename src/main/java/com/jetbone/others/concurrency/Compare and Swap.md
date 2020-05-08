# Compare and Swap

Compare and swap 是一种并发里的算法，简单来讲，就是比较并替换，如果一个值符合预期值则替换为另外一个值。

## What Situations Compare And Swap is Intended to Support

在代码里最常见的场景是检查后执行（check then act），先检查一个值是否符合预期，然后修改再原值

```java
class MyLock {

    private boolean locked = false;

    public boolean lock() {
        if(!locked) {
            locked = true;
            return true;
        }
        return false;
    }
}
```

上述代码会产生很多的错误，在多线程场景下，lock 的判断和修改有可能同时被多个线程调用，必然会出问题，当然解决方式也很简单

```java
class MyLock {

    private boolean locked = false;

    public synchronized boolean lock() {
        if(!locked) {
            locked = true;
            return true;
        }
        return false;
    }
}
```

很简单，只要加上 synchronized 修饰符，整个操作就是原子性（atomic）的。

这样修改是典型的 compare and swap 例子，不过我们还有另外一种方式来优化这个操作

## Compare And Swap As Atomic Operation

现代的 CPU 实际上是内置 atomic compare and swap 操作的。

Java 5 在 java.util.concurrent.atomic 包内提供了一些类，可以让我们直接使用 CPU 内置的操作方式。

下面是一个 AtomicBoolean 类的例子

```java
public static class MyLock {
    private AtomicBoolean locked = new AtomicBoolean(false);

    public boolean lock() {
        return locked.compareAndSet(false, true);
    }

}
```

以前 boolean 属性的 locked 变量现在被声明为了 AtomicBoolean 类型，在 lock() 方法内部，调用 compareAndSet() 方法来完成之前的 compare and swap 操作。

compareAndSet() 方法接收两个参数，第一个参数用来判断，判断是否与原值相等，如果相等，则将原值修改为第二个参数。

使用 Java 5 提供的 atomic 类来完成 compare and swap 操作的好处就是简化了代码，并且使用 CPU 内置的操作方式，速度更快。
