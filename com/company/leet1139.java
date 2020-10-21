package com.company;


import org.omg.CORBA.MARSHAL;

/**
 * 2020/08/23
 */

public class leet1139 {

    //双重关卡 设置两个flag  跳跃原点和检验步长准确度
    public int largest1BorderedSquare(int[][] grid) {
        int maxlen = 0;
        int height = grid.length;
        int width = grid[0].length;

        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                //遍历网格上的每个点
                if (grid[i][j] == 1){
                    int curlen = maxlen;
                    while (i + curlen < height && j + curlen < width){
                        //关卡1 检查curlen的有效性  (i,j)右方和下方全是1
                        boolean flag1 = true;
                        for (int temp_i=i,temp_j=j;temp_i<i+curlen+1&&temp_j<j+curlen+1;temp_i++,temp_j++){
                            if (grid[i][temp_j]==0 || grid[temp_i][j] == 0){
                                flag1 = false;
                                break;
                            }
                        }
                        if (flag1 == false) break;

                        boolean flag2 = true;
                        for (int temp_i=i+curlen,temp_j=j+curlen,_i=temp_i,_j=temp_j;temp_i>i&&temp_j>j;temp_i--,temp_j--){
                            if (grid[temp_i][_j] == 0 || grid[_i][temp_j] == 0){
                                curlen++;
                                flag2 = false;
                                break;
                            }
                        }
                        if (flag2 == false) continue;
                        maxlen = ++curlen;
                    }
                }
            }
        }
        return maxlen * maxlen;
    }


    public static int borderedSquare(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int [][][]dp = new int[m+1][n+1][2];

        /**
         * dp数组行和列都多设了一个，就避免对第一行和第一列初始化过程
         */
        for (int i=1;i<=m;i++){
            for (int j=1;j<=n;j++){
                if (grid[i-1][j-1]== 1){
                    dp[i][j][0] = 1+dp[i][j-1][0];
                    dp[i][j][1] = 1+dp[i-1][j][1];
                    System.out.println("-----------------");
                    System.out.println(dp[i][j][0]);
//                    System.out.println(dp[i][j][1]);
//                    System.out.println("-----------------");
                }
            }
        }
        int res = 0;
        for (int i = 1;i<=m;i++){
            for (int j=1;j<=n;j++){
                //枚举每个点能构成正方形的合法的side，取最大值
                //先拿到右下角的点
                //检查左下角的上边连续点个数（左边=?右边）
                //检查右上角的左边连续点个数（上边=?下边）
                for (int side = Math.min(dp[i][j][0],dp[i][j][1]);side>=1;side--){
                    if (dp[i][j-side+1][1] >= side && dp[i-side+1][j][0]>=side){
                        res = Math.max(res,side);
                        break;//更短的没必要考虑
                    }
                }
            }
        }
        return res*res;
    }

    public static void main(String args[]){
        int grid[][] ={{1,1,1},{1,0,1},{1,1,1}};
    }
}
