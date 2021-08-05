class Solution {
    int[] fa;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        fa = new int[n + 1];

        // 建立并查集
        for (int i = 1; i < n; i++)
            fa[i] = i;

        for (int i = 0; i < n; i++) {
            if (find(edges[i][0]) != find(edges[i][1])) unionSet(edges[i][0], edges[i][1]);
            else return edges[i];
        }
        return null;
    }

    private void unionSet(int i, int j) {
        int x = find(i);
        int y = find(j);
        if (x != y) fa[x] = y;
    }

    private int find(int x) {
        if (x == fa[x]) return x;
        return fa[x] = find(fa[x]);
    }
}
