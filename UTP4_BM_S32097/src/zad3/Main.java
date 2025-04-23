/**
 *
 *  @author Balcerowicz Maciej S32097
 *
 */

package zad3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) throws IOException {

    Map<String, List<String>> anagramGroups = new BufferedReader(
            new InputStreamReader(
                    new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").openStream()
            ))
            .lines()
            .collect(Collectors.groupingBy(
                    word -> {
                      char[] chars = word.toCharArray();
                      Arrays.sort(chars);
                      return new String(chars);
                    }
            ));

    anagramGroups.values()
            .stream()
            .filter(list -> list.size() > 1)
            .map(group -> String.join(" ", group))
            .sorted()
            .forEach(System.out::println);
  }
}
