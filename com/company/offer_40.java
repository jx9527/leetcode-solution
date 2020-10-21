package com.company;


import java.awt.image.AffineTransformOp;
import java.nio.file.ClosedWatchServiceException;
import java.util.*;

//TopK问题
public class offer_40 {

    // 快速排序模板
    public static void quickSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        quickSort(arr,0,arr.length-1);

    }

    // 快速排序，使得整数数组arr的[L,R]部分有序
    public static void quickSort(int[] arr,int low,int high){
        if (low<high){
            //把数组中随机的一个元素与最后一个元素交换，
//            swap(arr,new Random().nextInt(R-L+1)+L,R);

            //找准基准元素的正确索引
            int index = getIndex(arr,low,high);
            quickSort(arr, low, index-1);
            quickSort(arr,index+1,high);
        }
    }

    //分区，整数数组arr的[L,R]部分上，使得
    // 大于arr[R]的元素位于[L,R]部分的右边，但这部分数据不一定有序
    // 小于arr[R]的元素位于[L,R]部分的左边，但这部分数据不一定有序
    // 返回等于部分的第一个
    public static int getIndex(int[] arr, int low, int  high){
        //基准数据
        int tmp = arr[low];
        while (low<high){
            //当队尾的元素大于基准数据时，向前挪动high指针
            while (low<high && arr[high]>=tmp){
                high--;
            }
            // 如果队尾元素小于tmp了,需要将其赋值给low
            arr[low] = arr[high];

            while (low<high && arr[low]<=tmp){
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = tmp;
        return low;
    }


    //利用快排排好第K个数(下标为K-1),那么它左边的数就是比它小的另外K-1个数了
    //时间复杂度：每次拿到元素的index都可以看做上一次遍历的1/2，因此时间复杂度
    //N(第一次拿index遍历整个数组)+N/2+N/4+...+N/N=2N;
    public int[] getLeastNumbers(int[] arr,int k){
        if (k==0||arr.length==0){
            return new int[0];
        }
        return quickSort(arr,0,arr.length-1,k-1);
    }
    private int[] quickSort(int[] nums,int low,int high,int k){
        //每快排切分一次，找到排序为下标j的元素，如果j刚好等于k就返回j以及j左边所有的数；
        int index = getIndex(nums,low,high);
        if (index == k){
            return Arrays.copyOf(nums,index+1);
        }
        if (index>k){
         return  quickSort(nums,low,index-1,k);
        }else {
         return  quickSort(nums,index+1,high,k);
        }
    }

    //大根堆(前k小)/小跟堆(前k大),java中现成的PriorityQueue,实现起来最简单：O(Klog(N))
    public int[] getLeastNumvers1(int[] arr,int k){
        if (k==0||arr.length==0) {
            return new int[0];
        }
        Queue<Integer> pq = new PriorityQueue<>((v1,v2)->v2-v1);
        for (int num: arr){
            if (pq.size()<k){
                pq.offer(num);
            }else if (num<pq.peek()){
                pq.poll();
                pq.offer(num);
            }
        }
        //返回堆中的元素
        int[] res = new int[pq.size()];
        int idx = 0;
        for (int num:pq){
            res[idx++] = num;
        }
        return res;
    }

    //大顶堆&小顶堆的底层实现PriorityQueue()
    //topK+堆排序

    //1.topK方案:
    // 取前k小  1、先把前k个数字建立大跟堆 logK 2、之后的数字进行比较 如果比大顶堆的顶部大的直接跳过 如果小的 大顶堆poll()一下 然后加入该数 重新构造堆 (N-K)log(K)
    // 直到遍历完该数组

    //2.堆排序方案
   // (1)先把整个数组构造大顶堆  logN    (2) 然后remove数组顶部 Nlog(N)
    public static void maxHeapSort(int[] arr){

        maxHeapBuild(arr);
        int size = arr.length;
        while (size>1) {
            //先交换
            swap(arr, 0, size - 1);
            size--;
            //再排序
            maxSiftDown(arr,0,size);
        }

    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void maxHeapBuild(int[] arr){
        for (int i=arr.length/2;i<arr.length;i++){
            int child = i;
            int father = (child-1)/2;
            // 如果当前插入的值大于父节点的值 那么交换 并将索引指向父节点
            // 然后继续和上面的节点比较 直达不大于父节点
            while (arr[child]>arr[father]){
                swap(arr,child,father);
                child = father;
                father = (father-1) / 2;
            }
        }
    }

    public static void  maxSiftDown(int[] arr,int parent,int size){
        int leftChild = 2 * parent + 1;
        int rigthChild = 2 * parent + 2;
        while (leftChild < size){
            int largeIndex;
            if (arr[leftChild]<arr[rigthChild] && rigthChild < size){
                largeIndex = rigthChild;
            }else {
                largeIndex = leftChild;
            }
            //如果父节点大于子节点 则已经构建完成
            if (arr[parent]<arr[largeIndex]){
                swap(arr,largeIndex,parent);
                parent = largeIndex;
            }else {
                break;
            }
        }
    }


    //==============官方方法实现===================================//
    //https://blog.csdn.net/u010623927/article/details/87179364

    public void sortheapGF(int[] arr){
        build(arr);
        int size = arr.length;
        while (size>1){
            swap(arr,0,size-1);
            size--;
            siftDown(arr,0,size);
        }
    }
    public static void build(int[] arr){
        for(int i=1;i<arr.length;i++){
            int child = i;
            while (child>0){
                int parent = (i-1)/2;
                int e = arr[parent];
                if (arr[child]>e){
                    arr[child]=e;
                }else{
                    break;
                }
                child = parent;
            }
            arr[child]=arr[i];
        }
    }

    public static void siftDown(int[] arr,int parent,int size){
        int half = size/2;
        int x = arr[parent];
        //找到子节点两者中的最大者
        while (parent < half){
            int childLeft = 2 * parent + 1;
            int childRight = 2 * parent + 2;
            int max = childLeft;
            if (childRight < size && arr[childRight]>arr[childLeft]){
                max = childRight;
            }
            if (x > arr[max]){
                break;
            }
            arr[parent] = arr[max];
            parent = max;
        }
        arr[parent] = x;
    }
    ///==============================================================//

    //TreeMap、TreeSet的底层实现





    //HashMap、HashSet、HashTable的底层实现




    // 二叉树搜索树(BTS)解决
    // TreeMap的key是数字，value为该数字的个数
    // 遍历数组，维护一个数字总数为K的TreeMap
    // 1、若目前map中数字个数小于K，则将map中当前数字对应的个数+1
    // 2、否则，判断当前数字与map中最大的数字的大小关系；若当前数字大于等于map的最大数字，则直接跳过该数字；
    // 若小于等于map中的数字，则将map当前数字的个数+1，并将最大数字的个数-1;

    // O(Nlog(k))

    public int[] getLeastNumbers2(int[] arr,int k){
        if (k==0 || arr.length == 0){
            return new int[0];
        }
        // TreeMap的key是数字，value是该数字的个数
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int cnt = 0;

        for (int num:arr){
            if (cnt<k){
                map.put(num,map.getOrDefault(num,0)+1);
                cnt++;
                continue;
            }
            //拿到右子节点
            Map.Entry<Integer,Integer> entry = map.lastEntry();
            if (entry.getKey() > num){
                map.put(num,map.getOrDefault(num,0)+1);
                if (entry.getValue() == 1){
                    map.pollLastEntry();
                }else {
                    map.put(entry.getKey(),entry.getValue()-1);
                }
            }
        }
        int[] res = new int[k];
        int idx = 0;
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            int freq = entry.getValue();
            while (freq-->0){
                res[idx++] = entry.getKey();
            }
        }
        return res;
    }

    //数据范围有限时，直接计数排序：O(N)
    public int[] getLeastNumbers4(int[] arr,int k){
        if (k==0 || arr.length == 0){
            return new int[0];
        }
        //统计每个数字出现的次数
        int[] counter = new int[10001];
        for (int num:arr){
            counter[num]++;
        }
        //根据counter数组从头找出k个数作为返回结果
        int[] res = new int[k];
        int idx = 0;
        for (int num=0;num< counter.length;num++){
            while (counter[num]-- > 0 && idx < k){
                res[idx++] = num;
            }
            if (idx == k) {
                break;
            }
        }
        return res;
    }



}
