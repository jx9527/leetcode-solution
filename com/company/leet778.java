package com.company;

import com.sun.org.apache.regexp.internal.RE;
import javafx.util.BuilderFactory;
import sun.java2d.windows.GDIRenderer;

import java.awt.im.InputContext;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;


/**
 * 1、DFS+剪枝(N*N)  DFS+二分查找(....)  DFS+最小堆(o(N*N*log(N)))
 * 2、BFS+最小堆(N*N*log(N))  BFS+二分查找
 * 3、并查集+最小堆    并查集+二分
 */


class UF_778{
    private int count;
    private int[] size;
    private int[] parent;
    public UF_778(int n){
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i=0;i<n;i++){
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ){
            return;
        }
        if (size[rootP] > size[rootQ]){
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    public int find(int x){
        while (x!=parent[x]){
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public boolean connected(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    public int count(){
        return count;
    }

}


public class leet778 {

    private static  int minValue = Integer.MAX_VALUE;
    private static Deque<Integer> list = new LinkedList<>();
    //dfs 遍历所有路径然后比对
    //超时
    public static int swimInWater(int[][] grid){
        int[][] dire = {{0,1},{0,-1},{1,0},{-1,0}};
        boolean[] visted = new boolean[grid.length*grid.length];
        visted[0] = true;
        list.addLast(grid[0][0]);
        dfs(grid,dire,visted,0,0,grid[0][0]);
        return minValue;
    }

    private static boolean inArea(int x,int y, int length){
        return x>=0 && x<length && y>=0 && y<length;
    }

    public static void dfs(int[][] grid,int[][] dire,boolean[] visted, int x,int y,int maxValue){
        //如果到达终点
        if (x==grid.length-1 && y == grid.length-1){
            minValue = Math.min(minValue,maxValue);
            return;
        }

        visted[x*grid.length+y] =  true;
        for (int i=0;i<dire.length;i++){
            int new_x = x + dire[i][0];
            int new_y = y + dire[i][1];
            if (!inArea(new_x,new_y,grid.length) || visted[new_x*grid.length+new_y]){
                continue;
            }

            //如果上一条路径的的最大权重 小于 当前路径的max 则不需要考虑
            if (minValue<maxValue){
                continue;
            }

            dfs(grid,dire,visted,new_x,new_y,Math.max(maxValue,grid[new_x][new_y]));
        }
        visted[x*grid.length+y] = false;
    }


    //dfs 改进剪枝 用visted数组记录并比较 然后剪枝
    //最大路径的最小值
    public static int swimInWater2(int[][] grid){
        int[][] dire = {{0,1},{0,-1},{1,0},{-1,0}};
        int[] visted = new int[grid.length*grid.length];
        dfs1(grid,dire,visted,0,0,grid[0][0]);
        return minValue;
    }

    public static void dfs1(int[][] grid,int[][] dire,int[] visted, int x,int y,int maxValue){
        //如果到达终点
        if (x==grid.length-1 && y == grid.length-1){
            minValue = Math.min(minValue,maxValue);
            return;
        }
        visted[x*grid.length+y] = maxValue;
        for (int i=0;i<dire.length;i++) {
            int new_x = x + dire[i][0];
            int new_y = y + dire[i][1];
            // 如果下一个节点m已经访问过了，那么该节点m到头节点的最大权重为visted[m]   当前节点到头结点的权重为maxValue
            // 如果visted[m]<=maxValue 那么就没有走下一个节点的必要 因为走下一个节点maxValue肯定会被同化为visted[m]
            if (!inArea(new_x, new_y, grid.length) || visted[new_x * grid.length + new_y]!=0 && visted[new_x*grid.length+new_y]<=maxValue) {
                continue;
            }
            //如果上一条路径的的最大权重 小于 当前路径的max值 则不需要考虑
            if (minValue < maxValue) {
                continue;
            }
            dfs1(grid, dire, visted, new_x, new_y, Math.max(maxValue, grid[new_x][new_y]));
          }
    }

    //dfs改进  二分查找(加快深搜进程)+dfs
    // 二分查找[grid[0][0],N*N]赋值给t, 看在t下存不存在0-N-1的路径
    public int swimInWater3(int[][] grid){
        int N = grid.length;
        int lo = grid[0][0], hi = N * N;
        while (lo < hi){
            int mi = lo + (hi-lo)/2;
            if (!possible(mi,grid)){
                lo = mi+1;
            }else {
                hi = mi;
            }
        }
        return lo;
    }

    public boolean possible(int T,int[][] grid){
        int N = grid.length;
        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        while (!stack.empty()){
            int k = stack.pop();
            int r = k/N, c=k%N;
            if (r == N-1 && c == N-1){
                return true;
            }
            for (int i=0;i<4;i++){
                int cr = r + dir[i][0];
                int cc = c + dir[i][1];
                int ck = cr *  N + cc;
                if (inArea(cr,cc,N) && !seen.contains(ck) && grid[cr][cc] <= T){
                    stack.add(ck);
                    seen.add(ck);
                }
            }
        }
        return false;
    }



    // 最小堆+BFS（djksla+优先队列）
    // 每次都选择高度最小的平台，以这种方式到达终点时,路径中遇到的最高平台就是答案
    // BFS 向前波动 给遍历过的节点标记  不再遍历
    // 见详解
    // https://leetcode-cn.com/problems/swim-in-rising-water/solution/you-xian-dui-lie-fa-yi-ji-wei-he-hui-xiang-dao-you/
    public int swimInWater5(int[][] grid){
        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
        int N = grid.length;
        Set<Integer> seen = new HashSet<>();
        int ans = 0;
        //默认是小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((k1, k2) ->
                grid[k1 / N][k1 % N] - grid[k2 / N][k2 % N]);
        pq.offer(0);
        while (!pq.isEmpty()){
            int k = pq.poll();
            //横坐标
            int r = k/N;
            //竖坐标
            int c = k%N;
            ans = Math.max(ans,grid[r][c]);
            if (r == N-1 && c == N-1) {
                return ans;
            }
            for (int i=0;i<4;++i){
                int cr = r + dir[i][0],cc = c + dir[i][1];
                int ck = cr*N+cc;
                if (inArea(cr,cc,N) && !seen.contains(ck)){
                    pq.offer(ck);
                    seen.add(ck);
                }
            }
        }
        throw null;
    }

    // 二分 + BFS(判断所有小于t的节点中  从头结点出发能否波动到尾节点？)
    public int swimInWater_binarySearch_bfs(int[][] grid){
        int N = grid.length;
        int lo = grid[0][0], hi = N * N;
        while (lo < hi){
            int mi = lo + (hi-lo)/2;
            if (!possible_bfs(mi,grid)){
                lo = mi+1;
            }else {
                hi = mi;
            }
        }
        return lo;
    }

    public boolean possible_bfs(int T,int[][] grid){
        int N = grid.length;
        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()){
            int k = queue.poll();
            int r = k/N, c=k%N;
            if (r == N-1 && c == N-1){
                return true;
            }
            for (int i=0;i<4;i++){
                int cr = r + dir[i][0];
                int cc = c + dir[i][1];
                int ck = cr *  N + cc;
                if (inArea(cr,cc,N) && !seen.contains(ck) && grid[cr][cc] <= T){
                    queue.add(ck);
                    seen.add(ck);
                }
            }
        }
        return false;
    }






    //并查集  超时
    // 1、t = grid[0][0]
    // 2、所有小于t且与grid相邻的邻点构成连通图
    // 3、判断grid[0][0]与grid[N-1][N-1]是否连通
    // 4、连通直接返回 不连通t++ 重复2

    public static int swimInWater1(int[][] grid){
        int n = grid.length;
        UF_778 uf = new UF_778(n*n);
        int root = 0;
        int[][] d = new int[][]{{1,0}, {0,1}, {0,-1}, {-1,0}};
        int total = 0;

        for(int t=grid[0][0];t<50*49;t++){
            for (int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                        if (grid[i][j] <= t) {
                            for (int k = 0; k < 4; k++) {
                                int x = i + d[k][0];
                                int y = j + d[k][1];
                                if (inArea(x, y, n) && grid[x][y] <= t) {
                                    uf.union(x * n + y, i * n + j);
                                }
                            }
                        }
                }
            }

            if (uf.connected(0,n*n-1)){
                total = t;
                break;
            }
        }
        return total;
    }

    // 并查集+二分查找
    public static int swimInWater10(int[][] grid){
        int N = grid.length;
        int lo = grid[0][0], hi = N * N;
        while (lo < hi){
            int mi = lo + (hi-lo)/2;
            //如果mi不行,则在后半部分找
            if (!possible_1(mi,grid)){
                lo = mi+1;
            }else {
                hi = mi;
            }
        }
        return lo;
    }

    //检查与顶点相邻的节点是否小于mi，若小于则查看相邻的相邻 大于则查看下一个相邻
    public static boolean possible_1(int mid,int[][] grid){
        int N = grid.length;
        UF uf = new UF(N*N);
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                if (grid[i][j]>mid) {
                    continue;
                }
                if (i<N-1 && grid[i+1][j]<=mid){
                    uf.union(i*N+j,(i+1)*N+j);
                }
                if (j<N-1&& grid[i][j+1]<=mid){
                    uf.union(i*N+j,i*N+j+1);
                }
            }
        }
        if (uf.connected(0,N*N-1)){
            return true;
        }else {
            return false;
        }
    }

    //最小堆+并查集
    //每次以格子的高度为排序标准加进小顶堆
    //1、寻找合适的t
    //2、每次的t判断：都取出所有小于t的格子，然后各自连通相邻的节点，判断头结点和尾节点是否连通
    public int swimInWater8(int[][] grid){
        int N = grid.length;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((k1,k2)->(grid[k1/N][k1%N]-grid[k2/N][k2%N]));
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                priorityQueue.offer(i*N+j);
            }
        }
        UF uf = new UF(N*N);
        int time = grid[0][0];
        int[][] d = new int[][]{{1,0}, {0,1}, {0,-1}, {-1,0}};
        while (time<N*(N-1)){  //可以用二分查找优化

            while (!priorityQueue.isEmpty()&&grid[priorityQueue.peek()/N][priorityQueue.peek()%N]<time){
                int minIndex = priorityQueue.poll();
                int cr =minIndex/N, cc=minIndex%N;
                //(cr,cc的四周是否可以游泳)
                for (int k=0;k<4;k++){
                    int new_cr = cr + d[k][0];
                    int new_cc = cc + d[k][1];
                    if (inArea(new_cc,new_cr,N)&&grid[new_cr][new_cc]<=time){
                        uf.union(cr*N+cc,new_cr*N+new_cc);
                    }
                }
                if (uf.connected(0,N*N-1)){
                    return time;
                }
            }
            time++;
        }
        return 0;
    }



    public static void main(String[] args){

        String s = "[[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]";
        ArrayList<ArrayList<Integer>> iii = new ArrayList<>();

        int[][] grid ={{0,1,2,3,4}, {24,23,22,21,5}, {12,13,14,15,16}, {11,17,18,19,20}, {10,9,8,7,6}};
        int[][] grid2 = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
        int[][] grid1 = {{0,2},{1,3}};
        System.out.println(swimInWater10(grid1));
//        swimInWater1(grid1);
    }





}
