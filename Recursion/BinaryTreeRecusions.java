package Recursion;

public class BinaryTreeRecusions {

    // Subtrees of a tree is a tree. it is a recursive data structure which means it can be handled with a recusrive
    // approach

    // to get a summ of element of a binary tree -> calculate sum of its root plus left subtree sum and right sub tree
    // sum

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree(1);
        BinaryTree left = new BinaryTree(2);
        BinaryTree right = new BinaryTree(3);
        BinaryTree leftLeft = new BinaryTree(4);
        BinaryTree leftRight = new BinaryTree(5);
        BinaryTree rightRight = new BinaryTree(6);
        BinaryTree rightLeft = new BinaryTree(1);
        BinaryTree rightLeftLeft = new BinaryTree(1);
        
        binaryTree.setLeft(left);
        binaryTree.setRight(right);
        
        left.setLeft(leftLeft);
        left.setRight(leftRight);
        
        right.setLeft(rightLeft);
        right.setRight(rightRight);
        
        rightRight.setLeft(rightLeftLeft);

//        1
//      /   \
//     2     3
//    / \   / \
//   4   5 1   6
//            /
//           1

        System.out.println(treeSum(binaryTree));
        System.out.println(treeMax(binaryTree));
        System.out.println(treeHeight(binaryTree));
        System.out.println(existsInTree(binaryTree, 21));

    }

////////////////////////////////////////////////////////////////////////////////////////////////////    
    // TREESUM
    public static int treeSum(BinaryTree root) {
        // 1. Find one ore more base cases
        if (root == null) {
            return 0;
        } else {
        // 2 call the same function on the left sub tree
            int leftSum = treeSum(root.left);
        // 3 call the same function on the right sub tree
            int rightSum = treeSum(root.right);
        // 4. join the results
            return root.data + leftSum + rightSum;
        }

    }

////////////////////////////////////////////////////////////////////////////////////////////////////    
    // TREE MAX VALUE
    public static int treeMax(BinaryTree root) {
        if(root == null) {
            return Integer.MIN_VALUE;
        }
        int maxLeft = treeMax(root.left);
        int maxRight = treeMax(root.right);
        if(maxRight > maxLeft) {
            return Math.max(root.data, maxRight);
        }
        return Math.max(root.data, maxLeft);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////    
    // TREE HEIGHT
    public static int treeHeight(BinaryTree root) {
        if(root == null) {
            return 0;
        }
        int rightHeight = treeHeight(root.right);
        int leftHeight = treeHeight(root.left);
        return 1+Math.max(rightHeight, leftHeight);
        
    }

////////////////////////////////////////////////////////////////////////////////////////////////////    
    // EXISTS IN TREE
    public static boolean existsInTree(BinaryTree root, int value) {
        if(root == null) {
            return false;
        }
        boolean existsInLeft = existsInTree(root.left, value);
        boolean existsInRight = existsInTree(root.right, value);
        return root.data == value || existsInLeft || existsInRight;
        
    }

////////////////////////////////////////////////////////////////////////////////////////////////////    
    // REVERSE IN TREE
    private static void reverseTree(BinaryTree root) {
        if(root == null) {
            return;
        }
        reverseTree(root.left);
        reverseTree(root.right);
        BinaryTree temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
 
    private static class BinaryTree {
        int data;
        BinaryTree left, right;

        public BinaryTree() {
        }
        public BinaryTree(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public BinaryTree getLeft() {
            return left;
        }

        public void setLeft(BinaryTree left) {
            this.left = left;
        }

        public BinaryTree getRight() {
            return right;
        }

        public void setRight(BinaryTree right) {
            this.right = right;
        }
    }

}
