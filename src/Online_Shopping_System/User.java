package Online_Shopping_System;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class User {
    private String UserName;
    private String PassWord;


    public User() {

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    String data[][] = {{"ch","1234"},{"kaumal","k1234"},{"supun","s1234"}};
    public boolean storeDate(){


        boolean isAuthenticated = false;

        for (String[] userCredentials : data) {
            String usernameFromArray = userCredentials[0];
            String passwordFromArray = userCredentials[1];

            if (getUserName().equals(usernameFromArray) && getPassWord().equals(passwordFromArray)) {
                isAuthenticated = true;
                break;
            }
        }
        if (isAuthenticated) {
            System.out.println("Login successful");
            return true;
        } else {
            System.out.println("Invalid credentials, Please check your username and password.");
            return false;
        }
    }
    public boolean validateFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("UsersData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String[] entry : data) {
                    String arrayUsername = entry[0];
                    if (line.trim().equals(arrayUsername)) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }



}
