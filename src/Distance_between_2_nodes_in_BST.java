

 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

 class Solution {
    public int bstDistance(TreeNode root, int node1, int node2) {
        if (root == null) return -1;
        TreeNode lca = lca(root, node1, node2);
        return getDistance(lca, node1) + getDistance(lca, node2);
    }

    private int getDistance(TreeNode src, int dest) {
        if (src.val == dest) return 0;
        TreeNode node = src.val < dest ? src.right : src.left;
        return 1 + getDistance(node, dest);
    }

    private TreeNode lca(TreeNode root, int node1, int node2) {
        while (true) {
            if (root.val > node1 && root.val > node2) {
                root = root.left;
            } else if (root.val < node1 && root.val < node2) {
                root = root.right;
            } else {
                return root;
            }
        }
    }
}

 public class Distance_between_2_nodes_in_BST {
    public static void main(String[] args) {
        // Construct the BST from the example
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(1);
        root.right.right.right = new TreeNode(8);

        // Create an instance of the solution class
        Solution solution = new Solution();

        // Find and print the distance between node 1 and node 4
        int distance1to4 = solution.bstDistance(root, 1, 4);
        System.out.println("Distance between 1 and 4: " + distance1to4);

        // Find and print the distance between node 1 and node 8
        int distance1to8 = solution.bstDistance(root, 1, 8);
        System.out.println("Distance between 1 and 8: " + distance1to8);
    }
}
