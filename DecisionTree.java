import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DecisionTree<E> extends BinaryTree<E> {
    /** The value at this node */
    private E data;

    /** Left child of this node */
    private DecisionTree<E> left;

    /** Right child of this node */
    private DecisionTree<E> right;

    /**
     * constructs a DecisonTree
     * 
     * @param data
     */
    public DecisionTree(E data) {
        super(data);
    }

    /**
     * constructs DecisionTree
     * 
     * @param data  E data
     * @param left  tree left
     * @param right right tree node
     */
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
        return (DecisionTree<E>) super.getLeft();
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

    /** Determines whether this tree is a leaf */
    public boolean isLeaf() {
        return super.isLeaf(); // (this.left == null) && (this.right == null);
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

    /** Determines whether a tree is empty */
    public boolean isEmpty(DecisionTree<E> node) {
        return (node == null);
    }

    /**
     * returns node at the end of path
     * 
     * @param path String path of the tree
     * @return node at the end of the path
     */
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

    /**
     * Writes tree into the file
     * 
     * @param fileName fileName to write the tree information
     */
    public void writeTree(String fileName) {
        try {
            ArrayDeque<DecisionTree<E>> treeQueue = new ArrayDeque<DecisionTree<E>>();
            treeQueue.add(this); // add node
            ArrayDeque<String> pathQueue = new ArrayDeque<String>();
            String path = ""; // add indent to path for node
            pathQueue.add(path);

            PrintWriter out = new PrintWriter(new FileWriter(fileName));

            while (!treeQueue.isEmpty()) {
                String copyPath = pathQueue.peekFirst();
                String pathHead = pathQueue.pollFirst();
                DecisionTree<E> currentNode = treeQueue.pollFirst();

                out.println(pathHead + " " + currentNode.getData());

                if (!currentNode.isEmpty(currentNode.getLeft())) {
                    String newPath = copyPath + "Y";
                    pathQueue.add(newPath);
                    treeQueue.add(currentNode.getLeft());
                }
                if (!currentNode.isEmpty(currentNode.getRight())) {
                    String newPath = copyPath + "N";
                    pathQueue.add(newPath);
                    treeQueue.add(currentNode.getRight());
                }
            }

            out.close();
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    public static void main(String[] args) {
        DecisionTree<String> left = new DecisionTree<String>("Cat", new DecisionTree<String>("Whale"),
                new DecisionTree<String>("Rabbit"));
        DecisionTree<String> right = new DecisionTree<String>("Dog", new DecisionTree<String>("Chicken"),
                new DecisionTree<String>("Pig"));
        DecisionTree<String> t = new DecisionTree<>("Rat", left, right);
        // System.out.println(t);
        // System.out.println(t.getData()); // return node
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
        // System.out.println(t.followPath("YN"));

        t.writeTree("TestFile.txt");

    }

}