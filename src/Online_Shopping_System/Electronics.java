package Online_Shopping_System;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Electronics extends Product{
    private String Brand;
    private int WarrantyPeriod;

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public int getWarrantyPeriod() {
        return WarrantyPeriod;
    }

    public void setWarrantyPeriod(Scanner input) {
        boolean validInput = false;
        while (!validInput) {
            try {
                int warrantyPeriod = input.nextInt();
                this.WarrantyPeriod = warrantyPeriod;
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for Warranty Period.");
                input.next();
            }
        }
    }

    public Electronics(String ProductId, String ProductName, int ItemQty, double Price, String Brand, int WarrantyPeriod) {
      super(ProductId,ProductName,ItemQty,Price);
      this.Brand = Brand;
      this.WarrantyPeriod = WarrantyPeriod;
    }


    public Electronics() {

    }
}
