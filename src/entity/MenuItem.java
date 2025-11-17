/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
/**
 *
 * @author Gokhan
 */
public class MenuItem
{
    private int ID;
    private String ItemName;
    private String ItemDescription;
    private double Price;

    public MenuItem(){}

    public MenuItem(int ID, String ItemName, String ItemDescription, double Price)
    {
        this.ID = ID;
        this.ItemName = ItemName;
        this.ItemDescription = ItemDescription;
        this.Price = Price;
    }
    // Getters and Setters
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String ItemName) {
        this.ItemName = ItemName;   // FIXED
    }

    public String getItemDescription() {
        return ItemDescription;
    }
    public void setItemDescription(String ItemDescription) {
        this.ItemDescription = ItemDescription;  // FIXED
    }

    public double getPrice() {
        return Price;
    }
    public void setPrice(double Price) {
        this.Price = Price;   // FIXED
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "ID=" + ID +
                ", ItemName='" + ItemName + '\'' +
                ", ItemDescription='" + ItemDescription + '\'' +
                ", Price=" + Price + '}';
    }
}
