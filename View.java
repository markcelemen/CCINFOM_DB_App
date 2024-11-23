import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JPanel mainPanel;
    private JButton btnTransactions, btnReports;
    private JPanel transactionsPanel, reportsPanel;
    private JTextArea sqlQueryDisplay;
    
    public View() {
        setTitle("Restaurant Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());

        // Initial buttons for Transactions and Reports
        btnTransactions = new JButton("Transactions");
        btnReports = new JButton("Reports");
        
        mainPanel.add(btnTransactions, BorderLayout.NORTH);
        mainPanel.add(btnReports, BorderLayout.SOUTH);

        // Panels for Transactions and Reports
        transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));
        
        btnTransactions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTransactionsOptions();
            }
        });

        reportsPanel = new JPanel();
        reportsPanel.setLayout(new BoxLayout(reportsPanel, BoxLayout.Y_AXIS));
        
        btnReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayReportsOptions();
            }
        });

        sqlQueryDisplay = new JTextArea(5, 40);
        sqlQueryDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(sqlQueryDisplay);
        
        mainPanel.add(transactionsPanel, "TransactionsPanel");
        mainPanel.add(reportsPanel, "ReportsPanel");
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(mainPanel);
        
        setVisible(true);
    }

    // Display Transactions options
    private void displayTransactionsOptions() {
        transactionsPanel.removeAll();
        
        JButton btnPlaceOrder = new JButton("Place Order");
        JButton btnUpdateMenu = new JButton("Update Menu Item Prices");
        JButton btnGeneratePayroll = new JButton("Generate Payroll");
        JButton btnReturn = new JButton("Return");

        transactionsPanel.add(btnPlaceOrder);
        transactionsPanel.add(btnUpdateMenu);
        transactionsPanel.add(btnGeneratePayroll);
        transactionsPanel.add(btnReturn);
        
        btnPlaceOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPlaceOrderOptions();
            }
        });

        btnUpdateMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUpdateMenuOptions();
            }
        });

        btnGeneratePayroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayGeneratePayrollOptions();
            }
        });

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMainPanel();
            }
        });

        transactionsPanel.revalidate();
        transactionsPanel.repaint();
    }

    // Display Place Order options
    private void displayPlaceOrderOptions() {
        transactionsPanel.removeAll();
        
        JButton btnVerifyCustomer = new JButton("Reading a customer record to verify details");
        JButton btnPopulateOrder = new JButton("Reading menu items to populate the order");
        JButton btnUpdateTotalAmount = new JButton("Updating the total amount of the order");
        JButton btnReturn = new JButton("Return");

        transactionsPanel.add(btnVerifyCustomer);
        transactionsPanel.add(btnPopulateOrder);
        transactionsPanel.add(btnUpdateTotalAmount);
        transactionsPanel.add(btnReturn);
        
        btnVerifyCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCustomerVerification();
            }
        });

        btnPopulateOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayOrderPopulation();
            }
        });

        btnUpdateTotalAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayOrderTotalUpdate();
            }
        });

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTransactionsOptions();
            }
        });

        transactionsPanel.revalidate();
        transactionsPanel.repaint();
    }

    // Customer Verification
    private void displayCustomerVerification() {
        String customerId = JOptionPane.showInputDialog("Enter Customer ID:");
        String query = "SELECT * FROM Customers WHERE customer_id = " + customerId;
        sqlQueryDisplay.setText(query);
    }

    // Populate Order
    private void displayOrderPopulation() {
        // Similar to Customer Verification
        String query = "SELECT * FROM MenuItems";
        sqlQueryDisplay.setText(query);
    }

    // Update Total Amount of Order
    private void displayOrderTotalUpdate() {
        String orderId = JOptionPane.showInputDialog("Enter Order ID:");
        String query = "UPDATE Orders SET total_amount = ? WHERE order_id = " + orderId;
        sqlQueryDisplay.setText(query);
    }

    // Update Menu Options
    private void displayUpdateMenuOptions() {
        transactionsPanel.removeAll();

        JButton btnReadItem = new JButton("Reading a menu item record based on price");
        JButton btnUpdateItemPrice = new JButton("Updating the price of the specified menu item");
        JButton btnRegisterNewItem = new JButton("Registering a new menu item");
        JButton btnDeleteItem = new JButton("Deleting a menu item");
        JButton btnReturn = new JButton("Return");

        transactionsPanel.add(btnReadItem);
        transactionsPanel.add(btnUpdateItemPrice);
        transactionsPanel.add(btnRegisterNewItem);
        transactionsPanel.add(btnDeleteItem);
        transactionsPanel.add(btnReturn);

        btnReadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayItemPriceUpdate();
            }
        });

        btnUpdateItemPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPriceUpdate();
            }
        });

        btnRegisterNewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRegisterNewItem();
            }
        });

        btnDeleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayDeleteMenuItem();
            }
        });

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTransactionsOptions();
            }
        });

        transactionsPanel.revalidate();
        transactionsPanel.repaint();
    }

    // Display SQL for Updating Menu Item Price
    private void displayItemPriceUpdate() {
        String itemId = JOptionPane.showInputDialog("Enter Menu Item ID to update price:");
        String query = "SELECT * FROM MenuItems WHERE item_id = " + itemId;
        sqlQueryDisplay.setText(query);
    }

    private void displayPriceUpdate() {
        String itemId = JOptionPane.showInputDialog("Enter Menu Item ID to update price:");
        String newPrice = JOptionPane.showInputDialog("Enter new price:");
        String query = "UPDATE MenuItems SET price = " + newPrice + " WHERE item_id = " + itemId;
        sqlQueryDisplay.setText(query);
    }

    private void displayRegisterNewItem() {
        String query = "INSERT INTO MenuItems (name, price, description) VALUES (?, ?, ?)";
        sqlQueryDisplay.setText(query);
    }

    private void displayDeleteMenuItem() {
        String itemId = JOptionPane.showInputDialog("Enter Menu Item ID to delete:");
        String query = "DELETE FROM MenuItems WHERE item_id = " + itemId;
        sqlQueryDisplay.setText(query);
    }

    // Generate Payroll Options
    private void displayGeneratePayrollOptions() {
        transactionsPanel.removeAll();

        JButton btnReadEmployee = new JButton("Reading employee records");
        JButton btnDeleteEmployee = new JButton("Deleting an employee record");
        JButton btnUpdatePayroll = new JButton("Updating payroll records based on position");
        JButton btnReturn = new JButton("Return");

        transactionsPanel.add(btnReadEmployee);
        transactionsPanel.add(btnDeleteEmployee);
        transactionsPanel.add(btnUpdatePayroll);
        transactionsPanel.add(btnReturn);

        btnReadEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayReadEmployee();
            }
        });

        btnDeleteEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayDeleteEmployee();
            }
        });

        btnUpdatePayroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUpdatePayroll();
            }
        });

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTransactionsOptions();
            }
        });

        transactionsPanel.revalidate();
        transactionsPanel.repaint();
    }

    // SQL for Reading Employee Records
    private void displayReadEmployee() {
        String query = "SELECT * FROM Employees";
        sqlQueryDisplay.setText(query);
    }

    // SQL for Deleting an Employee Record
    private void displayDeleteEmployee() {
        String empId = JOptionPane.showInputDialog("Enter Employee ID to delete:");
        String query = "DELETE FROM Employees WHERE emp_id = " + empId;
        sqlQueryDisplay.setText(query);
    }

    // SQL for Updating Payroll Records
    private void displayUpdatePayroll() {
        String empId = JOptionPane.showInputDialog("Enter Employee ID to update payroll:");
        String newSalary = JOptionPane.showInputDialog("Enter new salary:");
        String query = "UPDATE Payroll SET salary = " + newSalary + " WHERE emp_id = " + empId;
        sqlQueryDisplay.setText(query);
    }

    // Display Reports Options
    private void displayReportsOptions() {
        reportsPanel.removeAll();
        
        JButton btnSalesReport = new JButton("Sales Report");
        JButton btnTopSellingMenuItemReport = new JButton("Top Selling Menu Item Report");
        JButton btnEmployeePerformanceReport = new JButton("Employee Performance Report");
        JButton btnReturn = new JButton("Return");

        reportsPanel.add(btnSalesReport);
        reportsPanel.add(btnTopSellingMenuItemReport);
        reportsPanel.add(btnEmployeePerformanceReport);
        reportsPanel.add(btnReturn);

        btnSalesReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySalesReport();
            }
        });

        btnTopSellingMenuItemReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTopSellingMenuItemReport();
            }
        });

        btnEmployeePerformanceReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayEmployeePerformanceReport();
            }
        });

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMainPanel();
            }
        });

        reportsPanel.revalidate();
        reportsPanel.repaint();
    }

    // Display Sales Report
    private void displaySalesReport() {
        reportsPanel.removeAll();
        
        JButton btnDayReport = new JButton("Sales Report Each Day");
        JButton btnMonthReport = new JButton("Sales Report Each Month");
        JButton btnYearReport = new JButton("Sales Report Each Year");
        
        reportsPanel.add(btnDayReport);
        reportsPanel.add(btnMonthReport);
        reportsPanel.add(btnYearReport);

        btnDayReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySalesReportForPeriod("Day");
            }
        });

        btnMonthReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySalesReportForPeriod("Month");
            }
        });

        btnYearReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySalesReportForPeriod("Year");
            }
        });

        reportsPanel.revalidate();
        reportsPanel.repaint();
    }

    private void displaySalesReportForPeriod(String period) {
        String query = "SELECT * FROM Sales WHERE period = '" + period + "'";
        sqlQueryDisplay.setText(query);
    }

    // Display Top Selling Menu Item Report
    private void displayTopSellingMenuItemReport() {
        String period = JOptionPane.showInputDialog("Enter period (Day, Month, Year):");
        String query = "SELECT * FROM MenuItems WHERE period = '" + period + "' ORDER BY sales DESC";
        sqlQueryDisplay.setText(query);
    }

    // Display Employee Performance Report
    private void displayEmployeePerformanceReport() {
        String period = JOptionPane.showInputDialog("Enter period (Day, Month, Year):");
        String query = "SELECT * FROM EmployeePerformance WHERE period = '" + period + "'";
        sqlQueryDisplay.setText(query);
    }

    // Show Main Panel
    private void showMainPanel() {
        mainPanel.removeAll();
        mainPanel.add(btnTransactions, BorderLayout.NORTH);
        mainPanel.add(btnReports, BorderLayout.SOUTH);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        new View();
    }
}