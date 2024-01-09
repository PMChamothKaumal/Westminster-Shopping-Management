package Online_Shopping_System;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    static String Pid;
    static Scanner input = new Scanner(System.in);
    public static int i;
    public static Electronics electronicsList[] = new Electronics[50];
    public static Clothing[] clothingList = new Clothing[50];
    public int countOfProduct =0;


    public void Menu() {

        Scanner input1 = new Scanner(System.in);
        System.out.println("\nWELCOME TO MANAGING PROCESS....");
        System.out.println();
        String data[] = {"Add a new Product", "Delete a Product", "Print the list of Products", "Save in a File", "Exit"};
        for ( i = 0; i < data.length; i++) {
            System.out.println("\t\t[" + (i + 1) + "] " + data[i]);

        }
        boolean hand = true;
        while (hand) {
            hand = false;
            System.out.print("\n\t\tENTER AN OPTION TO CONTINUE: ");
            String option = input1.next();

            if (option.equals("1")) {
                addProduct();
            } else if (option.equals("2")) {
                deleteProduct();
            } else if (option.equals("3")) {
                printProductList();
            } else if (option.equals("4")) {
                saveToFile();
            } else if (option.equals("5")) {
                countOfProduct=0;
                System.exit(0);
                break;
            } else {
                System.out.println("\t\t\tInvalid input try again..!\n");
                hand = true;
            }
        }
    }

    @Override
    public void addProduct() {
        boolean check = true;
        while (check) {
            System.out.print("\nEnter 1 for Electronics, 2 for Clothing: ");
            String Answer = input.next();

            if (Answer.equals("1")) {
                for ( i = 0; i < electronicsList.length; i++) {
                    if (countOfProduct<50){
                        System.out.println(countOfProduct);
                    if (electronicsList[i] == null){
                        electronicsList[i] = new Electronics();
                        System.out.print("Enter Product Id: ");
                        electronicsList[i].setProductId(input.next());
                        AddElectronics(electronicsList);
                    }else {
                        i++;
                    }
                    }else {
                        System.out.println("You Can't add products, Maximum count is cover!");
                        boolean go = true;
                        while (go) {
                            System.out.print("\nEnter 1 to Main menu: ");
                            String menu = input.next();
                            if (menu.equals("1")) {
                                go = false;
                                Menu();
                            } else {
                                System.out.println("Invalid input");
                            }
                        }
                        break;
                    }

                }
                check = false;
            } else if (Answer.equals("2")) {

                for ( i = 0; i < clothingList.length; i++) {
                    if (countOfProduct<50) {
                        if (clothingList[i] == null) {
                            clothingList[i] = new Clothing();
                            System.out.print("Enter Product Id: ");
                            clothingList[i].setProductId(input.next());
                            AddClothing(clothingList);
                        } else {
                            i++;
                        }
                    }else {
                        System.out.println("You Can't add products, Maximum count is cover!");

                        boolean go = true;
                        while (go) {
                            System.out.print("\nEnter 1 to Main menu: ");
                            String menu = input.next();
                            if (menu.equals("1")) {
                                go = false;
                                Menu();
                            } else {
                                System.out.println("Invalid input");
                            }
                        }
                        break;
                    }

                }
                check = false;
            } else {
                System.out.println("Invalid input Try again!");
            }
        }
    }
    public boolean handler = true;
    public boolean found = false;
    @Override
    public void deleteProduct() {
        while(handler) {
            found=false;
            System.out.print("Product ID: ");
            Pid = input.next();
            deleteElectronics(electronicsList);
            deleteClothing(clothingList);
            System.out.println(found);

            if (!found) {
                System.out.println("Can't find Book!! Try again.");
            }
        }
    }


    public  void deleteElectronics(Electronics electronics[]){
        for (int i = 0; i < electronics.length; i++) {
            if (electronics[i] != null && Pid.equals(electronics[i].getProductId())) {
                // Book found, mark as null
                System.out.println(electronicsList[i].getProductId() + "\n" + electronicsList[i].getProductName() + "\n" + electronicsList[i].getBrand());
                System.out.println(electronicsList[i].getItemQty() + "\n" + electronicsList[i].getPrice() + "\n" + electronicsList[i].getWarrantyPeriod()+"");
                electronics[i] = null;
                found = true;
                System.out.println("Deleted Successfully!\n");
                countOfProduct--;
                Validate("Delete Another Product (Y/N): ");
                boolean allNull = Arrays.stream(electronics).allMatch(element -> element == null);

                if (allNull) {
                    System.out.println("Oops Electronic list is empty!! ");
                    boolean em = true;
                    while(em){
                        System.out.print("Enter num '1' to go to Menu page: ");
                        String empty = input.next();
                        if(empty.equals("1")){
                            em = false;
                            Menu();
                        }
                        else{
                            System.out.println("Invalid Input!! Try again..");
                        }
                    }
                }
                break;
            }
        }
    }

    public  void deleteClothing(Clothing clothing[]){
        for (int i = 0; i < clothing.length; i++) {
            if (clothing[i] != null && Pid.equals(clothing[i].getProductId())) {
                // Product found, mark as null
                System.out.println(clothingList[i].getProductId() + "\n" + clothingList[i].getProductName() + "\n" + clothingList[i].getSize());
                System.out.println(clothingList[i].getItemQty() + "\n" + clothingList[i].getPrice() + "\n" + clothingList[i].getColour()+"");
                clothing[i] = null;
                found = true;
                System.out.println("\nDeleted Successfully!\n");
                countOfProduct--;
                Validate("Delete Another Product (Y/N): ");
                boolean allNull = Arrays.stream(clothing).allMatch(element -> element == null);

                if (allNull) {
                    System.out.println("Oops Clothing list is empty!! ");
                    boolean em = true;
                    while(em){
                        System.out.print("Enter num '1' to go to Menu page: ");
                        String empty = input.next();
                        if(empty.equals("1")){
                            em = false;
                            Menu();
                        }
                        else{
                            System.out.println("Invalid Input!! Try again..");
                        }
                    }
                }
                break;
            }
        }
    }

    @Override
    public void printProductList() {
        System.out.print("+");
        Loop();
        System.out.print("\n|");
        System.out.printf(" %18s    | %18s    | %18s    | %18s    | %18s    | %28s                 |",
                "PRODUCT ID", "PRODUCT NAME", "PRODUCT QTY", "PRODUCT PRICE", "CATEGORY", "INFO");
        System.out.print("\n+");
        Loop();

        for(int j=0; j<electronicsList.length; j++){
            if(electronicsList[j]!=null){
                System.out.print("\n|");
                System.out.printf(" %18s    | %18s    | %18s    | %18s    | %18s    | %38s       |",
                        electronicsList[j].getProductId(), electronicsList[j].getProductName(),
                        electronicsList[j].getItemQty(), electronicsList[j].getPrice(),
                        "Electronic","Brand: "+electronicsList[j].getBrand()+" ,  Warranty: "+electronicsList[j].getWarrantyPeriod());
                System.out.print("\n+");
                Loop();
            }
        }

        for(int j=0; j<clothingList.length; j++){
            if(clothingList[j]!=null){
                System.out.print("\n|");
                System.out.printf(" %18s    | %18s    | %18s    | %18s    | %18s    | %38s       |",
                        clothingList[j].getProductId(), clothingList[j].getProductName(),
                        clothingList[j].getItemQty(), clothingList[j].getPrice(),
                        "Clothing","Size: "+clothingList[j].getSize()+" ,  Colour: "+clothingList[j].getColour());
                System.out.print("\n+");
                Loop();
            }
        }

        boolean go = true;
        while (go) {
            System.out.print("\nEnter 1 to Main menu: ");
            String menu = input.next();
            if (menu.equals("1")) {
                go = false;
                Menu();
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    @Override
    public void saveToFile() {
        try{
            FileWriter fw = new FileWriter("Westminster_shopping.txt", false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        }catch(Exception exception){
            System.out.println("Exception have been caught");
        }

        try {
            FileWriter myWriter = new FileWriter("Westminster_shopping.txt");
            for(int j=0; j<electronicsList.length; j++){
                if(electronicsList[j]!=null){
                    myWriter.write("Electronics"+","+electronicsList[j].getProductId()+","+electronicsList[j].getProductName()+","+
                            electronicsList[j].getItemQty()+","+electronicsList[j].getPrice()+","+
                            electronicsList[j].getBrand()+","+electronicsList[j].getWarrantyPeriod()+"\n"
                    );
                }
            }

            for(int j=0; j<clothingList.length; j++){
                if(clothingList[j]!=null){
                    myWriter.write(
                            "Clothing"+","+clothingList[j].getProductId()+","+clothingList[j].getProductName()+
                                    ","+clothingList[j].getItemQty()+","+clothingList[j].getPrice()+
                                    ","+clothingList[j].getSize()+","+clothingList[j].getColour()+"\n"
                    );
                }
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.\n");
            Menu();

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public  void AddElectronics(Electronics electronics[]){
        boolean count = false;
        for (int j = 0; j < i; j++) {
            if (electronics[i].getProductId().equals(electronics[j].getProductId())) {
                System.out.println("Already exist in Store..!");
                Validate(" Add another Product (Y/N): ");
                count = true;
                break;
            }
        }

        if (!count) {
            System.out.print("Enter Product Name: ");
            electronics[i].setProductName(input);
            System.out.print("Enter Product Brand: ");
            electronics[i].setBrand(input);
            System.out.print("Enter Available Qty: ");
            electronics[i].setItemQty(input);
            System.out.print("Enter Product Price: ");
            electronics[i].setPrice(input);
            System.out.print("Enter Product Warranty period: ");
            electronics[i].setWarrantyPeriod(input);
        }

        if (!count) {
            System.out.println("Added successfully.\n");
            countOfProduct++;
            Validate("Add another Product (Y/N): ");
        }
    }

    public  void AddClothing(Clothing clothing[]){
        boolean count = false;
        for (int j = 0; j < i; j++) {
            if (clothing[i].getProductId().equals(clothing[j].getProductId())) {
                System.out.println("Already exist in Store..!");
                Validate(" Add another Product (Y/N): ");
                count = true;
                break;
            }
        }

        if (!count) {
            System.out.print("Enter Product Name: ");
            clothing[i].setProductName(input);
            System.out.print("Enter Available Qty: ");
            clothing[i].setItemQty(input);
            System.out.print("Enter Product Price: ");
            clothing[i].setPrice(input);
            System.out.print("Enter Product size: ");
            clothing[i].setSize(input);
            System.out.print("Enter Product colour: ");
            clothing[i].setColour(input);
        }

        if (!count) {
            System.out.println("Added successfully.\n");
            countOfProduct++;
            Validate("Add another Product (Y/N): ");
        }
    }

    public void Validate(String type) {
        Scanner input2 = new Scanner(System.in);
        boolean check = true;
        while (check) {
            System.out.print("Do you want to " + type);
            String answer = input2.next();
            if ((answer.toUpperCase()).equals("Y")) {
                check = false;
            } else if ((answer.toUpperCase()).equals("N")) {
                 i = 25;
                 Menu();;
                check = false;
            } else {
                System.out.println("Invalid input please Try Again..!");

            }
        }
    }


    public void LoadProduct(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("Westminster_shopping.txt"));
            String line = reader.readLine();
            int i = 0;
            int j = 0;
            while (line != null && (i < electronicsList.length || j < clothingList.length)) {

                String[] components = line.split(",");
                if (components.length == 7) {

                    String productId = components[1].trim();
                    String productName = components[2].trim();
                    int itemQty = Integer.parseInt(components[3].trim());
                    double price = Double.parseDouble(components[4].trim());

                    String category = components[0].trim();

                    if (category.equals("Electronics")){
                        String brand = components[5].trim();
                        int warrantyPeriod = Integer.parseInt(components[6].trim());
                        electronicsList[i] = new Electronics(productId, productName, itemQty, price, brand, warrantyPeriod);
                        countOfProduct++;
                        i++;
                    }else {
                        String size = components[5].trim();
                        String colour = components[6].trim();
                        clothingList[j] = new Clothing(productId, productName, itemQty, price,size,colour);
                        countOfProduct++;
                        j++;
                    }

                }

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Loop(){
        int mark = 24;
        for(int i=1; i<=168; i++){
            if(i==mark){
                if(mark<121) {
                    System.out.print("+");
                    mark += 24;
                }
                if (mark == 168){
                    System.out.print("+");
                }
            }
            else{
                System.out.print("-");
            }
        }
    }


}
