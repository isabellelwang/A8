import java.util.*;

public class DecisionTree extends BinaryTree<String> {

    private String data;

    /** Left child of this node */
    private DecisionTree left;

    /** Right child of this node */
    private DecisionTree right;

    public DecisionTree(String data) {
        super(data);
    }

    public DecisionTree(String data, DecisionTree left, DecisionTree right) {
        super(data, left, right);
    }

    public static <T> String preorderString(DecisionTree t) {
        if (t == null) {
            return "";
        } else {
            return "(" + t.data + " " + preorderString(t.left) + " " + preorderString(t.right) + ")";
        }
    }

    public static <T> String inorderString(DecisionTree t) {
        if (t == null) {
            return "";
        } else {
            return "(" + inorderString(t.left) + " " + t.data + " " + inorderString(t.right) + ")";
        }
    }

    public static <T> String postorderString(DecisionTree t) {
        if (t == null) {
            return "";
        } else {
            return "(" + postorderString(t.left) + " " + postorderString(t.right) + " " + t.data + ")";
        }
    }

    public String followPath(String path) {
        DecisionTree node = this;

        for (char letter : path.toCharArray()) {
            if (letter == 'Y') {
                node = (DecisionTree) node.getLeft();
            } else if (letter == 'N') {
                node = (DecisionTree) node.getRight();
            }
        }
        return node.getData();
    }

    public static void main(String[] args) {
        DecisionTree left = new DecisionTree("Cat", new DecisionTree("Whale"), new DecisionTree("Rabbit"));
        DecisionTree right = new DecisionTree("Dog", new DecisionTree("Chicken"), new DecisionTree("Pig"));
        DecisionTree t = new DecisionTree("Rat", left, right);
        System.out.println(t);
        System.out.println(t.getData()); // return node

        // System.out.println(t.getLeft());
        // // t.setRight(new DecisionTree("moose"));
        // System.out.println(t.getRight());
        // System.out.println(t);
        // System.out.println(BinaryTree.isEmpty(t));
        // System.out.println(t.isBranch());
        // System.out.println(t.isLeaf());
        // System.out.println("Preorder" + BinaryTree.preorderString(t));
        // System.out.println("Postorder" + BinaryTree.postorderString(t));

        System.out.println(t);
        System.out.println(t.followPath("YN"));

    }

}