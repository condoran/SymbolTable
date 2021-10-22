import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scaner {
    private String stFileName;
    private String pifFileName;
    private List<Pair<String, Integer>> pif = new ArrayList<>();
    private SymbolTable st = new SymbolTable();;
    private String problemFileName;
    private List<String> reservedWords;
    private List<String> operators;
    private List<String> separators;


    public Scaner(String pfn)
    {
        problemFileName = pfn;
    }

    public void scan() throws FileNotFoundException {
//        File stFile = new File(stFileName);
//        File pifFile = new File(stFileName);
        File problemFile = new File(problemFileName);

        java.util.Scanner reader = new java.util.Scanner(problemFile);

        while (reader.hasNextLine())
        {
            String data = reader.nextLine();
            String[] tokenList = data.split("\\s+");
            for (int i = 0; i < tokenList.length; i++)
            {
                int detected = detect(tokenList[i]);
                if (detected >= 1 && detected <= 3)
                {
                    pif.add(new Pair<>(tokenList[i], 0));
                }
                else if (detected == 4 || detected == 5)
                {
                    int pos = searchAndAddSt(tokenList[i]);
                    pif.add(new Pair<>(tokenList[i], pos));
                }
                else
                    System.out.println("Lexical Error");
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
        System.out.println(token);
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
