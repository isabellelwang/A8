import java.util.*;

public class DecisionTree<E> extends BinaryTree<E> {

    private String data;

    /** Left child of this node */
    private DecisionTree<E> left;

    /** Right child of this node */
    private DecisionTree<E> right;

    public DecisionTree(E data) {
        super(data);
    }

    public DecisionTree(E data, DecisionTree<E> left, DecisionTree<E> right) {
        super(data, left, right);
    }

    public static <T> String preorderString(DecisionTree<T> t) {
        if (t == null) {
            return "";
        } else {
            return "(" + t.data + " " + preorderString(t.left) + " " + preorderString(t.right) + ")";
        }
    }

    public static <T> String inorderString(DecisionTree<T> t) {
        if (t == null) {
            return "";
        } else {
            return "(" + inorderString(t.left) + " " + t.data + " " + inorderString(t.right) + ")";
        }
    }

    public static <T> String postorderString(DecisionTree<T> t) {
        if (t == null) {
            return "";
        } else {
            return "(" + postorderString(t.left) + " " + postorderString(t.right) + " " + t.data + ")";
        }
    }

    public E followPath(String path) {
        DecisionTree<E> node = this;

        for (char letter : path.toCharArray()) {
            if (letter == 'Y') {
                node = (DecisionTree<E>) node.getLeft();
            } else if (letter == 'N') {
                node = (DecisionTree<E>) node.getRight();
            }
        }
        return node.getData();
    }

    public static void main(String[] args) {
        DecisionTree<String> left = new DecisionTree<String>("Cat", new DecisionTree<String>("Whale"),
                new DecisionTree<String>("Rabbit"));
        DecisionTree<String> right = new DecisionTree<String>("Dog", new DecisionTree<String>("Chicken"),
                new DecisionTree<String>("Pig"));
        DecisionTree<String> t = new DecisionTree<>("Rat", left, right);
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