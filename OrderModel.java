import java.sql.*;

public class OrderModel {
    private int order_id;
    private int customer_id;
    private double total_amount;

    // Getters and setters
    public int getOrderId() { return order_id; }
    public void setOrderId(int order_id) { this.order_id = order_id; }

    public int getCustomerId() { return customer_id; }
    public void setCustomerId(int customer_id) { this.customer_id = customer_id; }

    public double getTotalAmount() { return total_amount; }
    public void setTotalAmount(double total_amount) { this.total_amount = total_amount; }

    // SQL Queries

    // 1. c. Updating the total amount of the order
    public boolean updateOrderTotal(int order_id) {
        String query = "UPDATE Orders SET total_amount = "
                     + "(SELECT SUM(m.price * oi.quantity) "
                     + "FROM OrderItem oi JOIN MenuItem m ON oi.item_id = m.item_id "
                     + "WHERE oi.order_id = ?) WHERE order_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, order_id);
            stmt.setInt(2, order_id);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}