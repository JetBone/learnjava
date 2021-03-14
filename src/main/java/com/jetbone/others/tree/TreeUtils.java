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
     * @param id 最开始构建的顶端ID
     * @param sort 是否需要排序
     * @param treeNodes 树类列表
     * @param <T> 实现 TreeNode 的类
     * @return 最终树形结构
     */
    public static <T extends TreeNode<T>> List<T> buildTree(Long id, boolean sort, List<T> treeNodes) {
        Map<Long, T> nodeIdMap = treeNodes.stream().collect(Collectors.toMap(TreeNode::getId, t -> t));
        Map<Long, List<T>> nodePidMap = treeNodes.stream()
                .collect(Collectors.groupingBy(t -> t.getPid() == null ? 0L : t.getPid()));
        return buildTree(id, sort, nodeIdMap, nodePidMap);
    }

    /**
     * 将传入的 Map 转换为树形结构，并且不排序
     * map 一般由外部使用 redis 存储，如果没有存储，可调用 buildTree(List<T> treeNodes, boolean sort) 方法，但是转换会影响效率
     * @param id 最开始构建的顶端ID
     * @param sort 是否需要排序
     * @param nodeIdMap Map, key: ID, value: 节点
     * @param nodePidMap Map, key: 父ID, value: 相同父ID的树列表
     * @param <T> 实现 TreeNode 的类
     * @return 最终树形结构
     */
    public static <T extends TreeNode<T>> List<T> buildTree(Long id, boolean sort, Map<Long, T> nodeIdMap, Map<Long, List<T>> nodePidMap) {

        id = id == null ? 0L : id;
        T topTreeNode = null;
        if (id != 0L && nodeIdMap.containsKey(id)) {
            topTreeNode = nodeIdMap.get(id);
        }
        List<T> pTree = nodePidMap.get(id);

        List<T> treeList = build(pTree, nodePidMap);
        if (sort) {
            sort(treeList);
        }

        if (topTreeNode != null) {
            topTreeNode.setTreeNodes(treeList);
            return Collections.singletonList(topTreeNode);
        } else {
            return treeList;
        }
    }

    /**
     * 递归构建树
     * @param treeNodes
     * @param pTreeNodeMap
     * @param <T>
     * @return
     */
    private static <T extends TreeNode<T>> List<T> build(List<T> treeNodes, Map<Long, List<T>> pTreeNodeMap) {
        if (treeNodes != null && !treeNodes.isEmpty()) {
            for (TreeNode<T> treeNode : treeNodes) {
                if (pTreeNodeMap.containsKey(treeNode.getId())) {
                    List<T> subTrees = pTreeNodeMap.get(treeNode.getId());
                    treeNode.setTreeNodes(build(subTrees, pTreeNodeMap));
                }
            }
        }
        return treeNodes;
    }

    /**
     * 构建全路径，只往上找
     * @param treeNodes 树列表，所有数据
     * @param ids 需要的全路径的id
     * @param <T> 树
     * @return 全路径
     */
    public static <T extends TreeNode<T>> List<T> buildSubTree(List<T> treeNodes, Long... ids) {
        if (ids == null || ids.length == 0) {
            return Collections.emptyList();
        }
        Map<Long, T> nodeIdMap = treeNodes.stream().collect(Collectors.toMap(TreeNode::getId, t -> t));

        return buildSubTree(nodeIdMap, ids);
    }

    /**
     * 构建全路径，只往上找
     * @param treeNodeMap map，一般由外部 redis 缓存起来
     * @param ids 需要的全路径的id
     * @param <T> 树
     * @return 全路径
     */
    public static <T extends TreeNode<T>> List<T> buildSubTree(Map<Long, T> treeNodeMap, Long... ids) {
        if (ids == null || ids.length == 0) {
            return Collections.emptyList();
        }
        List<T> subTreeList = new ArrayList<>();
        for (Long id : ids) {
            T treeNode = buildFullPath(treeNodeMap, id);
            if (treeNode != null) {
                subTreeList.add(treeNode);
            }
        }

        return subTreeList;
    }

    /**
     * 构建全路径
     * @param treeNodeMap map
     * @param id 父ID
     * @param <T> 树
     * @return 全路径
     */
    private static <T extends TreeNode<T>> T buildFullPath(Map<Long, T> treeNodeMap, Long id) {
        if (id == null) {
            return null;
        }

        if (treeNodeMap.containsKey(id)) {
            T treeNode = treeNodeMap.get(id);
            return findFullPath(treeNode, treeNodeMap);
        } else {
            return null;
        }

    }

    /**
     * 递归向上寻找全路径
     * @param treeNode 需要寻找上级的节点
     * @param treeNodeMap key：id，value：树类
     * @param <T> 树
     * @return 传入的 node 的全路径
     */
    private static <T extends TreeNode<T>> T findFullPath(T treeNode, Map<Long, T> treeNodeMap) {
        if (treeNode.getPid() == null || treeNode.getPid() == 0L) {
            return treeNode;
        }
        if (treeNodeMap.containsKey(treeNode.getPid())) {
            T pTreeNode = treeNodeMap.get(treeNode.getPid()).copyMySelf();
            if (pTreeNode.getTreeNodes() == null || pTreeNode.getTreeNodes().isEmpty()) {
                List<T> list = new ArrayList<>();
                list.add(treeNode);
                pTreeNode.setTreeNodes(list);
            } else {
                pTreeNode.getTreeNodes().add(treeNode);
            }
            return findFullPath(pTreeNode, treeNodeMap);
        } else {
            return treeNode;
        }
    }

    /**
     * 排序
     * @param treeNodes 树形结构数据
     * @param <T> 树
     */
    private static <T extends TreeNode<T>> void sort(List<T> treeNodes) {
        if (treeNodes != null && !treeNodes.isEmpty()) {
            Collections.sort(treeNodes);
            for (TreeNode<T> treeNode : treeNodes) {
                if (treeNode.getTreeNodes() != null && !treeNode.getTreeNodes().isEmpty()) {
                    sort(treeNode.getTreeNodes());
                }
            }
        }
    }


}
