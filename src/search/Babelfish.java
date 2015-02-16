package search;

import java.util.Scanner;

/**
 * Created by basin on 16/2/15.
 */
public class Babelfish {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap map = new HashMap(100001, 99997);
        String line = in.nextLine();
        while (line.length() > 0) {
            String[] words = line.split(" ");
            map.put(words[1].trim(), words[0].trim());
            line = in.nextLine();
        }
        line = in.nextLine();
        while (line.length() > 0) {
            String w = map.containsKey(line)?map.get(line):"eh";
            System.out.println(w);
            line = in.nextLine();
        }
    }
}
