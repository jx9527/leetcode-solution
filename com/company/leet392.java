package com.company;

public class leet392 {

    //双指针
    private boolean isExist = false;
    public boolean isSubsequence(String s, String t) {
        if (s==null||t==null) return false;
        if (s.equals("")) return true;
        judge(s,t,0,0);
        return isExist;
    }
    public boolean isSubsequence_1(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }

    public void judge(String s,String t, int index_s, int index_t){
        if (index_s==s.length()){
            isExist = true;
            return;
        }
        if (index_t == t.length()){

        }else {
            if (s.charAt(index_s) == t.charAt(index_t)){
                judge(s,t,index_s+1,index_t+1);
            }else {
                judge(s,t,index_s,index_t+1);
            }
        }
    }

    // 贪心算法/动态规划
    // 亮点 【数据结构设计】 【提前缓存】
    //设计数据结构对数据进行预处理，用于保存26个字母对应的各种情况，那么在之后的子序列判断中就只需要查询即可
    // dp[i][j] 代表字符串t从位置i开始，字符j+'a'出现的第一个位置
    //                             i  当t[i] = 'a' (记录当前位置)
    // 单独看一列就是dp[i][0]  =
    //                             dp[i+1][0] 当t[i]!=j (保留上一个位置)
    // 那么i就要从后向前枚举 j的话就是遍历26个字母

    //BaseCase: dp[m][j] 第m行设为m,代表该字母在m之后的位置没有出现,那么dp[m-1][j]就能保证正常初始化

    // 查找:  遍历s每个字母char，并查看从t的i位置出发char的位置,如果有 i = i+index_char ,没有即index_char==m return false;
    public boolean isSubsequence_2(String s, String t) {
        int n = s.length(),m=t.length();
        int [][] f = new int[m+1][26];

        for (int i=0;i<26;i++){
            f[m][i] = m;
        }
        for (int i=m-1;i>=0;i--){
            for (int j=0;j<26;j++){
                if (t.charAt(i)==j+'a'){
                    f[i][j] = i;
                }else {

                    f[i][j] = f[i+1][j];
                }
            }
        }
        int add =0;

        for (int i=0;i<n;i++){
            if (f[add][s.charAt(i)-'a'] == m){
                return false;
            }
            add = f[add][s.charAt(i)-'a'] + 1;
        }
        return true;
    }

    //动态规划
    //dp[i][j]表示字符串t的前j个字符串包含s的前i个字符串
    // s.charAt(i-1)  == t.charAt(j-1)  dp[i][j] = dp[i-1][j-1]
    // s.charaAt(i-1) != t.chatAt(j-1) dp[i][j] =dp[i][j-1]
    public boolean isSubsequence_3(String s,String t){
        if (s.length()==0) return true;
        boolean[][] dp = new boolean[s.length()+1][t.length()+1];
        for (int i=0;i<t.length();i++){
            dp[0][i]=true;
        }
        for (int i=1;i<=s.length();i++){
            for (int j=1;j<=t.length();j++){
                if (s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    // 速度最快解法
    public boolean isSubsequence_7(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()) {
            //index表示上一次查找的位置(第一次查找的时候为-1)，所以这里要从t的下标(index+1)开始查找
            index = t.indexOf(c, index + 1);
            //没找到，返回false
            if (index == -1)
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        int m = "abc".length();
    }

}
