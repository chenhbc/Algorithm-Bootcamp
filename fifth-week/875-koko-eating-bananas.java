/**
 * 最大速度为数组里最大值，即一次把这一堆吃完
 * 最小速度就按最小1来算，因为一次总要吃一根香蕉
 * 所以用二分法查找这个边界中的最小速度
 */
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // 确定边界
        int min = 1;
        int max = Arrays.stream(piles).max().getAsInt();

        int mid = 0;
        while (min < max) {
            mid = (min + max) >> 1;
            if (currentTime(piles, mid) <= h) {
                // 如果当前所需时间小于这个回来时间，则说明速度过快，需要左移
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return max;
    }

    private int currentTime(int[] piles, int speed) {
        // 所需小时数
        int hours = 0;
        for (int pile : piles) {
            // 当前这堆香蕉按照 speed 速度，计算需要吃几个小时，余数仍然需要花费一个小时去吃，所以向上取整
            hours += (pile + speed - 1) / speed;
            //hours += (int)Math.ceil((double) pile / speed);
        }
        return hours;
    }
}
