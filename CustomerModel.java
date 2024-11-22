import java.sql.*;

public class CustomerModel {
    private int customer_id;
    private String name;
    private String email;
    private String phone;

    // Getters and setters
    public int getCustomerId() { return customer_id; }
    public void setCustomerId(int customer_id) { this.customer_id = customer_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    // SQL Queries

    // 1. a. Reading customer record to verify details
    public CustomerModel getCustomerById(int customer_id) {
        CustomerModel customer = null;
        String query = "SELECT * FROM Customer WHERE customer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customer_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new CustomerModel();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}