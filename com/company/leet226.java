package com.company;


import java.util.*;

public class leet226 {

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
       // 翻转二叉树--层序遍历【Queue】--与DFS【Stack】
       // 这里的层序遍历和DFS只是使用的数据结构不一样，其余都是一样的
    public TreeNode invertTree(TreeNode root){
        if (root==null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode right = node.right;
            root.right = root.left;
            root.left = right;
            if (node.left!=null){
                queue.add(node.left);
            }
            if (node.right!=null){
                queue.add(node.right);
            }
        }
        return root;
    }
    //翻转--后序遍历--从下向上交换
    public TreeNode invertTree_1(TreeNode root){
        if (root==null) return root;
        TreeNode left = invertTree_1(root.left);
        TreeNode right = invertTree_1(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    //先序遍历--从上向下交换
    //看待递归还是从最后一层，遍历到叶子节点看，只看一层就ok
    public TreeNode invertTree_2(TreeNode root){
        if (root==null) return root;
        TreeNode rightTree = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(rightTree);
        return root;
    }
    public static TreeNode inverTree_2_1(TreeNode root){
        if (root==null) return root;
        Stack<TreeNode> stack = new Stack<>();
        //加入tmp_node
        TreeNode node = root;
        while (!stack.isEmpty()||node!=null){
            while (node!=null){
                stack.push(node);
                TreeNode rightTree = node.right;
                node.right = node.left;
                node.left = rightTree;
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return root;
    }


    //中序遍历
    public TreeNode invertTree_3(TreeNode root){
        if (root==null) return null;
        invertTree(root.left);
        TreeNode rightNode = root.right;
        root.right = root.left;
        root.left = rightNode;
        //此时的右子树边为左子树 所以
        invertTree(root.left);
        return root;
    }

    // 遍历的时候:根节点会改变，最后返回的root,
    // 不是整个树的根节点，所以要用 TreeNode node = root去交换，最后返回根节点。
    public static TreeNode inverTree_3_error(TreeNode root){
        if (root==null)return root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root!=null){
            while (root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();//拿到中间节点
            //交换
            TreeNode rightNode = root.right;
            root.right = root.left;
            root.left= rightNode;
            //右子树已经变成左子树
            root = root.left;
        }
        return root;
    }

    public static TreeNode inverTree_3_right(TreeNode root){
        if (root==null)return root;
        Stack<TreeNode> stack = new Stack<>();
        //加入tmp_node
        TreeNode node = root;
        while (!stack.isEmpty() || node!=null){
            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();//拿到中间节点
            //交换
            TreeNode rightNode = node.right;
            node.right = node.left;
            node.left= rightNode;
            //右子树已经变成左子树
            node = node.left;
        }
        return root;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(4);
    }
}
