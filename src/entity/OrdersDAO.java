package entity;

import core.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrdersDAO implements DAO<Orders> {

    public OrdersDAO() {}

    List<Orders> ordersList;


    @Override
    public Optional<Orders> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Orders WHERE orderid = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            Orders order = null;
            if (rs.next()) {
                order = new Orders();
                order.setOrderId(rs.getInt("orderid"));
                order.setCustomerName(rs.getString("customername"));
                order.setOrderDate(rs.getTimestamp("orderdate"));
            }

            return Optional.ofNullable(order);

        } catch (SQLException ex) {
            System.err.println(ex);
            return Optional.empty();
        }
    }

    /**
     * Get all Orders
     */
    @Override
    public List<Orders> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        ordersList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Orders ORDER BY orderid";
            rs = db.executeQuery(sql);

            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderId(rs.getInt("orderid"));
                order.setCustomerName(rs.getString("customername"));
                order.setOrderDate(rs.getTimestamp("orderdate"));

                ordersList.add(order);
            }

            return ordersList;

        } catch (SQLException ex) {
            System.err.println(ex);
            return new ArrayList<>();
        }
    }


    @Override
    public void insert(Orders order) {
        DB db = DB.getInstance();

        try {
            String sql = "INSERT INTO Orders(orderid, orderdate, customername) VALUES (?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);

            stmt.setInt(1, order.getOrderId());
            stmt.setTimestamp(2, order.getOrderDate());
            stmt.setString(3, order.getCustomerName());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Orders inserted successfully!");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Update an existing Orders record
     */
    @Override
    public void update(Orders order) {
        DB db = DB.getInstance();

        try {
            String sql = "UPDATE Orders SET orderdate=?, customername=? WHERE orderid=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);

            stmt.setTimestamp(1, order.getOrderDate());
            stmt.setString(2, order.getCustomerName());
            stmt.setInt(3, order.getOrderId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Orders updated successfully!");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Delete an Orders record
     */
    @Override
    public void delete(Orders order) {
        DB db = DB.getInstance();

        try {
            String sql = "DELETE FROM Orders WHERE orderid = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, order.getOrderId());

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Orders deleted successfully!");
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
            String sql = "SELECT * FROM Orders WHERE orderid = -1"; // No data, only headers
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
