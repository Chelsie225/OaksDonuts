/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import core.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 * contact
 * @author Gokhan
 */
public class MenuItemDAO implements DAO<MenuItem>
{   
    public MenuItemDAO() {
        
    }
    List<MenuItem> MenuItems;
    /**
     * Get a single MenuItem entity as a MenuItem object
     * @param id
     * @return 
     */
    @Override
    public Optional<MenuItem> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM MenuItem WHERE id = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            MenuItem menuItem = null;
            while (rs.next()) {
                menuItem = new MenuItem(rs.getInt("id"), rs.getString("itemname"), rs.getString("itemdescription"), rs.getDouble("price"));
            }
            return Optional.ofNullable(menuItem);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Get all MenuItem entities as a List
     * @return 
     */
    @Override
    public List<MenuItem> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        MenuItems = new ArrayList<>();
        try {
            String sql = "SELECT * FROM MenuItem ORDER BY id";
            rs = db.executeQuery(sql);
            MenuItem menuItem = null;
            while (rs.next()) {
                menuItem = new MenuItem(rs.getInt("id"), rs.getString("itemname"), rs.getString("itemdescription"), rs.getDouble("price"));
                MenuItems.add(menuItem);
            }
            return MenuItems;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a MenuItem object into MenuItem table
     * @param menuItem
     */
    @Override
    public void insert(MenuItem menuItem)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO MenuItem(ID, itemName,itemdescription , price) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, menuItem.getID());
            stmt.setString(2, menuItem.getItemName());
            stmt.setString(3, menuItem.getItemDescription());
            stmt.setDouble(4, menuItem.getPrice());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new MenuItem was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update a MenuItem entity in database if it exists using a MenuItem object
     * @param menuItem
     */
    @Override
    public void update(MenuItem menuItem) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE MenuItem SET ItemName=?, ItemDescription=?, Price=? WHERE id=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, menuItem.getItemName());
            stmt.setString(2, menuItem.getItemDescription());
            stmt.setDouble(3, menuItem.getPrice());
            stmt.setInt(4, menuItem.getID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing MenuItem was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete a MenuItem from MenuItem table if the entity exists
     * @param menuItem
     */
    @Override
    public void delete(MenuItem menuItem) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM MenuItem WHERE ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, menuItem.getID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A MenuItem was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Get all column names in a list array
     * @return 
     */
    @Override
    public List<String> getColumnNames() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        List<String> headers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM MenuItem WHERE ID = -1";//We just need this sql query to get the column headers
            rs = db.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //Get number of columns in the result set
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                headers.add(rsmd.getColumnLabel(i));//Add column headers to the list
            }
            return headers;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        } 
    }
}
