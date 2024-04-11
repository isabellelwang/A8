import java.util.Scanner;

public class AnimalGuess {

    public void checkInput(Scanner s) {
        boolean valIn = false;
        try {
            if (input.toLowerCase().equals("yes") || input.toLowerCase().equals("y")) {
                System.out.println();

            }

        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid input");
        }

    }

    public static void main(String[] args) {
        DecisionTree left = new DecisionTree("Cat", new DecisionTree("Whale"), new DecisionTree("Rabbit"));
        DecisionTree right = new DecisionTree("Dog", new DecisionTree("Chicken"), new DecisionTree("Pig"));
        DecisionTree t = new DecisionTree("Rat", left, right);

        DecisionTree node = t;
        for (int i = 0; i < 1; i++) {
            // ask questions
            // user input

            // check input to see if valid

            // use input to move node
        }

    }
}