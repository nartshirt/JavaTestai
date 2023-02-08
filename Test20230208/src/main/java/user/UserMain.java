package user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserMain {
    static final String USERS_FILE = "users.json";
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Name");
        String name = scanner.nextLine();
        System.out.println("Enter your Surname");
        String surname = scanner.nextLine();
        System.out.println("Enter your personal number");
        String personalNumber = scanner.nextLine();

        User user1 = new User(name, surname, personalNumber);

        List<User>userList = new ArrayList<>();
        userList.add(user1);
        //System.out.println(userList);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        File usersFile = new File(USERS_FILE);
        if (!usersFile.exists()){
            try {
                usersFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Problem with file " + e.getMessage());
            }
        }

        try {
            mapper.writeValue(new File(USERS_FILE),userList);
        } catch (IOException e) {
            System.out.println("Cannot write to file " + e.getMessage());
        }

        userList.clear();
    }
}
