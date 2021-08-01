class Solution {
    public int climbStairs(int n) {
        // 边界判断
        if (n < 2) return n;
        // 定义n+1长度的数组，用于存储累加结果
        int[] dp = new int[n + 1];
        // 初始化值
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
