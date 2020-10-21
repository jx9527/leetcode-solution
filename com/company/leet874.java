package com.company;

import com.sun.deploy.net.offline.WIExplorerOfflineHandler;
import com.sun.media.sound.ModelWavetable;

import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class leet874 {


    //利用HashSet结构索引很快
    public int rotbotSim1(int[] commands, int[][] obstacles){
        //direction表示当前朝向,0123表  北东南西
        int ans =0,direction=0,x=0,y=0;
        int[][] Direction={{0,1},{1,0},{0,-1},{-1,0}};
        HashSet<String> set = new HashSet<>();
        for (int[] arr:obstacles){
            set.add(arr[0]+","+arr[1]);
        }
        for (int com:commands){
            //定义下一步的坐标
            int next_x=0,next_y=0;
            if (com>0){
                for (int i=0;i<com;i++){
                    //取得下一步的坐标
                    next_x = x + Direction[direction][0];
                    next_y = y + Direction[direction][1];
                    //有障碍物
                    if (set.contains(next_x+","+next_y)) break;
                    //否则更新坐标和最远距离
                    x = next_x;
                    y = next_y;
                    ans = Math.max(ans,x*x+y*y);
                }
            }else {
                //改变方向
                if (com==-1){
                    //向右转 顺时针转+1
                    direction = (direction+1) % 4;
                }else {
                    //向左转 逆时针转-1
                    direction = (direction+3) % 4;
                }
            }
        }
        return ans;
    }

}
