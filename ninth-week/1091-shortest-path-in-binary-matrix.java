class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        // 因为这是一个 n * n 的矩阵，所以行和列都是 n
        int n = grid.length;
        // 边界判断
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1;

        // 方向数组：上，右，下，左，右上，右下，左下，左上
        int[] diretX = { 0, 1, 0, -1, 1, 1,-1, -1};
        int[] diretY = {-1, 0, 1,  0,-1, 1, 1, -1};

        // 用队列来放路径
        Queue<Node> queue = new LinkedList<>();
        // 第一个元素也就是入口 {0, 0}
        queue.offer(new Node(0, 0));

        int ans = 0;
        while (!queue.isEmpty()) {
            ++ans; // 记录步数

            // 这里 i 从大到小是因为，在循环里 queue 会 pop，size 会变化
            for (int i = queue.size(); i >= 1; --i) {
                // 使用 Queue 的 remove 或 poll 方法
                Node node = queue.poll();
                int x = node.x, y = node.y;
                
                if (x == n - 1 && y == n - 1) return ans; // 判断是否到达最后一个单元格，是则返回

                // 8 个方向移动
                for (int d = 0; d < 8; d++) {
                    int nx = x + diretX[d];
                    int ny = y + diretY[d];
                    
                    // 判断边界，移动之后的位置是否在矩阵中，而且当前单元格中的数为 0 才能走
                    if (nx >= 0 && ny >= 0 && nx < n && ny < n && grid[nx][ny] == 0) {
                        // 访问过之后把该位置设为 1，避免重复访问，或者用一个 visited 数组记录也可以
                        grid[nx][ny] = 1;
                        // 使用 Queue 的 offer 或 add 方法
                        queue.offer(new Node(nx, ny));
                    }
                }
            }
        }
        return -1;
    }
}
class Node {
    int x;
    int y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
