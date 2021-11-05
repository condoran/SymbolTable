import java.awt.*;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class FA {
    private List<String> states;
    private List<String> alphabet;
    private List<String> transitions;
    private List<String> finalStates;

    public FA (String path)
    {
        readFa(path);
    }

    private void readFa(String path) {
        java.util.Scanner reader = new java.util.Scanner(path);

        String statesString = reader.nextLine();
        String[] statesArray = statesString.split("\\s+");
        for (int i = 0; i < statesArray.length; i++) {
            states.add(statesArray[i]);
        }
        String alphabetString = reader.nextLine();
        String[] alphabetArray = alphabetString.split("\\s+");
        for (int i = 0; i < alphabetArray.length; i++) {
            alphabet.add(alphabetArray[i]);
        }
        String transitionsString = reader.nextLine();
        String[] transitionsArray = transitionsString.split("\\s+");
        for (int i = 0; i < transitionsArray.length; i++) {
            transitions.add(transitionsArray[i]);
        }
        String finalStatesString = reader.nextLine();
        String[] finalStatesArray = finalStatesString.split("\\s+");
        for (int i = 0; i < finalStatesArray.length; i++) {
            finalStates.add(finalStatesArray[i]);
        }
    }

    private void displayStates() {
        System.out.println("States:");
        for (String state : states) {
            System.out.print(state + " ");
        }
        System.out.println();
    }

    private void displayAlphabet() {
        System.out.println("Alphabet:");
        for (String alphab : alphabet) {
            System.out.print(alphab + " ");
        }
        System.out.println();
    }

    private void displayTransitions() {
        System.out.println("Transitions:");
        for (String tansition : transitions) {
            System.out.print(tansition + " ");
        }
        System.out.println();
    }

    private void displayFinalStates() {
        System.out.println("Final States:");
        for (String finalState : finalStates) {
            System.out.print(finalState + " ");
        }
        System.out.println();
    }

    public void menuLoop()
    {
        java.util.Scanner scanner = new java.util.Scanner(new InputStreamReader(System.in));
        int option;
        while (true)
        {
            System.out.println("1.Display the states");
            System.out.println("2.Display the alphabet");
            System.out.println("3.Display the transitions");
            System.out.println("4.Display the final states");
            option = scanner.nextInt();
            if (option == 1)
            {
                displayStates();
            }
            else if (option == 2)
            {
                displayAlphabet();
            }
            else if (option == 3)
            {
                displayTransitions();
            }
            else if (option == 4)
            {
                displayFinalStates();
            }
            else
            {
                System.out.println("Incorrect input!");
            }
        }
    }
}
