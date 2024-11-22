import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopSellingItemReportModel {
    private int itemId;
    private String itemName;
    private int totalQuantitySold;
    private double totalSales;

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

    public int getTotalQuantitySold() {
        return totalQuantitySold;
    }
    public void setTotalQuantitySold(int totalQuantitySold) {
        this.totalQuantitySold = totalQuantitySold;
    }

    public double getTotalSales() {
        return totalSales;
    }
    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    // SQL Query: Top Selling Menu Item by Day, Month, or Year
    public List<TopSellingItemReportModel> getTopSellingItems(String period, String startDate, String endDate) {
        List<TopSellingItemReportModel> items = new ArrayList<>();
        String query = "";

        switch (period.toLowerCase()) {
            case "day":
                query = "SELECT mi.item_id, mi.item_name, SUM(oi.quantity) AS total_quantity_sold, " +
                        "SUM(oi.quantity * oi.item_price) AS total_sales " +
                        "FROM OrderItem oi " +
                        "JOIN MenuItem mi ON oi.item_id = mi.item_id " +
                        "JOIN Orders o ON oi.order_id = o.order_id " +
                        "WHERE o.order_date = ? " +
                        "GROUP BY mi.item_id, mi.item_name " +
                        "ORDER BY total_sales DESC LIMIT 10";
                break;
            case "month":
                query = "SELECT mi.item_id, mi.item_name, SUM(oi.quantity) AS total_quantity_sold, " +
                        "SUM(oi.quantity * oi.item_price) AS total_sales " +
                        "FROM OrderItem oi " +
                        "JOIN MenuItem mi ON oi.item_id = mi.item_id " +
                        "JOIN Orders o ON oi.order_id = o.order_id " +
                        "WHERE o.order_date BETWEEN ? AND ? " +
                        "GROUP BY mi.item_id, mi.item_name " +
                        "ORDER BY total_sales DESC LIMIT 10";
                break;
            case "year":
                query = "SELECT mi.item_id, mi.item_name, SUM(oi.quantity) AS total_quantity_sold, " +
                        "SUM(oi.quantity * oi.item_price) AS total_sales " +
                        "FROM OrderItem oi " +
                        "JOIN MenuItem mi ON oi.item_id = mi.item_id " +
                        "JOIN Orders o ON oi.order_id = o.order_id " +
                        "WHERE o.order_date BETWEEN ? AND ? " +
                        "GROUP BY mi.item_id, mi.item_name " +
                        "ORDER BY total_sales DESC LIMIT 10";
                break;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TopSellingItemReportModel item = new TopSellingItemReportModel();
                item.setItemId(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setTotalQuantitySold(rs.getInt("total_quantity_sold"));
                item.setTotalSales(rs.getDouble("total_sales"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
