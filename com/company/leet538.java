package com.company;

import java.util.Stack;

public class leet538 {
            static int sum = 0;
            public static TreeNode convertBST(TreeNode root){
                 conver(root);
                 return root;
            }
            public static void conver(TreeNode node){
                if (node == null) return;
                conver(node.right);
                node.val +=sum;
                sum = node.val;
                conver(node.left);
            }

            public static void main(String[] args){
                TreeNode node = new TreeNode(5);
                node.right = new TreeNode(13);
                node.left = new TreeNode(2);
                convertBST(node);
            }
}
