import java.util.Scanner;

public class AnimalGuess {

    public static boolean checkInput(String input) {
        boolean isValid = false;
        if (input.toLowerCase().equals("yes") || input.toLowerCase().equals("y")) {
            isValid = true;
        } else if (input.toLowerCase().equals("no") || input.toLowerCase().equals("n")) {
            isValid = true;
        }

        return isValid;

    }

    public static void makeNewNode(DecisionTree<String> currentNode, String path,
            DecisionTree<String> t) {
        Scanner input = new Scanner(System.in);
        // DecisionTree<String> oldAnimal = currentNode;
        System.out.println("I got it wrong. \nPlease help me learn. ");
        System.out.println("What was your animal?");
        String animal = input.nextLine();

        System.out.println("Type a question that would distinguish between a " +
                animal + " to a " + currentNode.getData());
        String question = input.nextLine();

        System.out.println("Would you answer yes to this question for the " + animal
                + "?");
        String response = input.nextLine();

        System.out.println(path);
        DecisionTree<String> addTree = new DecisionTree<>(question);
        DecisionTree<String> animalTree = new DecisionTree<>(animal);

        if (checkInput(response)) {
            if (response.toLowerCase().equals("yes") ||
                    response.toLowerCase().equals("y")) {
                addTree.setRight(currentNode);
                addTree.setLeft(animalTree);

            } else if (response.toLowerCase().equals("no") ||
                    response.toLowerCase().equals("n")) {
                addTree.setLeft(currentNode);
                addTree.setRight(animalTree);
            }
        }

        DecisionTree<String> leaf;
        if (t.count() > 1) {
            leaf = t.followPath(path.substring(0, path.length() - 1));
            System.out.println(path.substring(0, path.length() - 1));

            if (path.charAt(path.length() - 1) == 'Y') {
                leaf.setLeft(addTree);
            } else {
                leaf.setRight(addTree);
            }
        } else {
            leaf = t.followPath("");
            leaf.setData(question);
            if (response.toLowerCase().equals("yes") ||
                    response.toLowerCase().equals("y")) {
                leaf.setLeft(animalTree);
                leaf.setRight(currentNode);

            } else if (response.toLowerCase().equals("no") ||
                    response.toLowerCase().equals("n")) {
                leaf.setLeft(currentNode);
                leaf.setRight(animalTree);
            }
        }

        // addNodes(path, addTree, t);

        input.close();
    }

    public static void addNodes(String path, DecisionTree<String> newNode,
            DecisionTree<String> origTree) {

        // DecisionTree<String> leaf = origTree.followPath(path);
        // System.out.println(path);

        // System.out.println("new node: " + newNode.getData());
        // System.out.println(newNode.getRight().getData());
        // leaf.setRight(newNode.getRight());
        // System.out.println(leaf.getRight().getData());

        // System.out.println("add Nodes leaf: " + leaf.getData());

        // // System.out.println("count" + newNode.count());
        // leaf.setData(newNode.getData());
        // leaf.setLeft(newNode.getLeft());
        // System.out.println(leaf.getLeft().getData());

        // System.out.println(origTree);
    }

    public static void makePath(String in, StringBuilder path) {
        if (in.toLowerCase().equals("y") || in.toLowerCase().equals("yes")) {
            path.append("Y");
        } else if (in.toLowerCase().equals("n") || in.toLowerCase().equals("no")) {
            path.append("N");
        }
    }

    public static void main(String[] args) {
        DecisionTree<String> right = new DecisionTree<>("Rat");
        DecisionTree<String> left = new DecisionTree<>("Moose");
        DecisionTree<String> t = new DecisionTree<String>(right.getData());
        //DecisionTree<String> t = new DecisionTree<>("Is it big?", left, right);

        StringBuilder path = new StringBuilder();

        DecisionTree<String> currentNode = t;
        System.out.println("Think of an animal. \nI'll try to guess it.");
        Scanner input = new Scanner(System.in);
        String in;

        while (!currentNode.isLeaf()) {
            System.out.println(currentNode.getData());
            in = input.nextLine();

            if (in.toLowerCase().equals("y") || in.toLowerCase().equals("yes")) {
                path.append("Y");
                currentNode = (DecisionTree<String>) currentNode.getLeft();
            } else if (in.toLowerCase().equals("n") || in.toLowerCase().equals("no")) {
                currentNode = (DecisionTree<String>) currentNode.getRight();
                path.append("N");
            }
        }

        System.out.println("Is your animal a " + currentNode.getData() + "?");

        in = input.nextLine();

        System.out.println(in);
        System.out.println(path);

        if (in.toLowerCase().equals("n") || in.toLowerCase().equals("no")) {

            System.out.println(path.toString());
            makeNewNode(currentNode, path.toString(), t);

            System.out.println("Root: " + t.getData());
            System.out.println("second level left" + t.getLeft().getData());
            System.out.println("second level right" + t.getRight().getData());
            // System.out.println("third level left left " +
            // t.getLeft().getLeft().getData());
            // System.out.println("third level left right" +
            // t.getLeft().getRight().getData());
            // System.out.println("third level right left " +
            //         t.getRight().getLeft().getData());
            // System.out.println("third level right right " +
            //         t.getRight().getRight().getData());
            // System.out.println(currentNode.getData());
            // System.out.println(currentNode.getRight().getData());
            // System.out.println(currentNode.getLeft().getData());

        } else {
            System.out.println("I guessed it!");
        }

        // ask play again?
        System.out.println();
        input.close();

    }
}