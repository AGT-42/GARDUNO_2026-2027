import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RedBullVendingMachineGUI extends JFrame {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/vending_machine";
    private static final String DB_USER = "vending_user";
    private static final String DB_PASSWORD = "StrongPasswordHere";
    
    private Connection connection;
    private double storedMoney = 0.0;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextArea productDisplay;
    private JTextField productIdField, moneyField, adminUserField;
    private JPasswordField adminPassField;
    
    public RedBullVendingMachineGUI() {
        initDB();
        initGUI();
        loadProducts();
    }
    
    private void initDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private void initGUI() {
        setTitle("Red Bull Vending Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Color.BLACK);
        add(mainPanel);
        
        createCustomerPanel();
        createAdminLoginPanel();
        createAdminPanel();
        
        cardLayout.show(mainPanel, "customer");
    }
    
    private void createCustomerPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("Red Bull Vending Machine", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.RED);
        panel.add(title, BorderLayout.NORTH);
        
        productDisplay = new JTextArea(12, 40);
        productDisplay.setEditable(false);
        productDisplay.setFont(new Font("Monospaced", Font.PLAIN, 14));
        productDisplay.setBackground(Color.BLACK);
        productDisplay.setForeground(Color.RED);
        productDisplay.setBorder(BorderFactory.createLineBorder(Color.RED));
        JScrollPane scroll = new JScrollPane(productDisplay);
        panel.add(scroll, BorderLayout.CENTER);
        
        JPanel buttons = new JPanel(new GridLayout(1, 3, 10, 0));
        buttons.setBackground(Color.BLACK);
        
        JButton viewBtn = new JButton("View Products");
        styleButton(viewBtn, Color.RED, Color.WHITE);
        viewBtn.addActionListener(e -> loadProducts());
        
        JButton buyBtn = new JButton("Buy Product");
        styleButton(buyBtn, Color.RED, Color.WHITE);
        buyBtn.addActionListener(e -> cardLayout.show(mainPanel, "buy"));
        
        JButton adminBtn = new JButton("Admin Login");
        styleButton(adminBtn, Color.RED, Color.WHITE);
        adminBtn.addActionListener(e -> cardLayout.show(mainPanel, "adminLogin"));
        
        buttons.add(viewBtn);
        buttons.add(buyBtn);
        buttons.add(adminBtn);
        panel.add(buttons, BorderLayout.SOUTH);
        
        mainPanel.add(panel, "customer");
    }
    
    private void createAdminLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;
        
        JLabel title = new JLabel("Admin Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.RED);
        c.gridwidth = 2;
        c.gridy = 0;
        panel.add(title, c);
        
        c.gridwidth = 1;
        c.gridy = 1;
        panel.add(new JLabel("Username:"), c);
        c.gridx = 1;
        adminUserField = new JTextField(20);
        panel.add(adminUserField, c);
        
        c.gridx = 0;
        c.gridy = 2;
        panel.add(new JLabel("Password:"), c);
        c.gridx = 1;
        adminPassField = new JPasswordField(20);
        panel.add(adminPassField, c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        JButton loginBtn = new JButton("Login");
        styleButton(loginBtn, Color.RED, Color.WHITE);
        loginBtn.addActionListener(e -> adminLogin());
        panel.add(loginBtn, c);
        
        c.gridy = 4;
        JButton backBtn = new JButton("Back");
        styleButton(backBtn, Color.BLACK, Color.RED);
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "customer"));
        panel.add(backBtn, c);
        
        mainPanel.add(panel, "adminLogin");
    }
    
    private void createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        
        JLabel title = new JLabel("Admin Panel", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.RED);
        panel.add(title, BorderLayout.NORTH);
        
        JPanel buttons = new JPanel(new GridLayout(4, 2, 10, 10));
        buttons.setBackground(Color.BLACK);
        buttons.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        String[] labels = {"Add Product", "View Products", "Edit Product", "Delete Product", 
                          "Collect Money", "Register Admin", "", "Logout"};
        String[] actions = {"add", "view", "edit", "delete", "collect", "register", "", "logout"};
        
        for (int i = 0; i < labels.length; i++) {
            if (labels[i].isEmpty()) continue;
            
            JButton btn = new JButton(labels[i]);
            styleButton(btn, Color.RED, Color.WHITE);
            final String action = actions[i];
            btn.addActionListener(e -> handleAdminAction(action));
            buttons.add(btn);
        }
        
        panel.add(buttons);
        mainPanel.add(panel, "admin");
    }
    
    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }
    
    private void loadProducts() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM products");
            ResultSet rs = stmt.executeQuery();
            
            StringBuilder sb = new StringBuilder("Available Products:\n\n");
            while (rs.next()) {
                sb.append(rs.getInt("id")).append(". ")
                  .append(rs.getString("name")).append(" - $")
                  .append(rs.getDouble("price")).append(" (Stock: ")
                  .append(rs.getInt("stock")).append(")\n");
            }
            productDisplay.setText(sb.toString());
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            productDisplay.setText("Error: " + e.getMessage());
        }
    }
    
    private void adminLogin() {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM admins WHERE username = ? AND password = ?");
            stmt.setString(1, adminUserField.getText());
            stmt.setString(2, new String(adminPassField.getPassword()));
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                cardLayout.show(mainPanel, "admin");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Login error: " + e.getMessage());
        }
    }
    
    private void handleAdminAction(String action) {
        try {
            switch (action) {
                case "view":
                    loadProducts();
                    break;
                case "collect":
                    JOptionPane.showMessageDialog(this, "Collected: $" + storedMoney);
                    storedMoney = 0;
                    break;
                case "logout":
                    cardLayout.show(mainPanel, "customer");
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Function: " + action + " (implement as needed)");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RedBullVendingMachineGUI().setVisible(true));
    }
}