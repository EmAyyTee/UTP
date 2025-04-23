package zad1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public Map<String, List<String>> getLangsMap() {
        return deepCopyMap(langsMap);
    }

    public Map<String, List<String>> getProgsMap() {
        return deepCopyMap(progsMap);
    }

    public Map<String, List<String>> getLangsMapSortedByNumOfProgs(){
        return sorted(
                langsMap,
                (e1, e2) -> {
                    int cmp = Integer.compare(e2.getValue().size(), e1.getValue().size());
                    return cmp !=0 ? cmp : e1.getKey().compareTo(e2.getKey());
                }
        );
    }

    public Map<String, List<String>> getProgsMapSortedByNumOfLangs(){
        return sorted(
                progsMap,
                (e1, e2) -> {
                    int cmp = Integer.compare(e2.getValue().size(), e1.getValue().size());
                    return cmp != 0 ? cmp : e1.getKey().compareTo(e2.getKey());
                }
        );
    }

    public Map<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int n){
        return filtered(
                progsMap,
                e -> e.getValue().size() > n
        );
    }

    public <K, V> Map <K,V> sorted (Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        return map.entrySet()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public <K, V> Map <K,V> filtered (Map<K, V> map, Predicate<Map.Entry<K, V>> predicate) {
        return map.entrySet()
                .stream()
                .filter(predicate)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    private static <K, V> Map<K, List<V>> deepCopyMap(Map<K, List<V>> originalMap) {
        return originalMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> new ArrayList<>(e.getValue()),
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
