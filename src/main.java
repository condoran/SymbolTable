import java.io.File;
import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        SymbolTable s = new SymbolTable();
//        System.out.println(s.add("abc"));
//        System.out.println(s.add("cba"));
//        System.out.println(s.add("1"));
//        System.out.println(s.add("\"string\""));
//        System.out.println(s.add("cba"));
//        System.out.println("\n");
//        System.out.println(s.search("abc"));
//        System.out.println(s.search("cba"));
//        System.out.println(s.search("1"));
//        System.out.println(s.search("\"string\""));
//        System.out.println(s.search("cbaa"));
//        System.out.println("\n");
//        Scaner sc = new Scaner("file.txt");
//        try {
//            sc.scan();
//        }
//        catch (Exception e)
//        {
//            System.out.println("Erorare");
//        }
        FA fa = new FA("FA.in");
        fa.menuLoop();
    }
}
