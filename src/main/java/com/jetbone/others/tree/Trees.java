package com.jetbone.others.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chris
 * @date 2021-11-23
 */
public class Trees {


    public static <T extends TreeNode<T>> List<T> buildTree(List<T> treeNodes) {
        var idMap = treeNodes.stream().collect(Collectors.toMap(TreeNode::getCode, t -> t, (t1, t2) -> t1));
        List<T> tree = new ArrayList<>();

        treeNodes.forEach(node -> {
            var path = node.getPath();
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            var pathCodes = path.split("/");
            if (pathCodes.length > 1) {
                boolean isRoot = true;
                for (int i = pathCodes.length - 2; i >= 0; i--) {
                    var code = pathCodes[i];
                    if (idMap.containsKey(code)) {
                        isRoot = false;
                        T pNode = idMap.get(code);
                        if (pNode.getTreeNodes() == null) {
                            List<T> list = new ArrayList<>();
                            list.add(node);
                            pNode.setTreeNodes(list);
                        } else {
                            pNode.getTreeNodes().add(node);
                        }

                        break;
                    }
                }
                if (isRoot) {
                    tree.add(node);
                }

            } else {
                tree.add(node);
            }
        });

        return tree;
    }

}
