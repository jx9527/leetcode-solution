package com.company;


import java.util.*;

//二叉树的深度优先搜索和广度优先搜索
//递归法和迭代法
public class leet94_144_145_102 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //中序遍历--递归
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }
    public void inorder(TreeNode root,List<Integer> res){
        if (root==null)return;
        inorder(root.left,res);//
        res.add(root.val); //先左孩子  再根节点  再右孩子
        inorder(root.right,res);//
    }
    //中序遍历--迭代
    public List<Integer> inorderTraversal_1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            //遍历到左孩子和叶子节点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    //前序遍历
    public void preOrderTraversel(TreeNode root){
        if (root!=null) return;
        List<Integer> res = new ArrayList<>();
        preOrder(root,res);
    }
    public void preOrder(TreeNode root,List<Integer> res){
        if (root==null) return;
        res.add(root.val);//先根节点 再左子树 后右子树
        preOrder(root.left,res);
        preOrder(root.right,res);
    }

    //迭代版
    public void preOrderTraversel_1(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            while (root!=null){
                res.add(root.val);
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
    }
    //后序遍历递归版
    //.......
    //后序遍历迭代版
     public List<Integer> postOrderTraversel(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.isEmpty()){
            while (root!=null){
                res.add(root.val);
                stack.push(root);
                root = root.right;
            }
            root = stack.pop();
            root = root.left;
        }
        Collections.reverse(res);
        return res;
    }


    //算法思想：
    // 首先经过根节点的左右孩子节点，然后进入左子树
    // 遍历到左子树的叶子节点，入栈时为保证出栈是左右顺序，入栈按照右节点 后 左节点保存，
    //那么如果出栈到左子树的根节点，因为左子树的根节点为pre，右子树的根节点为cur，那么他俩不是父子关系，
    //则进入右子树，相同规则入栈到右子树的叶子节点，然后出栈，最后出栈根节点。
    public List<Integer> postOrderTraversel_1(TreeNode root){
        TreeNode cur,pre = null;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            cur = stack.peek();
            if ((cur.left==null && cur.right==null)||(pre!=null && (pre==cur.left ||pre==cur.right))){
                //如果深度到叶子节点
                //或者
                res.add(cur.val);
                stack.pop();
                pre = cur;
            }else {
                if (cur.right!=null){
                    stack.push(cur.right);
                }
                if (cur.left!=null){
                    stack.push(cur.left);
                }
            }
        }
        return res;
    }

    //层次遍历

    public List<List<Integer>> levelOrder(TreeNode root){
        //按层遍历即可
        List<List<Integer>> lists = new ArrayList<>();
        if (root==null) return lists;
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);

        while (!treeNodes.isEmpty()){
            List<Integer> temp = new ArrayList<>();
            int level_len = treeNodes.size();
            for (int i=0;i<level_len;i++){
                TreeNode node = treeNodes.poll();
                temp.add(node.val);
                if (node.left!=null){
                    treeNodes.add(node.left);
                }
                if (node.right!=null){
                    treeNodes.add(node.right);
                }
            }
            lists.add(temp);
        }
        return lists;
    }


}