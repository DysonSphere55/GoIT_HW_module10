package Part_3;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.*;

public class PartThree {

    public static void main(String[] args) {

        File file = new File("file_3.txt");
        StringBuilder text = new StringBuilder();


        try (FileInputStream fis = new FileInputStream(file);
             Scanner scanner = new Scanner(fis)) {
            while (scanner.hasNext()) {
                text.append(scanner.next().toLowerCase()).append(" ");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        String[] words = text.toString().strip().split(" ");

        Map<String, Integer> hashMap = new HashMap<>();
        for (String word: words) {
            if (hashMap.containsKey(word)) {
                hashMap.put(word, hashMap.get(word) + 1);
            } else {
                hashMap.put(word, 1);
            }
        }

        System.out.println("unsorted hashMap = " + hashMap);
        System.out.println("sorted hashMap = " + sortMapByValues(hashMap));
    }



    static Map<String, Integer> sortMapByValues(Map<String, Integer> hashMap) {
        //        Sort the HashMap by values in 5 steps:

//        1) - create set by calling entrySet() method of Map:
        Set<Map.Entry<String, Integer>> entries = hashMap.entrySet();

//        2) - create a custom Comparator to sort entries based upon values:
        Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                Integer v1 = o1.getValue();
                Integer v2 = o2.getValue();
                return v2.compareTo(v1);
            }
        };

//        3) - convert entry set to list:
        List<Map.Entry<String, Integer>> listOfEntries = new ArrayList<>(entries);

//        4) - sort entry list by using Collections.sort() method by passing your value comparator:
        Collections.sort(listOfEntries, valueComparator);

//        5) - create a LinkedHashMap by adding entries in sorted order:
        LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<>(listOfEntries.size());
        for (Map.Entry<String, Integer> entry : listOfEntries) {
            sortedByValue.put(entry.getKey(), entry.getValue());
        }

        return sortedByValue;
    }
}
