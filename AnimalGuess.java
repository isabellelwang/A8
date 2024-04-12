import java.util.Scanner;

public class AnimalGuess {
    DecisionTree<String> t;

    public AnimalGuess() {
        t = new DecisionTree<String>(null, null, null);
    }

    public static boolean checkInput(String input) {
        if (input.toLowerCase().equals("yes") || input.toLowerCase().equals("y")) {
            return true;
        } else if (input.toLowerCase().equals("no") || input.toLowerCase().equals("n")) {
            return false;
        }
    }

    public static void addNode(String leaf, DecisionTree<String> t) {
        Scanner input = new Scanner(System.in);
        String animal;
        System.out.println("I got it wrong. \nPlease help me learn. ");
        System.out.println("What was your animal?");
        animal = input.nextLine();

        input.nextLine();
        System.out.println("Type a question that would distinguish between a " + animal + " to a " + leaf);
        String question = input.nextLine();

        input.nextLine();
        System.out.println("Would you answer yes to this question for the  " + animal + "?");
        String response = input.nextLine();

        if (checkInput(response)) {
            


        }

    }

    public static void main(String[] args) {
        AnimalGuess ag = new AnimalGuess();
        DecisionTree<String> left = new DecisionTree<>("Bear");
        DecisionTree<String> right = new DecisionTree<>("Moose");
        DecisionTree<String> t = new DecisionTree<>("Is it big?", left, right);

        DecisionTree<String> currentNode = t;
        System.out.println("Think of an animal. \nI'll try to guess it.");
        Scanner input = new Scanner(System.in);
        String in;

        while (!currentNode.isLeaf()) {
            System.out.println(currentNode.getData());
            in = input.nextLine();

            if (in.toLowerCase().equals("y") || in.toLowerCase().equals("yes")) {
                currentNode = (DecisionTree<String>) currentNode.getRight();
            } else if (in.toLowerCase().equals("n") || in.toLowerCase().equals("no")) {
                currentNode = (DecisionTree<String>) currentNode.getLeft();
            }
        }

        System.out.println("Is your animal a " + currentNode.getData() + "?");
        input.nextLine();
        in = input.nextLine();

        if (in.toLowerCase().equals("n") || in.toLowerCase().equals("no")) {
            addNode(currentNode.getData(), t);
        } else {
            System.out.println("We guessed it!");
        }

    }
}

// check input

// ask questions
// user input

// check input to see if valid

// use input to move node
