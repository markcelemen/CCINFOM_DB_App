import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    // Main Panels
    private JPanel mainPanel;
    private JPanel transactionPanel;
    private JPanel reportPanel;

    // Main Buttons
    private JButton btnTransactions;
    private JButton btnReports;

    // Transaction Buttons
    private JButton btnPlaceOrder;
    private JButton btnUpdateMenuItemPrices;
    private JButton btnGeneratePayroll;

    // Report Buttons
    private JButton btnSalesReport;
    private JButton btnMenuItemPriceUpdateReport;
    private JButton btnEmployeePerformanceReport;

    // Constructor
    public View() {
        // Main Frame Configuration
        setTitle("Restaurant Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        // Initialize Panels
        mainPanel = new JPanel();
        transactionPanel = new JPanel();
        reportPanel = new JPanel();

        // Set Layouts
        mainPanel.setLayout(new GridLayout(2, 1, 10, 10));
        transactionPanel.setLayout(new GridLayout(3, 1, 10, 10));
        reportPanel.setLayout(new GridLayout(3, 1, 10, 10));

        // Initialize Main Buttons
        btnTransactions = new JButton("Transactions");
        btnReports = new JButton("Reports");

        // Add Main Buttons to Main Panel
        mainPanel.add(btnTransactions);
        mainPanel.add(btnReports);

        // Initialize Transaction Buttons
        btnPlaceOrder = new JButton("Place Order Transaction");
        btnUpdateMenuItemPrices = new JButton("Update Menu Item Prices Transaction");
        btnGeneratePayroll = new JButton("Generate Payroll Transactions");

        // Add Transaction Buttons to Transaction Panel
        transactionPanel.add(btnPlaceOrder);
        transactionPanel.add(btnUpdateMenuItemPrices);
        transactionPanel.add(btnGeneratePayroll);

        // Initialize Report Buttons
        btnSalesReport = new JButton("Sales Report");
        btnMenuItemPriceUpdateReport = new JButton("Menu Item Price Update Report");
        btnEmployeePerformanceReport = new JButton("Employee Performance Report");

        // Add Report Buttons to Report Panel
        reportPanel.add(btnSalesReport);
        reportPanel.add(btnMenuItemPriceUpdateReport);
        reportPanel.add(btnEmployeePerformanceReport);

        // Add Panels to Frame
        add(mainPanel, "Main");
        add(transactionPanel, "Transactions");
        add(reportPanel, "Reports");

        // Default to Main Panel
        showMainPanel();
    }

    // Display Main Panel
    public void showMainPanel() {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Main");
    }

    // Display Transaction Panel
    public void showTransactionPanel() {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Transactions");
    }

    // Display Report Panel
    public void showReportPanel() {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Reports");
    }

    // Getter Methods for Buttons
    public JButton getBtnTransactions() {
        return btnTransactions;
    }

    public JButton getBtnReports() {
        return btnReports;
    }

    public JButton getBtnPlaceOrder() {
        return btnPlaceOrder;
    }

    public JButton getBtnUpdateMenuItemPrices() {
        return btnUpdateMenuItemPrices;
    }

    public JButton getBtnGeneratePayroll() {
        return btnGeneratePayroll;
    }

    public JButton getBtnSalesReport() {
        return btnSalesReport;
    }

    public JButton getBtnMenuItemPriceUpdateReport() {
        return btnMenuItemPriceUpdateReport;
    }

    public JButton getBtnEmployeePerformanceReport() {
        return btnEmployeePerformanceReport;
    }

    // Attach Action Listeners
    public void addActionListener(ActionListener listener) {
        btnTransactions.addActionListener(listener);
        btnReports.addActionListener(listener);
        btnPlaceOrder.addActionListener(listener);
        btnUpdateMenuItemPrices.addActionListener(listener);
        btnGeneratePayroll.addActionListener(listener);
        btnSalesReport.addActionListener(listener);
        btnMenuItemPriceUpdateReport.addActionListener(listener);
        btnEmployeePerformanceReport.addActionListener(listener);
    }
}
