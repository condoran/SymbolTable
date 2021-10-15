public class main {
    public static void main(String[] args)
    {
        SymbolTable s = new SymbolTable();
        System.out.println(s.add("abc"));
        System.out.println(s.add("cba"));
        System.out.println(s.add("cba"));
        System.out.println(s.search("abc"));
        System.out.println(s.search("cba"));
        System.out.println(s.search("cbaa"));
    }
}
