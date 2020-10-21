package com.company;


import com.company.search.In;
import sun.reflect.generics.tree.Tree;

import javax.transaction.TransactionRequiredException;
import java.security.interfaces.DSAPublicKey;
import java.util.*;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){ val = x;}
}



public class leet637 {

    // BFS迭代
    public List<Double> averageofLevels(TreeNode root){
        if (root==null) return null;
        Queue<TreeNode> list = new LinkedList<>();
        List<Double> average = new ArrayList<>();
        list.add(root);
        while (list.size()!=0){
            int len = list.size();
            double sum = 0;
            for (int i=0;i<len;i++){
                TreeNode node = list.poll();
                sum += node.val;
                if (node.left!=null){
                    list.add(node.left);
                }
                if (node.right!=null){
                    list.add(node.right);
                }
            }
            average.add(sum/len);
        }
        return average;
    }

    //BFS递归
    public List<Double> averageofLevels_1(TreeNode root){
        List<Double> bigList = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        dfs(list,bigList);
        return bigList;
    }
    public void dfs(LinkedList<TreeNode> list,List<Double> bigList){
        if (list.size()==0) return;
        double sum = 0;
        int size = list.size();
        for (int i=0;i<size;i++){
            TreeNode t = list.remove();
            sum += t.val;
            if (t.left!=null) list.add(t.left);
            if (t.right!=null) list.add(t.right);
        }
        bigList.add(sum/size);
        dfs(list,bigList);
    }

    //DFS
    //在向下深搜时，当前层的level=sum的大小
    //因为起初level==sum==0，而且level先+1,直到sum+1。满足level>=sum.size()
    //等到同层时,level<sum.size()
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Integer> counts = new ArrayList<>();
        List<Double> sums = new ArrayList<>();
        dfs(root,0,counts,sums);
        List<Double> averages = new ArrayList<>();
        int size = sums.size();
        for (int i = 0; i < size; i++) {
            averages.add(sums.get(i) / counts.get(i));
        }
        return averages;
    }
    public static void dfs(TreeNode root,int level,List<Integer> counts,List<Double> sums) {
        if (root == null) return;
        if (level < sums.size()) {
            sums.set(level, sums.get(level) + root.val);
            counts.set(level, counts.get(level) + 1);
        } else {
            sums.add(1.0 * root.val);
            counts.add(1);
        }
        dfs(root.left, level + 1, counts, sums);
        dfs(root.right, level + 1, counts, sums);
    }

    public static void main(String[] args) {
    }
}
