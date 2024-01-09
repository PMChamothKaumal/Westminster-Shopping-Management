package Online_Shopping_System;

import java.util.Scanner;

public class Clothing extends Product{
    private String Size;
    private String Colour;

    public Clothing() {

    }
    public Clothing(String ProductId, String ProductName, int ItemQty, double Price, String size, String colour) {
        super(ProductId, ProductName, ItemQty, Price);
        Size = size;
        Colour = colour;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(Scanner input) {
        boolean validInput = true;
        while (validInput) {
            String Size = input.next();
            if (Size.matches("[a-zA-Z]+")) {
                this.Size = Size;
                validInput = false;
            } else {
                System.out.print("Invalid input. Please enter a valid Size: ");
            }
        }
    }

    public String getColour() {
        return Colour;
    }



    public void setColour(Scanner input) {
        boolean validInput = true;
        while (validInput) {
            String Colour = input.next();
            if (Colour.matches("[a-zA-Z]+")) {
                this.Colour = Colour;
                validInput = false;
            } else {
                System.out.print("Invalid input. Please enter a Colour Name: ");
            }
        }
    }
}


