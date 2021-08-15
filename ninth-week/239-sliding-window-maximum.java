class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 边界判断
        if (nums.length == 1) return nums;

        // 初始化数据
        int n = nums.length;
        int[] result = new int[n - k + 1];

        // 使用队列存放当前窗口最大值的下标，保证队列中的元素从大到小排列
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            // 弹出队尾元素，要保证元素从大到小，如果前面数小则依次弹出，直至满足要求
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) queue.pollLast();
            // 放入元素（当前索引值）
            queue.offer(i);
            // 判断当前队首元素是否有效
            if (queue.peek() <= i - k) queue.poll();
            // 保存当前窗口最大值
            if (i + 1 >= k) result[i + 1 - k] = nums[queue.peek()];
        }
        return result;
    }
}
