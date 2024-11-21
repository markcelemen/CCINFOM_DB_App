import java.sql.*;

public class OrdersModel {
    private int orderId;
    private int customerId;
    private int employeeId;
    private Date orderDate;
    private double totalAmount;

    // Getters and setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    // SQL Queries

    // 1. c. Recording the order with customer and item details
    public boolean recordOrder(int customerId, int employeeId, double totalAmount) {
        String query = "INSERT INTO Orders (customer_id, employee_id, order_date, total_amount) VALUES (?, ?, CURDATE(), ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            stmt.setInt(2, employeeId);
            stmt.setDouble(3, totalAmount);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 1. e. Updating the total amount of the order
    public boolean updateOrderTotal(int orderId, double totalAmount) {
        String query = "UPDATE Orders SET total_amount = ? WHERE order_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, totalAmount);
            stmt.setInt(2, orderId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
