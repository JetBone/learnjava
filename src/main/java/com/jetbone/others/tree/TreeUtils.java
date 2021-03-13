package com.jetbone.others.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Chris
 * @date 2021-03-11
 */
public class TreeUtils {

//    private List<Tree<T>> trees;
//    private Map<Long, Tree<T>> treeMap;
//    private Map<Long, List<Tree<T>>> pTreeMap;
//
//    private Trees(Map<Long, Tree<T>> treeMap, Map<Long, List<Tree<T>>> pTreeMap) {
//        this.treeMap = treeMap;
//        this.pTreeMap = pTreeMap;
//    }
//
//    public static <T> Trees<T> init(List<Tree<T>> trees) {
//        return new Trees<>(treeMap, pTreeMap);
//    }

    /**
     * 将传入的树列表转换为树形结构，并且排序
     * @param trees 树类列表
     * @param <T> 实现 TreeNode 的类
     * @return 最终树形结构
     */
    public static <T extends TreeNode<T>> List<T> buildTreeWithSort(List<T> trees) {
        return buildTree(trees, true);
    }

    /**
     * 将传入的树列表转换为树形结构，并且不排序
     * @param trees 树类列表
     * @param <T> 实现 TreeNode 的类
     * @return 最终树形结构
     */
    public static <T extends TreeNode<T>> List<T> buildTreeWithoutSort(List<T> trees) {
        return buildTree(trees, false);
    }

    /**
     * 将传入的树列表转换为树形结构，并且不排序
     * @param trees 树类列表
     * @param sort 是否需要排序
     * @param <T> 实现 TreeNode 的类
     * @return 最终树形结构
     */
    private static <T extends TreeNode<T>> List<T> buildTree(List<T> trees, boolean sort) {
        Map<Long, List<T>> pTreeMap = trees.stream()
                .collect(Collectors.groupingBy(t -> t.getPid() == null ? 0L : t.getPid()));
        long startTime = System.currentTimeMillis();

        List<T> pTree = pTreeMap.get(0L);

        List<T> result = build(pTree, pTreeMap);
        if (sort) {
            sort(result);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Duration: " + (endTime - startTime));

        return result;
    }

    private static <T extends TreeNode<T>> List<T> build(List<T> trees, Map<Long, List<T>> pTreeMap) {
        for (TreeNode<T> treeNode : trees) {
            if (pTreeMap.containsKey(treeNode.getId())) {
                List<T> subTrees = pTreeMap.get(treeNode.getId());
                treeNode.setTreeNodes(build(subTrees, pTreeMap));
            }
        }
        return trees;
    }

    private static <T extends TreeNode<T>> void sort(List<T> trees) {
        Collections.sort(trees);
        for (TreeNode<T> treeNode : trees) {
            if (treeNode.getTreeNodes() != null && !treeNode.getTreeNodes().isEmpty()) {
                sort(treeNode.getTreeNodes());
            }
        }
    }

    public static <T extends TreeNode<T>> List<T> buildFullPath(List<T> trees, Long... ids) {
        if (ids == null || ids.length == 0) {
            return Collections.emptyList();
        }
        Map<Long, T> treeMap = trees.stream().collect(Collectors.toMap(TreeNode::getId, t -> t));
        List<T> subTreeList = new ArrayList<>();
        for (Long id : ids) {
            T treeNode = buildFullPath(treeMap, id);
            if (treeNode != null) {
                subTreeList.add(treeNode);
            }
        }

        return subTreeList;
    }

    private static <T extends TreeNode<T>> T buildFullPath(Map<Long, T> treeMap, Long id) {
        if (id == null) {
            return null;
        }

        if (treeMap.containsKey(id)) {
            T treeNode = treeMap.get(id);
            return findFullPath(treeNode, treeMap);
        } else {
            return null;
        }

    }

    private static <T extends TreeNode<T>> T findFullPath(T treeNode, Map<Long, T> treeMap) {
        if (treeNode.getPid() == null || treeNode.getPid() == 0L) {
            return treeNode;
        }
        if (treeMap.containsKey(treeNode.getPid())) {
            T pTreeNode = treeMap.get(treeNode.getPid()).copyMySelf();
            if (pTreeNode.getTreeNodes() == null || pTreeNode.getTreeNodes().isEmpty()) {
                List<T> list = new ArrayList<>();
                list.add(treeNode);
                pTreeNode.setTreeNodes(list);
            } else {
                pTreeNode.getTreeNodes().add(treeNode);
            }
            return findFullPath(pTreeNode, treeMap);
        } else {
            return treeNode;
        }
    }


}
