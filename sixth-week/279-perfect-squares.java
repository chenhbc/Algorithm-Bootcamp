class Solution {
    public int numSquares(int n) {
        // 初始化 DP 数组
        int[] dp = new int[n + 1];
        // 第一个为0，因为数字是从1开始的
        dp[0] = 0;
        // 背包个数
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
            // 找到小于等于 i 的 物品（j 的平方）
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i - j * j] + 1, dp[i]); 
            }
        }
        return dp[n];
    }
}
