import java.util.*;

public class DecisionTree<E> extends BinaryTree<E> {
    /** The value at this node */
    private E data;

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

    /** This constructor creates a deep copy of the entire tree structure */
    public DecisionTree(DecisionTree<E> tree) {
        super(tree);
    }

    /** Accessor for node data */
    public E getData() {
        return super.getData(); 
    }

    /** Accessor for left child */
    public DecisionTree<E> getLeft() {
        return (DecisionTree<E>)super.getLeft();
    }

    /** Accessor for right child */
    public DecisionTree<E> getRight() {
        return (DecisionTree<E>) super.getRight();
    }

    /** Manipulator for node data */
    public void setData(E data) {
        super.setData(data); 
    }

    /** Manipulator for left child */
    public void setLeft(DecisionTree<E> left) {
        super.setLeft(left); 
    }

    /** Manipulator for right child */
    public void setRight(DecisionTree<E> right) {
        super.setRight(right); 
    }

    // /** Determines whether a tree is empty */
    // public static boolean isEmpty(DecisionTree node) {
    //     return isEmpty(node); 
    // }

    /** Determines whether this tree is a leaf */
    public boolean isLeaf() {
        return super.isLeaf(); //(this.left == null) && (this.right == null);
    }

    /** Determines whether this tree is a branch */
    public boolean isBranch() {
        return super.isBranch(); 
    }

    /** Counts the number of nodes */
    public int count() {
        return super.count(); 
    }

    /** Computes the height of the tree */
    public int height() {
        return super.height(); 
    }

    /** Creates a string representation */
    public String toString() {
        return super.toString(); 
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

    public DecisionTree<E> followPath(String path) {
        DecisionTree<E> node = this;

        for (char letter : path.toCharArray()) {
            if (letter == 'Y') {
                node = (DecisionTree<E>) node.getLeft();

            } else if (letter == 'N') {
                node = (DecisionTree<E>) node.getRight();
                
            }
        }
        return node;
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