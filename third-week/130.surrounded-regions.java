class Solution {

    int rowCount, colCount;

    public void solve(char[][] board) {
        if (null == board || board.length == 0) return;

        rowCount = board.length;
        colCount = board[0].length;

        // 查找边界是否相连，是则从边界往上下左右进行 dfs，相连的O标记为-
        for(int row = 0; row < rowCount; row++){
            dfs(board, row, 0);
            dfs(board, row, colCount - 1);
        }
        for(int col = 0; col < colCount; col++){
            dfs(board, 0, col);
            dfs(board, rowCount - 1, col);
        }

        // 修改与边界不相连的O为X，与边界相连的-修改为O
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if(board[row][col] == 'O') board[row][col] = 'X';
                if(board[row][col] == '-') board[row][col] = 'O';
            }
        }
    }

    // 是否超出边缘
    private boolean beyongEge(int row, int col) {
        return row < 0 || col < 0 || row >= rowCount || col >= colCount;
    }

    // 深度优先遍历
    private void dfs(char[][] board, int row, int col) {
        // 边界判断，如果超出边缘，则不再继续递归
        if (beyongEge(row, col) || board[row][col] != 'O') return;

        board[row][col] = '-';
        // 往上下左右四个方向递归
        dfs(board, row + 1, col);
        dfs(board, row - 1, col);
        dfs(board, row, col + 1);
        dfs(board, row, col - 1);
    }
}
