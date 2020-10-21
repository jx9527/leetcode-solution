package com.company;

import com.sun.security.auth.UnixNumericUserPrincipal;

import javax.transaction.TransactionRequiredException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



//dfs+bfs
public class leet404 {
    private int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return sum;
    }
    public void dfs(TreeNode root){
        if (root==null)return;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
        dfs(root.left);
        dfs(root.right);
    }
    public void bfs(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node.left!=null){
                if (node.left.left==null && node.left.right==null)
                queue.add(node.left);
            }
            if (node.right!=null){
                queue.add(node.right);
            }
        }
    }
}
