import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
    private Integer cap = 20;
    private Integer length = 0;
    private List<String> table;

    public SymbolTable()
    {
        table = new ArrayList<>();
        for (int i = 0; i < cap; i++)
            table.add(null);
    }


    public Integer h(String v)
    {
        Integer s = 0;
        for (int i = 0; i < v.length(); i++)
        {
            s += (int) v.charAt(i);
        }
        return s % table.size();
    }

//  Input: v - string - value to be searched in the hash table
//  Output: pos - integer - the position of the element in the table or -1 if it does not exist in the table
    public Integer search(String v)
    {
        Integer pos = h(v);
        while (pos < cap) {

            if (table.get(pos) == v)
                return pos;
            else if (table.get(pos) == null)
                return -1;
            pos++;
        }
        if (pos == cap)
        {
            while (pos < cap)
            {
                if (table.get(pos) == v)
                    return pos;
                else if (table.get(pos) == null)
                    return -1;
                pos++;
            }
        }
        return -1;
    }

//  Input: v - string - value to be added in the hash table
//  Output: pos - integer - the position in which it has been inserted or -1 if the element already exists
    public Integer add(String v)
    {
        Integer pos = h(v);
        if (search(v) != -1)
        {
            return -1;
        }
        while (pos < cap && table.get(pos) != null && table.get(pos) != v)
        {
            pos++;
        }
        if (pos == cap)
        {
            while (table.get(pos) != null)
            {
                pos++;
            }
        }
        table.set(pos, v);
        return pos;
    }
}
