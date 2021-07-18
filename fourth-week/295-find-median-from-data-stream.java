class MedianFinder {
    /**
     * 思路：用中位数分隔数据为两部分，一部分是中位数以下的数字，一部分是中位数以上的数字
     *      取数据的时候只要取前部分数据的最大数字和后部分数据的最小数字即可
     *      维护最大数字和最小数字，用两个堆来存储：大顶堆和小顶堆
     *          大顶堆      小顶堆
     *      [3, 4, 6, 7] [8, 9, 10]
     *      [3, 4, 6, 7] [8, 9, 10, 11]
     */

    // 小顶堆
    private PriorityQueue<Integer> minHeap;

    // 大顶堆
    private PriorityQueue<Integer> maxHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();

        // Java 里的优先队列是小顶堆，构建大顶堆的话需要重写比较器
        maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    }
    
    public void addNum(int num) {
        // 先放小顶堆，然后把小顶堆里最小的值放到大顶堆里，用于平衡两边的数据，省去复杂的判断
        minHeap.offer(num);
        maxHeap.offer(minHeap.poll());

        // 如果两个堆大小相差大于1，则需要调整数据
        if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.offer(maxHeap.poll());
        }
    }
    
    public double findMedian() {
        int size = minHeap.size() + maxHeap.size();
        if ((size & 1) == 1) {
            // 奇数：返回大顶堆中的最大值
            return maxHeap.peek();
        }
        // 偶数：返回大顶堆最大值和小顶堆最小值和的平均值
        return (minHeap.peek() + maxHeap.peek()) * 0.5;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
