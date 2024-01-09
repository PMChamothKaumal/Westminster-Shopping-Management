package Online_Shopping_System;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Electronics extends Product{
    private String Brand;
    private int WarrantyPeriod;

    public String getBrand() {
        return Brand;
    }

    public void setBrand(Scanner input) {
        boolean validInput = true;
        while (validInput) {
            String Brand = input.next();
            if (Brand.matches("[a-zA-Z]+")) {
                this.Brand = Brand;
                validInput = false;
            } else {
                System.out.print("Invalid input. Please enter valid Brand Name: ");
            }
        }
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
                System.out.print("Please enter a valid value for Warranty Period: ");
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
