class Solution {

    int[] fa;
    int count = 0;

    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int n = row * col;
        fa = new int[n];

        // 建立并查集
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    fa[i * col + j] = i * col + j;
                    count++;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    if (i + 1 < row && grid[i + 1][j] == '1') 
                        unionSet(i * col + j, (i + 1) * col + j);
                    if (j + 1 < col && grid[i][j + 1] == '1')
                        unionSet(i * col + j, i * col + j + 1);
                }
            }
        }
        
        return count;
    }

    private void unionSet(int i, int j) {
        int x = find(i);
        int y = find(j);
        if (x != y) fa[x] = y;
        else return;
        count--;
    }

    private int find(int x) {
        if (x == fa[x]) return x;
        return fa[x] = find(fa[x]);
    }
}
