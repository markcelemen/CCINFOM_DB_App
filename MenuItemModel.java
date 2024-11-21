import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemModel {

    // 1. a. Reading available menu items to populate the order
    public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT m.item_id, m.item_name, m.description, m.price, m.category, m.inventory " +
                       "FROM MenuItem m";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setItemId(rs.getInt("item_id"));
                menuItem.setItemName(rs.getString("item_name"));
                menuItem.setDescription(rs.getString("description"));
                menuItem.setPrice(rs.getDouble("price"));
                menuItem.setCategory(rs.getString("category"));
                menuItem.setInventory(rs.getInt("inventory"));
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItems;
    }

    // 2. a. Adding a new menu item
    public boolean addMenuItem(MenuItem menuItem) {
        String query = "INSERT INTO MenuItem (item_name, description, price, category, inventory) " +
                       "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, menuItem.getItemName());
            stmt.setString(2, menuItem.getDescription());
            stmt.setDouble(3, menuItem.getPrice());
            stmt.setString(4, menuItem.getCategory());
            stmt.setInt(5, menuItem.getInventory());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. a. Updating the price of a menu item
    public boolean updateMenuItemPrice(int itemId, double price) {
        String query = "UPDATE MenuItem SET price = ? WHERE item_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, price);
            stmt.setInt(2, itemId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. a. Updating the inventory of a menu item
    public boolean updateMenuItemInventory(int itemId, int inventory) {
        String query = "UPDATE MenuItem SET inventory = ? WHERE item_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, inventory);
            stmt.setInt(2, itemId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5. a. Deleting a menu item
    public boolean deleteMenuItem(int itemId) {
        String query = "DELETE FROM MenuItem WHERE item_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, itemId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // MenuItem Class
    public static class MenuItem {
        private int itemId;
        private String itemName;
        private String description;
        private double price;
        private String category;
        private int inventory;

        // Getters and setters
        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }
    }
}
