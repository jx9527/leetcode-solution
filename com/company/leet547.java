package com.company;

import java.util.LinkedList;
import java.util.Queue;

class UF{
    private int count;
    private int[] size;
    private int[] parent;
    public UF(int n){
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



public class leet547 {


    // 并查集
    public int findCircleNum(int[][] M){
        UF uf = new UF(M.length);
        for (int i= 0;i<M.length;i++){
            for (int j=i+1;j<M.length;j++){
                if (M[i][j] == 1){
                    uf.union(i,j);
                }
            }
        }
        return uf.count();
    }

    //dfs 从每个节点开始的dfs都能遍历完与该节点相连的所有节点 即连通图 然后用visted做标记
    public int findCircleNum_1(int[][] M){
        int[] visted = new int[M.length];
        int count = 0;
        for (int i=0;i<M.length;i++){
            if (visted[i] == 0){
                dfs(M,visted,i);
                count++;
            }
        }
        return count;
    }
    public void dfs(int[][]M,int[] visted,int i){
        for (int j=0;j<M.length;j++){
            if (M[i][j] == 1 && visted[j] == 0){
                visted[j] = 1;
                dfs(M,visted,j);
            }
        }
    }
    //bfs
    public int findCirCleNum_2(int[][] M){
        int[] visted = new int[M.length];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0;i<M.length;i++){
            if (visted[i]==0){
                queue.add(i);
                while (!queue.isEmpty()){
                    int s = queue.poll();
                    visted[s] = 1;
                    for (int j=0;j<M.length;j++){
                        if (M[s][j] == 1 && visted[j] == 0){
                            queue.add(j);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }



}
