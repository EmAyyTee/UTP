package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ProgLang {
    private final Map<String, List<String>> langsMap = new LinkedHashMap<>();
    private final Map<String, List<String>> progsMap = new LinkedHashMap<>();

    public ProgLang(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        for (String s : lines) {
            String[] tokens = s.split("\t");
            String lang = tokens[0];
            List<String> programmers = Arrays.asList(Arrays.copyOfRange(tokens, 1, tokens.length));
            langsMap.put(lang, new ArrayList<>(programmers));
            for(String prog : programmers) {
                progsMap.computeIfAbsent(prog, k -> new ArrayList<>()).add(lang);
            }
        }
    }
}
