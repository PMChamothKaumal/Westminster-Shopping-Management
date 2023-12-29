package Online_Shopping_System;

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

    public void setSize(String Size) {
        this.Size = Size;
    }

    public String getColour() {
        return Colour;
    }



    public void setColour(String Colour) {
        this.Colour = Colour;
    }
}


