package com.jetbone.others.tree;

import lombok.Data;

import java.util.List;

/**
 * @author Chris
 * @date 2021-03-11
 */
@Data
public class TreeNodeDemo implements TreeNode<TreeNodeDemo> {

    private static final long serialVersionUID = 1293523231552212908L;

    private Long id;

    private Integer sortNo;

    private Long pid;

    private String name;

    private List<TreeNodeDemo> treeNodes;

    public TreeNodeDemo(Long id, int sortNo, Long pid, String name) {
        this.id = id;
        this.sortNo = sortNo;
        this.pid = pid;
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public TreeNodeDemo copyMySelf() {
        return new TreeNodeDemo(this.getId(), this.getSortNo(), this.getPid(), this.getName());
    }
}
