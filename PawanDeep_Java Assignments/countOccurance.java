package JAVA_AGGIGNMENTS;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class countOccurance {
    public static void main(String[] args) {
        String name="PAWANDEEP";
        char[] N=name.toCharArray();
        Map<Character,Integer>CharMap = new HashMap();
        for(Character ch : N)
        {
            if(CharMap.containsKey(ch))
            {
                CharMap.put(ch,CharMap.get(ch)+1);
            }
            else
            {
                CharMap.put(ch,1);
            }
        }
        Set<Character> k=CharMap.keySet();
        for(Character ch : k)
        {
            if(CharMap.get(ch)>0)
            {
                System.out.println(ch+" "+ CharMap.get(ch));
            }
        }
    }
}
