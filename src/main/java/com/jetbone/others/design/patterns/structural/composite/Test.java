package com.jetbone.others.design.patterns.structural.composite;


/**
 * Created by Chris on 2019/8/28
 */
public class Test {
    public static void main(String[] args) {

        // 创建目录
        CatalogComponent mainCatalog = new Dictionary("总目录",1);
        CatalogComponent javaCatalog = new Dictionary("Java文件目录",2);
        CatalogComponent pythonCatalog = new Dictionary("Python文件目录",2);

        // 创建文件
        CatalogComponent javaFile1 = new File("Java文件1", 10);
        CatalogComponent javaFile2 = new File("Java文件2", 20);
        CatalogComponent javaFile3 = new File("Java文件3", 30);
        CatalogComponent pythonFile1 = new File("pythonFile1", 10);
        CatalogComponent pythonFile2 = new File("pythonFile2", 20);
        CatalogComponent sqlFile = new File("sqlFile", 15);

        // 构建目录结构
        mainCatalog.add(javaCatalog);
        mainCatalog.add(pythonCatalog);

        // 这里可以很明显的看出，文件和目录本身是两个不同的东西
        // 但是这里可以当作两个是一种类型的东西进行处理
        // 这里即为组合模式的核心
        mainCatalog.add(sqlFile);

        javaCatalog.add(javaFile1);
        javaCatalog.add(javaFile2);
        javaCatalog.add(javaFile3);

        pythonCatalog.add(pythonFile1);
        pythonCatalog.add(pythonFile2);

        mainCatalog.print();

        // 这里使用目录文件的举例可能不太恰当
        // 组合模式主要是把许多相同的类组合成了一个树形结构的类
        // 并且对于外界来讲，我可以用调用一个被组合类的方式调用树形结构的类
        // 效果就是这个树形结构帮我们将树中所有的类都调用了相同的方法
        // 在这个例子当中Dictionary类就是树形结构类，File就是实际具体被调用的类
        // 我们所关注的，不是两个不同的类实现同一个接口，导致外部行为一样
        // 而是创建了一个树形结构的"工具类"，用来帮助将大量相同的实际类组合起来
        // 树形结构之所以要和被组合的类实现同一个接口，其实就是为了让外部行为看起来一致
        // 我们当然可以创建一个树形结构的类而不实现这个接口，但是外部行为就不一样了
        // 就像适配器模式一样，单有适配器而没有被适配者是不行的，这里也是，单有树形结构类而没有实际节点类也是不行的
        // 组合模式的关键点：
        // 1.将大量相同的类组合为一个类(工具人嗷)
        // 2.并且这一个类和被组合的单独一个类的外部行为看起来一样
    }
}
