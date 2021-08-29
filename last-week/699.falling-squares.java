class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        // 存放结果
        List<Integer> result = new ArrayList<>();
        if (null == positions || positions.length == 0) return result;

        // 当前方块的高度
        List<Integer> currentHeightList = new ArrayList<>();

        int maxHeight = positions[0][1];
        int height = positions[0][1];

        result.add(maxHeight);
        currentHeightList.add(height);

        for (int i = 1; i < positions.length; i++) {
            height = positions[i][1];
            for (int j = i - 1; j >= 0; j--) {
                // 判断当前方块能否放在之前的方块上
                if (!(positions[j][0] >= positions[i][0] + positions[i][1] || positions[j][0] + positions[j][1] <=  positions[i][0])) {
                    // 获取最大高度
                    int tmp = currentHeightList.get(j) + positions[i][1];
                    if (tmp > height) height = tmp;
                }
            }

            currentHeightList.add(height);
            if (height > maxHeight) maxHeight = height;
            result.add(maxHeight);
        }
        return result;
    }
}
