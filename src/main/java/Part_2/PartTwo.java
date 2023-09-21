package Part_2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartTwo {

    public static void main(String[] args) {

        File file = new File("file_2.txt");
        List<User> users = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             Scanner scanner = new Scanner(fis)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String name = scanner.next();
                Integer age = scanner.nextInt();
                users.add(new User(name, age));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileOutputStream fos = new FileOutputStream("user.json");
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos))) {
            bufferedWriter.write(gson.toJson(users));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
