package com.jetbone.others.tree;

import cn.hutool.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris
 * @date 2021-03-11
 */
public class Test {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        TreeNodeDemo demo1 = new TreeNodeDemo(1L, 1, 0L, "1");
        TreeNodeDemo demo2 = new TreeNodeDemo(2L, 2, 0L, "2");
        TreeNodeDemo demo3 = new TreeNodeDemo(3L, 3, 0L, "3");
        TreeNodeDemo demo4 = new TreeNodeDemo(4L, 4, 0L, "4");
        TreeNodeDemo demo5 = new TreeNodeDemo(5L, 5, 1L, "1-1");
        TreeNodeDemo demo6 = new TreeNodeDemo(6L, 6, 2L, "2-1");
        TreeNodeDemo demo7 = new TreeNodeDemo(7L, 7, 1L, "1-2");
        TreeNodeDemo demo8 = new TreeNodeDemo(8L, 8, 2L, "2-2");
        TreeNodeDemo demo9 = new TreeNodeDemo(9L, 9, 3L, "3-1");
        TreeNodeDemo demo10 = new TreeNodeDemo(10L, 10, 3L, "3-2");
        TreeNodeDemo demo11 = new TreeNodeDemo(11L, 11, 4L, "4-1");
        TreeNodeDemo demo12 = new TreeNodeDemo(12L, 12, 5L, "1-1-1");
        TreeNodeDemo demo13 = new TreeNodeDemo(13L, 13, 6L, "2-1-1");
        TreeNodeDemo demo14 = new TreeNodeDemo(14L, 14, 6L, "2-1-2");
        TreeNodeDemo demo15 = new TreeNodeDemo(15L, 15, 6L, "2-1-3");
        TreeNodeDemo demo16 = new TreeNodeDemo(16L, 16, 7L, "1-2-1");
        TreeNodeDemo demo17 = new TreeNodeDemo(17L, 17, 9L, "3-1-1");
        TreeNodeDemo demo18 = new TreeNodeDemo(18L, 18, 10L, "3-2-1");
        TreeNodeDemo demo19 = new TreeNodeDemo(19L, 19, 9L, "3-1-2");
        TreeNodeDemo demo20 = new TreeNodeDemo(20L, 20, 7L, "1-2-2");
        TreeNodeDemo demo21 = new TreeNodeDemo(21L, 21, 6L, "2-1-4");
        TreeNodeDemo demo22 = new TreeNodeDemo(22L, 22, 6L, "2-1-5");
        TreeNodeDemo demo23 = new TreeNodeDemo(23L, 23, 5L, "1-1-2");
        TreeNodeDemo demo24 = new TreeNodeDemo(24L, 24, 1L, "1-2");
        TreeNodeDemo demo25 = new TreeNodeDemo(25L, 25, 17L, "3-1-1-1");
        TreeNodeDemo demo26 = new TreeNodeDemo(26L, 26, 19L, "3-1-2-1");
        TreeNodeDemo demo27 = new TreeNodeDemo(27L, 27, 20L, "1-2-2-1");
        List<TreeNodeDemo> trees = new ArrayList<>();
        trees.add(demo3);
        trees.add(demo1);
        trees.add(demo2);
        trees.add(demo6);
        trees.add(demo11);
        trees.add(demo7);
        trees.add(demo8);
        trees.add(demo9);
        trees.add(demo10);
        trees.add(demo12);
        trees.add(demo4);
        trees.add(demo5);
        trees.add(demo13);
        trees.add(demo14);
        trees.add(demo15);
        trees.add(demo17);
        trees.add(demo16);
        trees.add(demo19);
        trees.add(demo20);
        trees.add(demo22);
        trees.add(demo23);
        trees.add(demo24);
        trees.add(demo25);
        trees.add(demo26);
        trees.add(demo21);
        trees.add(demo18);
        trees.add(demo27);

//        List<TreeNodeDemo> trees = new ArrayList<>();
//        for (int i = 1; i <= 10000000; i++) {
//            trees.add(new TreeNodeDemo((long) i, (int) (Math.random()*20+1), (long) (Math.random()*1000), "name-" + i));
//        }

//        List<TreeNodeDemo> result = TreeUtils.buildTreeWithSort(trees);
        List<TreeNodeDemo> result = TreeUtils.buildSubTree(trees, 15L, 8L, 25L);

        JSONArray jsonArray = new JSONArray(result);
        System.out.println(jsonArray.toString());

        long endTime = System.currentTimeMillis();

        System.out.println("################ END : " + (endTime - startTime) + " ################");

    }
}
