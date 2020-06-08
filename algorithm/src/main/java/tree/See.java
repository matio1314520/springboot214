package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * @author mawt
 * @description
 * @date 2020/6/8
 */
public class See {

    private static TreeNode root;

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            buildTree(random.nextInt(100));
        }
    }

    private static void preorderTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + "\t");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }

    private static void preorderTraversal2(TreeNode root) {
        System.out.println("不使用递归的方式前序遍历二叉树:根节点 左子树 右子树");
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.print(node.val + "\t");
                stack.add(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop().right;
            }
        }
    }

    private static void inorderTraversal(TreeNode root) {
        if (root != null) {
            preorderTraversal(root.left);
            System.out.print(root.val + "\t");
            preorderTraversal(root.right);
        }
    }

    private static void inorderTraversal2(TreeNode root) {
        System.out.println("不使用递归的方式中序遍历二叉树:左子树 根节点 右子树");
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.val + "\t");
                node = node.right;
            }
        }
    }

    //递归计算节点个数
    private static int getNodeNumRec(TreeNode root) {
        return root == null ? 0 : (getNodeNumRec(root.left) + getNodeNumRec(root.left) + 1);
    }

    private static int getNodeNumRec2(TreeNode root) {
        System.out.println("不使用递归的方式计算二叉树节点个数");
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>(); // 用队列保存树节点，先进先出
        queue.add(root);
        int count = 1; // 节点数量
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left != null) { // 添加左子孩子到对列
                queue.add(node.left);
                count++;
            }
            if (node.right != null) { // 添加右子孩子到对列
                queue.add(node.right);
                count++;
            }
        }
        return count;
    }

    //递归计算二叉树的深度
    public static int getMaxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = getMaxDepth(root.left);
        int rightDepth = getMaxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    //计算二叉树中叶子节点的个数
    public static int getNodeNumLeafRec(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return Math.max(getNodeNumLeafRec(root.left), getNodeNumLeafRec(root.right));
    }

    public static int getNodeNumLeafRec2(TreeNode root) {
        if (root == null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left == null && node.right == null) count++;
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return count;
    }

    //判断两棵二叉树是否相同的树
    public static boolean isSameRec(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) return true;
        if (r1 == null || r2 == null) return false;
        if (r1.val != r2.val) return false;
        return isSameRec(r1.left, r2.left) && isSameRec(r1.right, r2.right);
    }

    //判断是否是AVL树
    public static boolean isAVLTree(TreeNode root) {
        if (root == null) return true;
        if (Math.abs(getMaxDepth(root.left) - getMaxDepth(root.right)) > 1) return false;
        return isAVLTree(root.left) && isAVLTree(root.right);
    }

    private static void buildTree(int val) {
        if (root != null) {
            TreeNode node = root, parent = root;
            while (node != null) {
                if (node.val == val) return;
                parent = node;
                node = node.val > val ? node.left : node.right;
            }
            if (node.val > val) {
                parent.left = new TreeNode(val);
            } else {
                parent.right = new TreeNode(val);
            }
        } else {
            root = new TreeNode(val);
        }
    }


}
