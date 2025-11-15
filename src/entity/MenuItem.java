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
    
    public MenuItem(int ID, String ItemName, String ItemDescription, double Price)
    {
        this.ID = ID;
        this.ItemName = ItemName;
        this.ItemDescription = ItemDescription;
        this.Price = Price;
    }

    public int getID() {
        return ID;
    }

    public String getItemName() {
        return  ItemName;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public double getPrice() {
        return Price;
    }

    @Override
    public String toString() {
        return "MenuItem{" + "ID=" + ID + ", ItemName=" + ItemName + ", ItemDescription=" + ItemDescription + ", price=" + Price + '}';
    }
}
