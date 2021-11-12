import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FA {
    private List<String> states = new ArrayList<>();
    private List<String> alphabet = new ArrayList<>();
    private List<String> transitions = new ArrayList<>();
    private List<String> finalStates = new ArrayList<>();
    private String start;

    public FA (String path) throws FileNotFoundException {
        readFa(path);
    }

    private void readFa(String path) throws FileNotFoundException {
        File faFile = new File(path);
        java.util.Scanner reader = new java.util.Scanner(faFile);

        String startString = reader.nextLine();
        String[] startArray = startString.split("\\s+");
        start = startArray[0];

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
        String[] transitionsArray = transitionsString.split(",");
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
            if (isDfa())
                System.out.println("5.Verify if a sequence is accepted by the FA");
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
            else if (option == 5 && isDfa())
            {
                scanner.nextLine();
                String seq = scanner.nextLine();
                if (acceptedSequence(seq))
                {
                    System.out.println("Accepted sequence!");
                }
                else
                {
                    System.out.println("Not an accepted sequence!");
                }
            }
            else
            {
                System.out.println("Incorrect input!");
            }
        }
    }

    boolean isDfa()
    {
        if (alphabet.contains("eps"))
            return false;
        for (String transition : transitions) {
            String[] transitionsArray = transition.split("\\s+");
            List<String> alphas = new ArrayList<>();
            for (String transition2 : transitions) {
                if (transition2.contains(transitionsArray[0])) {
                    String[] transitionsArray2 = transition2.split("\\s+");
                    if (alphas.contains(transitionsArray2[1])) {
                        return false;
                    }
                    alphas.add(transitionsArray2[1]);
                }
            }
        }
        return true;
    }

    boolean acceptedSequence(String seq)
    {
        String[] sequenceArray = seq.split("\\s+");
        String currentNode = start;
        String pastNode;
        for (String sequence : sequenceArray)
        {
            pastNode = currentNode;
            for (String transition : transitions)
            {
                if (transition.contains(pastNode) && transition.contains(sequence))
                {
                    String[] transitionArray = transition.split("\\s+");
                    currentNode = transitionArray[2];
                    break;
                }
            }
            if (currentNode.equals(pastNode))
            {
                return false;
            }
        }
        if (finalStates.contains(currentNode))
            return true;
        return false;
    }


}
