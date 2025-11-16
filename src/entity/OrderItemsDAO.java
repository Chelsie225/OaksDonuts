package entity;

import core.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrderItemsDAO implements DAO<OrderItems> {

    public OrderItemsDAO() {}

    List<OrderItems> orderItemsList;

    /**
     * Get a single OrderItems entity by ID
     */
    @Override
    public Optional<OrderItems> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM OrderItem WHERE orderitemid = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            OrderItems orderItem = null;
            if (rs.next()) {
                // Assuming you have MenuItemDAO to fetch the MenuItem object
                MenuItemDAO menuDAO = new MenuItemDAO();
                MenuItem menuItem = menuDAO.get(rs.getInt("menuitemid")).orElse(null);

                orderItem = new OrderItems(menuItem, rs.getInt("quantity"));
                orderItem.setOrderItemId(rs.getInt("orderitemid"));
                orderItem.setOrderId(rs.getInt("orderid"));
            }

            return Optional.ofNullable(orderItem);

        } catch (SQLException ex) {
            System.err.println(ex);
            return Optional.empty();
        }
    }

    /**
     * Get all OrderItems
     */
    @Override
    public List<OrderItems> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        orderItemsList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM OrderItem ORDER BY orderitemid";
            rs = db.executeQuery(sql);

            MenuItemDAO menuDAO = new MenuItemDAO();

            while (rs.next()) {
                MenuItem menuItem = menuDAO.get(rs.getInt("menuitemid")).orElse(null);
                OrderItems orderItem = new OrderItems(menuItem, rs.getInt("quantity"));
                orderItem.setOrderItemId(rs.getInt("orderitemid"));
                orderItem.setOrderId(rs.getInt("orderid"));

                orderItemsList.add(orderItem);
            }

            return orderItemsList;

        } catch (SQLException ex) {
            System.err.println(ex);
            return new ArrayList<>();
        }
    }

    /**
     * Insert a new OrderItems record
     */
    @Override
    public void insert(OrderItems orderItem) {
        DB db = DB.getInstance();

        try {
            String sql = "INSERT INTO OrderItem(orderitemid, orderid, menuitemid, quantity, lineprice) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);

            stmt.setInt(1, orderItem.getOrderItemId());
            stmt.setInt(2, orderItem.getOrderId());
            stmt.setInt(3, orderItem.getMenuItem().getID());
            stmt.setInt(4, orderItem.getQuantity());
            stmt.setDouble(5, orderItem.getLinePrice());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("OrderItem inserted successfully!");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Update an existing OrderItems record
     */
    @Override
    public void update(OrderItems orderItem) {
        DB db = DB.getInstance();

        try {
            String sql = "UPDATE OrderItem SET orderid=?, menuitemid=?, quantity=?, lineprice=? WHERE orderitemid=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);

            stmt.setInt(1, orderItem.getOrderId());
            stmt.setInt(2, orderItem.getMenuItem().getID());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setDouble(4, orderItem.getLinePrice());
            stmt.setInt(5, orderItem.getOrderItemId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("OrderItem updated successfully!");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Delete an OrderItems record
     */
    @Override
    public void delete(OrderItems orderItem) {
        DB db = DB.getInstance();

        try {
            String sql = "DELETE FROM OrderItem WHERE orderitemid = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, orderItem.getOrderItemId());

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("OrderItem deleted successfully!");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Get column names for table display
     */
    @Override
    public List<String> getColumnNames() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        List<String> headers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM OrderItem WHERE orderitemid = -1"; // no data, only headers
            rs = db.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                headers.add(rsmd.getColumnLabel(i));
            }

            return headers;

        } catch (SQLException ex) {
            System.err.println(ex);
            return new ArrayList<>();
        }
    }
}
