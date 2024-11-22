import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesReportModel {
    private Date reportDate;
    private double totalSales;
    private double averageSales;

    // Getters and setters
    public Date getReportDate() { 
        return reportDate; 
    }
    public void setReportDate(Date reportDate) { 
        this.reportDate = reportDate; 
    }

    public double getTotalSales() { 
        return totalSales; 
    }
    public void setTotalSales(double totalSales) { 
        this.totalSales = totalSales; 
    }

    public double getAverageSales() { 
        return averageSales; 
    }
    public void setAverageSales(double averageSales) { 
        this.averageSales = averageSales; 
    }

    // SQL Query: Sales Report by Day, Month, or Year
    public List<SalesReportModel> getSalesReport(String period) {
        List<SalesReportModel> reports = new ArrayList<>();
        String query = "";
        
        switch (period.toLowerCase()) {
            case "day":
                query = "SELECT o.order_date AS report_date, SUM(o.total_amount) AS total_sales, " +
                        "AVG(o.total_amount) AS average_sales " +
                        "FROM Orders o " +
                        "GROUP BY o.order_date " +
                        "ORDER BY o.order_date";
                break;
            case "month":
                query = "SELECT YEAR(o.order_date) AS year, MONTH(o.order_date) AS month, " +
                        "SUM(o.total_amount) AS total_sales, AVG(o.total_amount) AS average_sales " +
                        "FROM Orders o " +
                        "GROUP BY YEAR(o.order_date), MONTH(o.order_date) " +
                        "ORDER BY YEAR(o.order_date), MONTH(o.order_date)";
                break;
            case "year":
                query = "SELECT YEAR(o.order_date) AS year, SUM(o.total_amount) AS total_sales, " +
                        "AVG(o.total_amount) AS average_sales " +
                        "FROM Orders o " +
                        "GROUP BY YEAR(o.order_date) " +
                        "ORDER BY YEAR(o.order_date)";
                break;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SalesReportModel report = new SalesReportModel();
                if (period.equals("day")) {
                    report.setReportDate(rs.getDate("report_date"));
                } else {
                    report.setReportDate(rs.getDate("year")); // For month and year, use Date as placeholder
                }
                report.setTotalSales(rs.getDouble("total_sales"));
                report.setAverageSales(rs.getDouble("average_sales"));
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }
}