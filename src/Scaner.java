import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scaner {
    private String stFileName;
    private String pifFileName;
    private List<Pair<String, Integer>> pif = new ArrayList<>();
    private SymbolTable st = new SymbolTable();;
    private String problemFileName;
    private List<String> reservedWords = new ArrayList<>();
    private List<String> operators = new ArrayList<>();
    private List<String> separators = new ArrayList<>();


    public Scaner(String pfn)
    {
        problemFileName = pfn;
        reservedWords.add("if");
        reservedWords.add("else");
        reservedWords.add("while");
        reservedWords.add("for");
        reservedWords.add("not");
        reservedWords.add("no");
        reservedWords.add("yes");
        reservedWords.add("in");
        reservedWords.add("screen");
        reservedWords.add("hi");
        reservedWords.add("const");
        reservedWords.add(">>");
        reservedWords.add("<<");
        reservedWords.add("$");
        reservedWords.add("!");
        reservedWords.add("=");
        reservedWords.add("&");
        reservedWords.add("#");
        reservedWords.add("@");
        reservedWords.add("^");
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        operators.add("<");
        operators.add("==");
        operators.add(">=");
        operators.add(">");
        operators.add("<=");
        operators.add("%");
        separators.add("[");
        separators.add(",");
        separators.add("]");
        separators.add("{");
        separators.add("}");
        separators.add("(");
        separators.add(")");
        separators.add(":");
        separators.add(";");
    }

    public void scan() throws FileNotFoundException {
//        File stFile = new File(stFileName);
//        File pifFile = new File(stFileName);
        File problemFile = new File(problemFileName);

        java.util.Scanner reader = new java.util.Scanner(problemFile);
        int line = 1;
        while (reader.hasNextLine())
        {
            List<String> constants = new ArrayList<>();
            String data = reader.nextLine();
            String[] tokenList = new String[1];
            for (String separator : separators)
            {
                int i = 0;
                while (data.indexOf(separator, i) != -1)
                {
                    data = data.substring(0, data.indexOf(separator, i)) + " " + separator + " " + data.substring(data.indexOf(separator, i) + separator.length());
                    i = data.indexOf(separator, i) + separator.length();
                }

                //System.out.println(data);
            }
            int i = 0;
            if (data.indexOf("\"", i) != -1) {
                while (data.indexOf("\"", i) != -1 && data.indexOf("\"", data.indexOf("\"", i) + 1) != -1) {
                    //System.out.println(data.indexOf("\"", i));
                    //System.out.println(data.indexOf("\"", data.indexOf("\"", i) + 1));
                    tokenizeAndDetect(data.substring(0, data.indexOf("\"", i)).split("\\s+"), line);
                    tokenList[0] = (data.substring(data.indexOf("\"", i), data.indexOf("\"", data.indexOf("\"", i) + 1) + 1));
                    tokenizeAndDetect(tokenList, line);
                    tokenizeAndDetect(data.substring(data.indexOf("\"", data.indexOf("\"", i) + 1) + 1).split("\\s+"), line);
                    data = data.substring(0, data.indexOf("\"", i)) + data.substring(data.indexOf("\"", data.indexOf("\"", i) + 1) + 1);
                    //System.out.println(constants.get(0));
                    i = data.indexOf("\"", i + 1);
                }
            }
            else
            {
                tokenList = data.split("\\s+");
                tokenizeAndDetect(tokenList, line);
            }
            //tokenList = data.split("\\s+");
        line++;
        }
        try {
            FileWriter myWriter = new FileWriter("PIF.out");
            myWriter.write("PIF:\n");
            for (Pair<String, Integer> pifElem : pif) {
                myWriter.write(pifElem.getKey() + " | " + pifElem.getValue() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
//        System.out.println("PIF:");
//        for (Pair<String, Integer> pifElem : pif) {
//            System.out.println(pifElem.getKey() + " | " + pifElem.getValue());
//        }
        try {
            FileWriter myWriter = new FileWriter("ST.out");
            myWriter.write("Symbol Table:\n");
            st.tString(myWriter);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
//        System.out.println("\n");
//        System.out.println("Symbol Table:");

    }

    private void tokenizeAndDetect(String[] tokenList, int line)
    {
        for (int i = 0; i < tokenList.length; i++)
        {
            if (tokenList[i].length() != 0) {
                int detected = detect(tokenList[i]);
                if (detected >= 1 && detected <= 3) {
                    pif.add(new Pair<>(tokenList[i], 0));
                } else if (detected == 4 || detected == 5) {
                    int pos = searchAndAddSt(tokenList[i]);
                    pif.add(new Pair<>(tokenList[i], pos));
                } else {
                    System.out.println("Lexical Error");
                    System.out.println("Line: " + line);
                }

            }
        }
    }

    private int searchAndAddSt(String v) {
        //System.out.println("It worked");
        if (st.search(v) != -1)
            return st.search(v);
        return st.add(v);
    }

    public int detect(String token)
    {

        if (isNumeric(token) || (token.indexOf("\"") == 0 && token.substring(1, token.length()).indexOf("\"") == token.length() - 2))
            return 5;
        else if (reservedWords.contains(token))
        {
            return 1;
        }
        else if (operators.contains(token))
        {
            return 2;
        }
        else if (separators.contains(token))
        {
            return 3;
        }
        else if (Character.isLetter(token.charAt(0)))
            return 4;

        return -1;
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
