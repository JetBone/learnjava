package com.jetbone.app;

import com.jetbone.others.tree.TreeNode;
import com.jetbone.others.tree.TreeNodeDemo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author Chris
 * @date 2021-03-14
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public <T extends TreeNode<T>> void redisTest() {
        System.out.println("############ START ############");
//        TreeNodeDemo demo1 = new TreeNodeDemo(1L, 1, 0L, "1");
//        TreeNodeDemo demo2 = new TreeNodeDemo(2L, 2, 0L, "2");
//        TreeNodeDemo demo3 = new TreeNodeDemo(3L, 3, 0L, "3");
//        TreeNodeDemo demo4 = new TreeNodeDemo(4L, 4, 0L, "4");
//        List<TreeNodeDemo> trees = new ArrayList<>();
//        trees.add(demo3);
//        trees.add(demo1);
//        trees.add(demo2);
//        trees.add(demo4);
//        Map<Long, List<TreeNodeDemo>> result = trees.stream().collect(Collectors.groupingBy(TreeNodeDemo::getPid));
//        redisTemplate.opsForValue().set("TREE", result);
        Map<Long, List<TreeNodeDemo>> result =  getValues();

//        System.out.println(value);
        System.out.println("############ END ############");
    }

    private <T extends TreeNode<T>> Map<Long, List<T>> getValues() {
        Map<Long, List<T>> result = (Map<Long, List<T>>) redisTemplate.opsForValue().get("TREE");
        return result;
    }

}
