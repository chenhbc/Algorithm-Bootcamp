/**
 * 由于每天至少要运一次，所以最低运载能力不能小于最大货物的重量，这是下限
 * 上限就是货物的总重量，那么这个需要的答案即在这两者之间，所以通过二分法来查找这个值
 */
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        // 获取边界
        int min = Arrays.stream(weights).max().getAsInt();
        int max = Arrays.stream(weights).sum();
        int mid = 0;

        // 使用二分法查找
        while (min < max) {
            mid = (min + max) >> 1;
            if (currentDays(weights, mid) <= days) {
                // 如果计算出来的天数小于预期天数，说明运载能力太大，需要往左移
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return max;
    }

    // 计算最低运载能力 w 运载完所有获取需要多少天
    private int currentDays(int[] weights, int w) {
        // 某一天运载的货物总重量
        int totalWeight = 0;
        // 需要的天数，初始为一天
        int d = 1;
        for (int weight : weights) {
            totalWeight += weight;
            if (totalWeight > w) {
                totalWeight = weight;
                d++;
            }
        }
        return d;
    }
}
