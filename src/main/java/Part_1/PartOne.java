package Part_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {


    public static void main(String[] args) {

        File file = new File("file_1.txt");

        try (FileInputStream fis = new FileInputStream(file);
             Scanner scanner = new Scanner(fis)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (isLinePhoneNumber(line)) {
                    System.out.println(line);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static Boolean isLinePhoneNumber(String input) {
        final String REGEX = "(\\d{3}\\-\\d{3}\\-\\d{4})|(\\(\\d{3}\\)\\s\\d{3}\\-\\d{4})";
        boolean isMatch = false;
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            isMatch = true;
        }
        return isMatch;
    }
}
