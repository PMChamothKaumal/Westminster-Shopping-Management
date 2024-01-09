import GUI.CartGui;
import GUI.ShoppingGui;
import Online_Shopping_System.User;
import Online_Shopping_System.WestminsterShoppingManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    private static String ManagerId = "M001";
    private  static String ManagerPassword = "M1234";
    public static boolean go = true;
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean bool1 = true;

        try {
            File myObj = new File("Westminster_shopping.txt");
            File myObj2 = new File("Cart_Storage.txt");
            File myObj3 = new File("UsersData.txt");
            if (myObj.createNewFile()||myObj2.createNewFile()||myObj3.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File already Created & You can Go Throw \n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

        System.out.println("\nWelcome to Online Shopping System\n");
        System.out.println("1. User Login ");
        System.out.println("2. Manager Login ");


        while (bool1) {
            System.out.print("\nEnter option to go throw: ");
            String option1 = input.next();

            if (option1.equals("1")) {

                while (go) {
                    User user1 = new User();
                    System.out.print("Enter UserName: ");
                    user1.setUserName(input.next());
                    System.out.print("Enter Password: ");
                    user1.setPassWord(input.next());
                    if (user1.storeDate()) {
                        go = false;
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    ShoppingGui shoppingGui = new ShoppingGui(user1.getUserName());
                                    shoppingGui.setVisible(true);

                                } catch (HeadlessException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }

                bool1 = false;
            } else if (option1.equals("2")) {
                boolean manger = true;
                while (manger) {
                    System.out.print("Enter Manger Id: ");
                    String ans1 = input.next();
                    System.out.print("Enter Manger Password: ");
                    String ans2 = input.next();
                    if (ManagerId.equals(ans1) && ManagerPassword.equals(ans2)) {
                        WestminsterShoppingManager w1 = new WestminsterShoppingManager();
                        w1.LoadProduct();
                        w1.Menu();
                        bool1 = false;
                        manger = false;
                    }else {
                        System.out.println("\nInvalid Username or Password! ");
                    }
                }
            } else {
                System.out.println("Invalid input, Please Try Again! ");
            }
        }
    }
}