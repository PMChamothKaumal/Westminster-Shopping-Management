package Online_Shopping_System;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Product {
    private String ProductId;
    private String ProductName;
    private int ItemQty;
    private double Price;

    public Product() {
         
    }

    public Product(String ProductId, String ProductName, int ItemQty, double Price) {
        this.ProductId = ProductId;
        this.ProductName = ProductName;
        this.ItemQty = ItemQty;
        this.Price = Price;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(Scanner input) {
        boolean validInput = true;
        while (validInput) {
            String ProductName = input.next();
            if (ProductName.matches("[a-zA-Z]+")) {
                this.ProductName = ProductName;
                validInput = false;
            } else {
                System.out.print("Invalid input. Please enter valid product name: ");
            }
        }
    }

    public int getItemQty() {
        return ItemQty;
    }

    public void setItemQty(Scanner input) {
        boolean validInput = false;
        while (!validInput) {
            try {
                int ItemQty = input.nextInt();
                this.ItemQty = ItemQty;
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid Product Qty: ");
                input.next();
            }
        }
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(Scanner input) {
        boolean validInput = false;
        while (!validInput) {
            try {
                int Price = input.nextInt();
                this.Price = Price;
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid Price. ");
                input.next();
            }
        }
    }
}
