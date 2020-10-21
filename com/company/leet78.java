package com.company;

import com.sun.org.apache.xml.internal.utils.res.XResources_zh_CN;

import javax.jnlp.ClipboardService;
import java.util.ArrayList;
import java.util.List;

public class leet78 {

    public List<List<Integer>> subSet(int[] nums){
        List<List<Integer>> res = new ArrayList<>(1<<nums.length);
        //先添加一个空集合
        res.add(new ArrayList<>());
        for (int num:nums){
            //每遍历一个元素就在之前的子集中每个集合中追加这个元素，让他变成新的子集
            for (int i=0,j=res.size();i<j;i++){
                //遍历之前的子集，重新封装成一个子集
                List<Integer> list = new ArrayList<>(res.get(i));
                //然后在新的子集后面追加这个子集
                list.add(num);
                //把这个新的子集添加到集合中
                res.add(list);
            }
        }
        return res;
    }

    //回溯算法
    public List<List<Integer>> subSet1(int[] nums){
        List<List<Integer>> lists = new ArrayList<>();
        backtrack(lists,new ArrayList<>(),nums,0);
        return lists;
    }
    private void backtrack(List<List<Integer>> lists, List<Integer> tempList,int[] nums, int start){
        //走过的路径都是子集的一部分，所以要加入到集合中
        lists.add(new ArrayList<>(tempList));
        for (int i=start;i<nums.length;i++){
            //做出选择
            tempList.add(nums[i]);
            //递归
            backtrack(lists,tempList,nums,i+1);
            //撤销选择
            tempList.remove(tempList.size()-1);
        }
    }
}
