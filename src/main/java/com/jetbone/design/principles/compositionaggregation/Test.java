package com.jetbone.design.principles.compositionaggregation;

/**
 * Created by Chris on 2019-07-30 21:31.
 */
public class Test {
    public static void main(String[] args) {

        // 第一种简单方式，使用mysql添加数据
        // 如果想新增使用oracle方式，则必须修父类，新增方法
        // 违反了开闭原则，需要修改所有的子类
        Dao dao = new Dao();
        dao.addInfo();


        // Dao与Connection之间使用组合/聚合关系
        // 支持对数据库连接的扩展，并且无需求改
        NewDao newDao = new NewDao();

        // 根据需求注入制定的连接方式即可
        newDao.setConnection(new MysqlConnection());
        newDao.setConnection(new OracleConnection());

        newDao.addInfo();
    }
}
