/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    // inorder[inLeft~inRight], postorder[postLeft~postRight]
    private TreeNode build(int[] inorder, int[] postorder, 
                        int inLeft, int inRight, int postLeft, int postRight) {
        if (inLeft > inRight) return null; 
        // 跟节点为后序遍历结果中的最后一个
        TreeNode root = new TreeNode(postorder[postRight]);
        // 根据根节点的值，从中序遍历结果中找出左右子节点的分隔点
        int inSplit = inLeft;
        // 如果中序结果中的值等于根节点的值，就说明已经找到分隔点了
        while(inSplit < inRight && root.val != inorder[inSplit]) inSplit++;

        // 中序遍历结果中根节点前后分隔后的长度
        int leftSize = inSplit - inLeft;

        // 递归还原左右子节点
        root.left = build(inorder, postorder, inLeft, inSplit - 1
                        , postLeft, postLeft + leftSize - 1);
        root.right = build(inorder, postorder, inSplit + 1, inRight
                        , postLeft + leftSize, postRight - 1);

        return root;
    }

/*
遍历规则：
中序：左根右
后序：左右根
所以根据后序遍历可知，最后一个肯定是根节点，然后根据根节点的值（树种没有重复的值），可以从中序遍历结果中得出左节点和右节点，如图所示：
    [3]
   /   \
  [9]  [15, 20, 7]
如此循环即可分别找出左右子节点 
*/
}
