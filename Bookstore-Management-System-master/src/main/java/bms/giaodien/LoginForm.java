package bms.giaodien;

import bms.work.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import java.net.URL;

public class LoginForm extends JFrame {

    public LoginForm() {
        setTitle("Đăng nhập hệ thống quản lý BMS");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tải hình nền
        URL imgUrl = getClass().getResource("login.png");
        Image bgImage = imgUrl != null ? new ImageIcon(imgUrl).getImage() : new ImageIcon("src/main/java/bms/giaodien/login.png").getImage();

        // Tạo panel nền và vẽ hình ảnh
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Tạo khối đăng nhập
        JPanel loginBox = new JPanel(new GridBagLayout());
        loginBox.setBackground(Color.WHITE);
        loginBox.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel lblTitle = new JLabel("Đăng nhập");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginBox.add(lblTitle, gbc);

        // Ô nhập Email
        JTextField txtEmail = new JTextField();
        txtEmail.setBorder(BorderFactory.createTitledBorder("Tài khoản"));
        txtEmail.setPreferredSize(new Dimension(250, 45));
        gbc.gridy = 1;
        loginBox.add(txtEmail, gbc);

        // Ô nhập Password
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBorder(BorderFactory.createTitledBorder("Mật khẩu"));
        txtPassword.setPreferredSize(new Dimension(250, 45));
        gbc.gridy = 2;
        loginBox.add(txtPassword, gbc);

        // Nút Login
        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.setBackground(new Color(76, 175, 80));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setFocusPainted(false);
        gbc.gridy = 3;
        loginBox.add(btnLogin, gbc);

        // Quên mật khẩu
        JLabel lblForgot = new JLabel("Quên mật khẩu?");
        lblForgot.setFont(new Font("Arial", Font.PLAIN, 12));
        lblForgot.setForeground(Color.GRAY);
        lblForgot.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 4;
        loginBox.add(lblForgot, gbc);

        // Thêm khối đăng nhập vào panel nền
        backgroundPanel.add(loginBox);
        add(backgroundPanel);

        // Hành động nút Login
        btnLogin.addActionListener((ActionEvent e) -> {
            String username = txtEmail.getText();
            String password = new String(txtPassword.getPassword());
            Login log1 = new Login();
            try {
                if (log1.checkLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    SwingUtilities.invokeLater(() -> {
                        try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                            UIManager.put("Button.arc", 10);
                            UIManager.put("Component.arc", 10);
                            UIManager.put("TextComponent.arc", 10);
                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {}
                        
                        try {
                            new MenuForm(username, log1.getID(username)).setVisible(true);
                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        dispose();
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Hành động Quên mật khẩu
        lblForgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ForgotPasswordForm.openForgotPasswordForm();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginForm loginUI = new LoginForm();
            loginUI.setVisible(true);
        });
    }
}