package com.company;

import javax.print.attribute.standard.NumberUp;
import java.util.LinkedList;
import java.util.Queue;



//三种解法

public class leet968 {
    //树形dp的通用解法
    //确定节点状态
    //a.root放置相机的情况下，覆盖整棵树所需的最少的摄像头数目；
    //b.覆盖整棵树需要的摄像头数目，不管root是否放置摄像头
    //c.覆盖两棵子树需要的摄像头数目，无论节点root是否放摄像头

    //对于节点root,设其孩子节点left,right的状态变量为(la,lb,lc),(ra,rb,rc)

    // a = lc + rc + 1    左孩子的子树覆盖 + 左孩子的子树覆盖 + root放相机 = 全树覆盖
    // b = min(a,min(la+rb,ra+lb))  a|| 左孩子覆盖 (包含覆盖root)+ 右孩子覆盖 || 右孩子覆盖(包含覆盖root) + 左孩子覆盖
    // c = min(a, lb + rb)

    //  边界条件： 对于某个孩子为空的情况，代表该孩子不能放相机，那么该孩子对应a状态，返回一个大整数，表示不可能的情况。
    //  b,c状态返回 0

    public int minCameraCover(TreeNode root) {
        int[] array = dfs(root);
        return array[1];
    }

    public int[] dfs(TreeNode root) {
        if (root == null) return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        int[] leftArray = dfs(root.left);
        int[] rightArray = dfs(root.right);
        int[] array = new int[3];
        array[0] = leftArray[2] + rightArray[2] + 1;
        array[1] = Math.min(array[0], Math.min(leftArray[0] + rightArray[1], rightArray[0] + leftArray[1]));
        array[2] = Math.min(array[0], leftArray[1] + rightArray[1]);
        return array;
    }

    //贪心策略：尽量让叶子节点的父节点安装相机，这样相机的数量才是最少的
    // 所以遍历顺序，从下往上
    // 确定节点状态：0该节点无覆盖  1本节点有摄像头  2本节点有覆盖
    // 1、初始解：空节点，为保证叶子节点的父节点安装相机，空节点不能是无覆盖状态，也不能安装相机。所以是有覆盖状态。
    // 2、看单层处理逻辑
    //  (1). 左右节点有覆盖  那么中间节点root为0状态
    //  (2). 左右节点至少有一个无覆盖的情况  那么root应该为1状态  result++;
    //  (3). 左右节点至少有一个摄像头 那么root为2状态
    //  (4). 头节点可能是无覆盖状态  如果无覆盖 result++
    private int result ;
    private int traversal(TreeNode cur){
        //空节点，该节点有覆盖
        if (cur==null) return 2;
        int left = traversal(cur.left);  //左
        int right = traversal(cur.right); //右

        //情况(1)
        if (left==2&&right==2) return 0;
        //情况(2)
        if (left==0||right==0){
            result++;
            return 1;
        }
        //情况(3)
        if (left==1||right==1) return 2;
        return -1;
    }

    public int minCameraCover_1(TreeNode root){
        result = 0;
        if (traversal(root) == 0){
            result++;
        }
        return result;
    }

    //#############################################################################################//

    //NO_CAMERA表示的是子节点没有相机，当前节点也没放相机
    private final int NO_CAMERA = 0;
    //HAS_CAMERA表示当前节点有一个相机
    private final int HAS_CAMERA = 1;
    //NO_NEEDED表示当前节点没有相机，但他的子节点有一个相机，把它给
    //覆盖了，所以它不需要了。或者他是一个空的节点也是不需要相机的
    private final int NO_NEEDED = 2;

    //全局的，统计有多少相机
    int res = 0;

    public int minCameraCover_2(TreeNode root) {
        //边界条件判断
        if (root == null)
            return 0;
        //如果最后返回的是NO_CAMERA，表示root节点的子节点也没有相机，
        //所以root节点要添加一个相机
        if (dfs_1(root) == NO_CAMERA)
            res++;
        //返回结果
        return res;
    }

    public int dfs_1(TreeNode root) {
        //如果是空的，就不需要相机了
        if (root == null)
            return NO_NEEDED;
        int left = dfs_1(root.left), right = dfs_1(root.right);
        //如果左右子节点有一个是NO_CAMERA，表示的是子节点既没相机，也没相机覆盖它，
        //所以当前节点需要有一个相机
        if (left == NO_CAMERA || right == NO_CAMERA) {
            //在当前节点放一个相机，统计相机的个数
            res++;
            return HAS_CAMERA;
        }
        //如果左右子节点只要有一个有相机，那么当前节点就不需要相机了，否则返回一个没有相机的标记
        return left == HAS_CAMERA || right == HAS_CAMERA ? NO_NEEDED : NO_CAMERA;
    }
}