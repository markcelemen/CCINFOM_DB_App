import java.sql.*;
import java.util.*;

public class MenuItemModel {
    private int item_id;
    private String item_name;
    private double price;
    private String description;
    private String category;
    private int inventory;
    private String status;

    // Getters and setters
    public int getItemId() { return item_id; }
    public void setItemId(int item_id) { this.item_id = item_id; }

    public String getItemName() { return item_name; }
    public void setItemName(String item_name) { this.item_name = item_name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getInventory() { return inventory; }
    public void setInventory(int inventory) { this.inventory = inventory; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // SQL Queries

    // 1. b. Reading available menu items to populate the order
    public List<MenuItemModel> getAllMenuItems() {
        List<MenuItemModel> menuItems = new ArrayList<>();
        String query = "SELECT item_id, item_name, price FROM MenuItem ORDER BY item_name ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MenuItemModel menuItem = new MenuItemModel();
                menuItem.setItemId(rs.getInt("item_id"));
                menuItem.setItemName(rs.getString("item_name"));
                menuItem.setPrice(rs.getDouble("price"));
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    // 2. b. Updating the price of the specified menu item
    public boolean updateMenuItemPrice(int item_id, double priceMultiplier) {
        String query = "UPDATE MenuItem SET price = price * ? WHERE item_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, priceMultiplier);
            stmt.setInt(2, item_id);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. c. Registering a new menu item
    public boolean addMenuItem(String item_name, String description, double price, String category, int inventory, String status) {
        String query = "INSERT INTO MenuItem (item_name, description, price, category, inventory, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, item_name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setString(4, category);
            stmt.setInt(5, inventory);
            stmt.setString(6, status);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. d. Deleting a menu item
    public boolean deleteMenuItem(int item_id) {
        String query = "DELETE FROM MenuItem WHERE item_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, item_id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}