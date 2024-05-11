import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AnimalGuess {
    // yes on the left, no on the right

    /**
     * write tree into files
     * 
     * @param fileName  fileName blank file to be put into
     * @param blankTree tree to make
     */
    public static void makeTree(String fileName, DecisionTree<String> blankTree) {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            String root = reader.nextLine().substring(1);
            DecisionTree<String> currentNode = new DecisionTree<String>(root);
            blankTree.setData(currentNode.getData());
            // System.out.println(blankTree.getData());

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String nodeData = data.substring(data.indexOf(" ") + 1);

                String path = data.substring(0, data.indexOf(" "));
                String parentPath = path.substring(0, path.length() - 1);
                String lastPath = path.substring(path.length() - 1);
                DecisionTree<String> addedNode = new DecisionTree<String>(nodeData);
                DecisionTree<String> parentNode = blankTree.followPath(parentPath);

                if (lastPath.equals("N")) {
                    parentNode.setRight(addedNode);

                } else if (lastPath.equals("Y")) {
                    parentNode.setLeft(addedNode);
                }

            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        }
    }

    /**
     * Checks input
     * 
     * @param input String input
     * @return boolean true or false if input is true
     */
    public static boolean checkInput(String input) {
        boolean isValid = false;
        if (input.toLowerCase().equals("yes") || input.toLowerCase().equals("y")) {
            isValid = true;
        } else if (input.toLowerCase().equals("no") || input.toLowerCase().equals("n")) {
            isValid = true;
            System.out.println("Invalid input: type 'yes' or 'y' or 'n' or 'n'");
            isValid = false;
        }

        return isValid;

    }

    /**
     * Makes a new node with the question and right and left nodes
     * 
     * @param currentNode currentNode of the decisionTree
     * @param path        String path of the node
     * @param t           t original tree
     * @param input1      scanner input of the response
     */
    public static void makeNewNode(DecisionTree<String> currentNode, String path,
            DecisionTree<String> t, Scanner input1) {
        System.out.println("I got it wrong. \nPlease help me learn. \nWhat was your animal?");
        String animal = input1.nextLine();

        System.out.println("Type a question that would distinguish between a " +
                animal + " to a " + currentNode.getData());
        String question = input1.nextLine();

        System.out.println("Would you answer yes to this question for the " + animal
                + "?");
        String response = input1.nextLine();

        DecisionTree<String> addTree = new DecisionTree<>(question);
        DecisionTree<String> animalTree = new DecisionTree<>(animal);

        if (response.toLowerCase().equals("yes") ||
                response.toLowerCase().equals("y")) {
            addTree.setRight(currentNode);
            addTree.setLeft(animalTree);

        } else if (response.toLowerCase().equals("no") ||
                response.toLowerCase().equals("n")) {
            addTree.setLeft(currentNode);
            addTree.setRight(animalTree);
        }

        DecisionTree<String> leaf;
        if (t.count() > 1) {
            leaf = t.followPath(path.substring(0, path.length() - 1));
            // System.out.println(leaf);
            // System.out.println("leaf" + leaf);
            // System.out.println("Path" + path.substring(path.length() - 1));
            // System.out.println("Path" + path);

            if (path.charAt(path.length() - 1) == 'Y') {
                leaf.setLeft(addTree);

            } else {
                leaf.setRight(addTree);
            }
        } else {
            DecisionTree<String> oldAnimal = new DecisionTree<>(currentNode);
            // System.out.println(currentNode);

            leaf = t.followPath(path);
            leaf.setData(question);
            if (response.toLowerCase().equals("yes") ||
                    response.toLowerCase().equals("y")) {
                leaf.setLeft(animalTree);
                leaf.setRight(oldAnimal);

            } else if (response.toLowerCase().equals("no") ||
                    response.toLowerCase().equals("n")) {
                leaf.setLeft(oldAnimal);
                leaf.setRight(animalTree);
            }
        }
        // System.out.println(t);
    }

    /**
     * Makes path depending on yes or no
     * 
     * @param in   String response
     * @param path StringBuilder path
     */
    public static void makePath(String in, StringBuilder path) {
        if (in.toLowerCase().equals("y") || in.toLowerCase().equals("yes")) {
            path.append("Y");
        } else if (in.toLowerCase().equals("n") || in.toLowerCase().equals("no")) {
            path.append("N");
        }
    }

    // main
    public static void main(String[] args) {
        DecisionTree<String> t = new DecisionTree<String>("");
        AnimalGuess.makeTree("AnimalTree.txt", t);

        // DecisionTree<String> right = new DecisionTree<>("Rat");
        // DecisionTree<String> left = new DecisionTree<>("Moose");
        // // DecisionTree<String> t = new DecisionTree<String>(right.getData());
        // DecisionTree<String> t = new DecisionTree<>("Is it big?", left, right);

        StringBuilder path = new StringBuilder();

        DecisionTree<String> currentNode = t;
        System.out.println("Think of an animal. \nI'll try to guess it.");
        Scanner input = new Scanner(System.in);
        String in;

        String playAgain = "yes";
        while (playAgain.toLowerCase().equals("yes") || playAgain.toLowerCase().equals("y")) {
            currentNode = t.followPath("");

            while (!currentNode.isLeaf()) {
                System.out.println(currentNode.getData());
                in = input.next();

                // Adding to the tree
                if (in.toLowerCase().equals("y") || in.toLowerCase().equals("yes")) {
                    path.append("Y");
                    currentNode = (DecisionTree<String>) currentNode.getLeft();
                } else if (in.toLowerCase().equals("n") || in.toLowerCase().equals("no")) {
                    currentNode = (DecisionTree<String>) currentNode.getRight();
                    path.append("N");
                }
            }

            input.nextLine();
            if (!currentNode.getData().contains(" ")) {
                System.out.println("Is your animal a " + currentNode.getData() + "?");

            } else {
                System.out.println(currentNode.getData());
            }

            in = input.nextLine();

            if (in.toLowerCase().equals("n") || in.toLowerCase().equals("no")) {
                // path.append("N");

                makeNewNode(currentNode, path.toString(), t, input);

                // testing
                // System.out.println("Root: " + t.getData());
                // System.out.println("second level left" + t.getLeft().getData());
                // System.out.println("second level right" + t.getRight().getData());
                // System.out.println("third level left left " +
                // t.getLeft().getLeft().getData());
                // System.out.println("third level left right" +
                // t.getLeft().getRight().getData());
                // System.out.println("third level right left " +
                // t.getRight().getLeft().getData());
                // System.out.println("third level right right " +
                // t.getRight().getRight().getData());
                // System.out.println(in);

            } else {
                // path.append("Y");
                System.out.println("I guessed it!");
            }

            // ask play again?
            System.out.println("Would you like to play again?");
            playAgain = input.nextLine();

            t.writeTree("TestFile.txt");
        }
        input.close();
    }
}