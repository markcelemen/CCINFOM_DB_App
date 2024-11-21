import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemPriceUpdateReportModel {
    private Date update_date;
    private String item_name;
    private double old_price;
    private double new_price;

    // Getters and setters
    public Date getUpdateDate() { 
        return update_date; 
    }
    public void setUpdateDate(Date update_date) { 
        this.update_date = update_date; 
    }

    public String getItemName() { 
        return item_name; 
    }
    public void setItemName(String item_name) { 
        this.item_name = item_name; 
    }

    public double getOldPrice() { 
        return old_price; 
    }
    public void setOldPrice(double old_price) { 
        this.old_price = old_price; 
    }

    public double getNewPrice() { 
        return new_price; 
    }
    public void setNewPrice(double new_price) { 
        this.new_price = new_price; 
    }

    // SQL Query: Menu Item Price Update Report by Day, Month, or Year
    public List<MenuItemPriceUpdateReportModel> getMenuItemPriceUpdates(String period) {
        List<MenuItemPriceUpdateReportModel> reports = new ArrayList<>();
        String query = "";

        switch (period.toLowerCase()) {
            case "day":
                query = "SELECT pi.update_date, m.item_name, pi.old_price, pi.new_price " +
                        "FROM PriceHistory pi " +
                        "JOIN MenuItem m ON pi.item_id = m.item_id " +
                        "WHERE DATE(pi.update_date) = CURDATE()";
                break;
            case "month":
                query = "SELECT pi.update_date, m.item_name, pi.old_price, pi.new_price " +
                        "FROM PriceHistory pi " +
                        "JOIN MenuItem m ON pi.item_id = m.item_id " +
                        "WHERE MONTH(pi.update_date) = MONTH(CURDATE())";
                break;
            case "year":
                query = "SELECT pi.update_date, m.item_name, pi.old_price, pi.new_price " +
                        "FROM PriceHistory pi " +
                        "JOIN MenuItem m ON pi.item_id = m.item_id " +
                        "WHERE YEAR(pi.update_date) = YEAR(CURDATE())";
                break;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MenuItemPriceUpdateReportModel report = new MenuItemPriceUpdateReportModel();
                report.setUpdateDate(rs.getDate("update_date"));
                report.setItemName(rs.getString("item_name"));
                report.setOldPrice(rs.getDouble("old_price"));
                report.setNewPrice(rs.getDouble("new_price"));
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }
}