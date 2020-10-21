

#### 常用技巧

数据预处理



二维坐标映射为一维数组：

​                       (x, y)   ——》  x * n + y (m是行数、n是列数)





#### 哈希表法

特性，哈希表查找key/value为O(1),





#### 动态规划

##### 1. 解题框架

###### 备忘录递归法

###### 迭代动态规划法

##### 2. 问题分类

[1] 基本动规

[2] 背包问题和变体

[3] 博奕类动态规划

[4] 双重dp  leet267

[5] 树形dp leet968  leet337

​      n状态变量代表不同选择产生的状态，覆盖整棵树的情况。

​      n个变量之间的联系通过n个状态转移方程表示和求解。

​      边界条件，孩子节点==null...

##### 3. 算法思想

a. 最长公共子序列 

![1601046282097](C:\Users\49246\AppData\Roaming\Typora\typora-user-images\1601046282097.png)



#### 贪心算法

是寻找**最优解问题**的常用方法，这种方法模式一般将求解过程分成**若干个步骤**，但每个步骤都应用贪心原则，选取当前状态下**最好/最优的选择**（局部最有利的选择），并以此希望最后堆叠出的结果也是最好/最优的解。 

##### 1. Tips:  

与动态规划不同的在于它对每个子问题都做出选择，不能回退，即不看前面（不需要从前面的状态转移过来），也不看后面（后面的选择也不会对前面的选择有影响）。DP则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。

​             **回溯算法，dp，贪心都可以解决同一类问题**

回溯算法，动态规划和贪心算法其实是循序渐进的，完成一件事分步决策。回溯算法即是暴力的**枚举**，每一步对所有可能都进行计算，计算到达终点即返回，每一步返回后把状态回归到之前的状态。回溯算法的痛点是他有很多重复的计算，解决的办法是引入备忘录，备忘录记录了每一步的结果，这其实就是子问题的最优解，动态规划由此产生。所以使用动态规划时必须要满足**无后效性**，子问题的解一旦确定，就不再改变，不受在这之后、包含它的更大的问题的求解决策影响。如果一个问题没有**重叠子问题**，那就只能使用回溯算法，比如[N皇后问题](https://lil-q.github.io/2020/03/08/回溯算法/#八皇后问题)。

对于有些问题，我们通过建立数学模型之后明确知道了子问题的最优解，那么就不需要枚举各种情况了。比如有 5 元，2 元和 1 元三种硬币无限个，求用这些硬币兑换 x 元的最少硬币个数。解决这个问题总是先考虑最大面额的硬币，并不需要枚举。但是对于 5 元，4 元和 1 元这三个面额，贪心算法并不能得出最优解。

##### 3. 解题框架



##### 4. 算法思想

a. 从初始解出发，初始解符合当下最好的选择，最好往往是根据题目来的，可能是“最小”，也可能是“最大”。

b. 采用迭代的过程，当可以向目标前进一步时，就根据局部最优策略，得到一部分解，缩小问题规模。 

c. 将所有子问题的局部最优解合成原来解问题的一个解。

##### 5. 贪心策略

leet968_监控二叉树：从下向上推导，尽量让叶子节点的父节点安装摄像头，这样安装摄像头的数量才是最少的。



#### 回溯算法

##### 1.解题框架

```java
## 模板一
private void backtrack("原始参数"){
    //结束条件
    if("满足所需的条件"){
        //一些逻辑操作(可有可无，视情况而定)
        return;
    }
    for(int i="0 or start";i<"for循环结束的参数";i++){ //枚举各种情况-如果是方向，会枚举方向
          // 原始情况+枚举情况 = 新情况（枚举加方向的新坐标）
          x_new = x+dire[i][0]; y_new = y+dire[i][1];
          if("已经访问过;界限，到达边界情况;等...") continue; //剪枝
          //设置已访问状态 (新坐标已访问)
          used[start] = true; //对应i=0
          backtrack("新情况 or start+1")
          //恢复到未访问状态
          used[start] = false; //对应i=0
    }
}

##模板二
private void backtrack("原始参数"){
    //结束条件
    if("满足所需的条件"){
        //一些逻辑操作(可有可无，视情况而定)
        return;
    }
    //设置已访问状态 (坐标已访问并不符合满足的条件)
    used[start] = true; 
    for(int i="0 or start";i<"for循环结束的参数";i++){ //枚举各种情况-如果是方向，会枚举方向
          // 原始情况+枚举情况 = 新情况（枚举加方向的新坐标）
          x_new = x+dire[i][0]; y_new = y+dire[i][1];
          if("已经访问过;界限，到达边界情况;等...") continue; //剪枝
          backtrack("新情况 or start+1")
    }
    //恢复到未访问状态
   used[start] = false; /
}
```

```java

##遍历所有情况对pair组成的序列
backtrack(){
  for(int i=start;start<arr.length;start++){
     for(int j=start+1;j<arr.length;j++){
            backtrack(arr,j);
     }
  }
}
```



##### 2. 算法思想

a.(*非常重要*)画出递归树，找到状态变量(回溯函数的参数)

b.根据题意，确立结束条件

c.找选择列表 for(int i =0 or start,i<. i++)

d.判断是否需要剪枝

e.做出选择(已访问)

f.递归调用下一层 start+1

g. 撤销选择(未访问)

##### 3.相关博客

子集、组合类问题：关键使用一个start参数控制选择列表

 https://leetcode-cn.com/problems/subsets/solution/c-zong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-gao-/ 

排列问题使用used数组来标识选择列表

 https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/solution/c-zong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-ga-4/ 

搜索问题，将问题抽象成“子集，排列，组合”类型问题

 https://leetcode-cn.com/problems/binary-watch/solution/czong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-gao-d/ 





#### 二分查找

##### 算法框架

```java
## 快速找到[lo,hi]中符合possible函数条件的数字
## boolean possible();
int search(){
  int N = grid.length;
  int lo = "初始状态", hi = "可能达到的最高状态";
  while (lo < hi){
      int mi = lo + (hi-lo)/2;
            //如果mi不行,则在后半部分找
            if (!possible(mi,...)){
                lo = mi+1;
            }else {
                hi = mi;
            }
        }
        return lo;
}
```

##### 算法思想

用于加速dfs/bfs等算法的搜索进程，即possible函数为dfs



#### BFS&DFS

##### 算法框架

```java
BFS -- 采用先进先出队列Queue /其他策略的队列 PriorityQueue
void BFS(){
    Set<type> seen; //如果是寻路或者坐标问题+
    Queue<type> queue;
    q.push(初始状态)
    while(!queue.empty()){
        type k = queue.front();
        //拿出当前节点做处理
        //...
        if("结束条件") break;
        for("遍历k的下一个新状态"){
            if("next状态符合条件" && ["若是坐标问题：不能超出边界、!seen.comtains(next)"])
            {
                queue.push(next);
                seen.add(next);
                计数或维护等;
            }
        }
    }
}


DFS -- 采用栈Stack
void DFS(){
    Set<type> seen;
    Stack<type> stack;
    stack.add(0);
    
    ArrayList<type> path;//记录路径，如果是寻路等相关问题
    while(!stack.empty()){
        type k = stack.pop();
        //拿当前节点做处理
        //...
        if("结束条件"){
           System.out.println(path.toString());
           break;
        }
        for("遍历k的下一个新状态"){
            if("next状态符合条件" && ["若是坐标问题：不能超出边界、!seen.comtains(next)"])
            {
                stack.add(next);
                seen.add(next);
                计数或维护等;
            }
        }
    }
}

DFS -- 递归
boolean DFS(Node cur, Node target, Set<Node> visited) {
    return true if cur is target;
    for (next : each neighbor of cur) {
        if (next is not in visited) {
            add next to visted;
            return true if DFS(next, target, visited) == true;
        }
    }
    return false;
}
```



##### DFS遍历图中两个路径（回溯+队列/栈)  (双栈）

```c
struct point//位置
{
	int x,y;
} p;
 
stack<point> path,temp;//记录路径,temp是一个临时变量，和path一起处理路径
 
int count;//路径条数
 
void dfs(int x,int y)//x,y:当前位置
{
	if(x==n-1 && y==m-1)//成功---下面处理路径问题
	{
		cout << "******************路径"<< ++count <<  "******************" << endl;
		while(!path.empty())//将path里面的点取出来，放在temp里面
		{//path从栈顶-栈底的方向，路径是从终点-起点的顺序
			point p1 = path.top();
			path.pop();
			temp.push(p1);
		}
		while(!temp.empty())
		{//输出temp里面的路径，这样刚好是从起点到终点的顺序
			point p1 = temp.top();
			temp.pop();
			path.push(p1);//将路径放回path里面，因为后面还要回溯!!!
			cout << "(" << p1.x << "," << p1.y << ")" << endl;
		}
		return;
	}
 
	if(x<0 || x>=n || y<0 || y>=m)//越界
		return;
	
	//如果到了这一步，说明还没有成功，没有出界
	for(int i=0;i<4;i++)//从4个方向探测
	{
		int nx = x + dir[i][0];
		int ny = y + dir[i][1];//nx,ny：选择一个方向，前进一步之后，新的坐标
		if(0<=nx && nx<n && 0<=ny && ny<m && maze[nx][ny]==0 && vis[nx][ny]==0)
		{//条件：nx,ny没有出界，maze[nx][ny]=0这个点不是障碍可以走，vis[nx][ny]=0说明(nx,ny)没有访问过，可以访问
			
			vis[nx][ny]=1;//设为访问过
			p.x = nx;
			p.y = ny;
			path.push(p);//让当前点进栈
 
			dfs(nx,ny);//进一步探测
 
			vis[nx][ny]=0;//回溯
			path.pop();//由于是回溯，所以当前点属于退回去的点，需要出栈
		}
	}
}

```





#### 算法思想

对链状图，BFS最好（队列中最多只有1个元素），DFS最差（所有节点都在根节点的递归内）

对起点与其他所有点相邻的图，DFS最好（递归深度为1），BFS最差（队列中放满了所有与起点相邻的图）。

但是BFS的状态数一多，需要的空间就会较大。因此就需要状态压缩，

#### 

#### Dijkstra's algorithm

##### 算法思想

贪心+回溯：每次在回溯到的结果中找到最优值再进行下一个状态。













#### 数据结构

##### 一、树

==树的类型==

#####        二叉搜索树： a.空树   b.左子树不空，其上的节点均小于根节点   c.右子树不空，其上的节均大于根节点

==遍历==

  #####        先序、中序、后序遍历





#### 位运算和位存储

##### BitMap，采用位存储出现的字母

【leet771】__char类型范围为0-127，每个int是32位，因此只需要4个int就能储存出现的字母(4*32=128).
简单来讲BitMap就是将出现的字母储存在对应的int的二进制位中,比如'b'的值为97，因此将第97位置1。对应的就是第97/32=3个int数的第97%32=1位为0,即bitMap[97/32] |= (1<<(97%32)).
当字符a出现的时候有：bitMap[a/32] |= (1<<(a%32));
判断字符a是否出现有: bitMap[a/32] & (1<<(a%32)) == 0;





#### 前缀和

累加的性质



#### 并查集

##### 1. 解题框架

适当利用[动态连通性]：自反性、对称性、传递性

关键点：

1、用 `parent` 数组记录每个节点的父节点，相当于指向父节点的指针，所以 `parent` 数组内实际存储着一个森林（若干棵多叉树）。

2、用 `size` 数组记录着每棵树的重量，目的是让 `union` 后树依然拥有平衡性，而不会退化成链表，影响操作效率。

3、在 `find` 函数中进行路径压缩，保证任意树的高度保持在常数，使得 `union` 和 `connected` API 时间复杂度为 O(1)。

```java
class UF {
    // 连通分量个数
    private int count;
    // 存储一棵树
    private int[] parent;
    // 记录树的“重量”
    private int[] size;

    public UF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;

        // 小树接到大树下面，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    private int find(int x) {
        while (parent[x]  != x) {
            // 进行路径压缩,爷爷节点变成x的父节点
            parent[x] = parent[parent[x]];
            // 改变x的指向
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return count;
    }
}
```

##### 算法思想

解决图的连通性问题，可以用于检测图中是否存在环；

能不能应用出来主要是看你抽象问题的能力，把原始问题抽象成一个有关图论的问题。

DFS的替代方案

适时增加虚拟节点，想办法让元素「分门别类」，建立动态连通关系。

【路径压缩】策略：

   「隔代压缩」性能比较高，虽然压缩不完全，不过多次执行「隔代压缩」也能达到「完全压缩」的效果，我本人比较偏向使用「隔代压缩」的写法。
   「完全压缩」需要借助系统栈，使用递归的写法。或者先找到当前结点的根结点，然后把沿途上所有的结点都指向根结点，得遍历两次

<img src="README.assets/路径压缩.png" style="zoom:80%;" />