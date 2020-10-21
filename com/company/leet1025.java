package com.company;

import com.sun.org.apache.xml.internal.serializer.utils.BoolStack;


//见Weiwei题解
public class leet1025 {
    //问题定位：博奕类动态规划
    /**
     * 特点: 两个人的操作交替进行
     *      假设每一个人都[足够聪明],每一步都假设他们使用【动态规划】考虑了所有可能的情况，走出了最有利于自己的那一步
     */
    /**
     * 解决博弈类动态规划问题的方法：
     * 多见一些问题；
     * 画图分析，通常都是由一个最基本的情况导致了全局的情况，所以博弈类动态规划问题有这样的特点，其实输入的数据就决定了结果；
     * 每一步通常有多种选择，所以先用「记忆化递归」，然后再改成「动态规划」可能是一个相对比较好的学习路径；
     * 还有一类博弈类问题的思路是这样的：考虑「自己受益最大化」等价于「自己的选择可以让对方的受益最小」，
     * 所以「对方获得」这件事情就相当于「自己失去」，
     * 因此在设计状态和设计递归函数的时候，最大化的目标值是「相对分数」，
     * [力扣」上有一类问题叫做「极小化极大」大概是这个思路。
     */

    //记忆化递归
    public boolean divisorGame(int N){
        if (N==1) return false;
        Boolean[] memo = new Boolean[N+1];
        return dfs(N,memo);
    }
    public boolean dfs(int n, Boolean[] memo){
        if (n==1) return false;
        if (n==2) return false;
        if (memo[n]!=null) return memo[n];
        boolean canWin = false;
        // n/2是下取整，所以可以取等号
        for (int i=1;i<=n/2;i++){
            if (n%i==0&&!dfs(n-i,memo)){
                // dfs(n-i)=false 指如果对手的下一个状态值有false，就说明我们可以赢
                canWin = true;
                // 由于假设两边都足够聪明，因此我们赢了之后，就可以退出循环了
                break;
            }
        }
        memo[n] = canWin;
        return canWin;
    }

    //动态规划
    public boolean divisorGame_1(int N){
        if(N==1) return false;
        //dp[i]:黑板上的数字为i时，当前做出选择的人是否会赢
        //初始化状态都是false
        boolean dp[] = new boolean[N+1];
        //BaseCase
        dp[1] = false;
        dp[2] = true;
        //以线性的方式逐步递推得到结果
        for (int i=3;i<=N;i++){
            for (int j=1;j<=i/2;j++){
                //只要做出的选择的其中之一，能让对方输，在当前这一步我们就可以赢
                if (i%j==0 && !dp[i-j]){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[N];
    }

    //数学方法
    public boolean divisorGame_2(int N){
        return N%2==0;
    }

    public static void main(String[] args){
        boolean dp11[] = new boolean[10];
        System.out.println(dp11[1]);
    }

}
