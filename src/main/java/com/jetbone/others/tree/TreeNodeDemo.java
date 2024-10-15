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

    private String code;

    private String path;

    private List<TreeNodeDemo> treeNodes;

    public TreeNodeDemo(Long id, Integer sortNo, Long pid, String code, String path) {
        this.id = id;
        this.sortNo = sortNo;
        this.pid = pid;
        this.code = code;
        this.path = path;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public TreeNodeDemo copyMySelf() {
        return new TreeNodeDemo(this.getId(), this.getSortNo(), this.getPid(), this.getCode(), this.getPath());
    }
}
