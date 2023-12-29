import GUI.CartGui;
import GUI.ShoppingGui;
import Online_Shopping_System.User;
import Online_Shopping_System.WestminsterShoppingManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean bool1 = true;


        //CartGui cart = new CartGui();
        System.out.println("\nWelcome to Online Shopping System\n");
        System.out.println("1. User Login ");
        System.out.println("2. Manager Login");
        while (bool1) {
            System.out.print("\nEnter option to go throw: ");
            String option1 = input.next();
            if (option1.equals("1")) {
                User u1 = new User();

                ShoppingGui shoppingGui = new ShoppingGui();
                shoppingGui.setVisible(true);
                bool1 = false;
            } else if (option1.equals("2")) {
                WestminsterShoppingManager w1 = new WestminsterShoppingManager();
                w1.Menu();
                bool1 = false;
            } else {
                System.out.println("Invalid input, Please Try Again! ");
            }
        }
    }
}