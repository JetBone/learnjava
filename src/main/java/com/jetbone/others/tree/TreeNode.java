package com.jetbone.others.tree;

import java.util.List;

/**
 * @author Chris
 * @date 2021-03-11
 */
public interface TreeNode<T extends TreeNode<T>> extends Comparable<TreeNode<T>> {

    /**
     * 获取ID
     * @return ID
     */
    Long getId();

    /**
     * 获取排序序号
     * @return 排序序号
     */
    Integer getSortNo();

    /**
     * 获取父ID
     * @return 父ID
     */
    Long getPid();

    /**
     * 获取子树
     * @return 子树
     */
    List<T> getTreeNodes();

    /**
     * 设置子树
     * @param treeNodes 子树
     */
    void setTreeNodes(List<T> treeNodes);

    /**
     * 复制自己
     * 请实现者自行决定是深克隆还是浅克隆
     * 建议深克隆，或者浅克隆+子节点不克隆达到深克隆的目的
     * @return 复制品
     */
    T copyMySelf();

    /**
     * 排序
     * @param o 目标
     * @return 排序大小
     */
    @Override
    default int compareTo(TreeNode o) {
        return (this.getSortNo() == null ? 0 : this.getSortNo()) - (o.getSortNo() == null ? 0 : o.getSortNo());
    }

}
