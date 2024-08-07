/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package view;

import Appql.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginForm() {
        setTitle("Đăng Nhập");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Tên đăng nhập"));
        txtUsername = new JTextField();
        add(txtUsername);

        add(new JLabel("Mật khẩu"));
        txtPassword = new JPasswordField();
        add(txtPassword);

        btnLogin = new JButton("Đăng nhập");
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                if (checkLogin(username, password)) {
                    openMainApp();
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Tên đăng nhập hoặc mật khẩu không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        txtPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLogin.doClick();
            }
        });
    }

    private boolean checkLogin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    private void openMainApp() {
        App app = new App();
        app.showMainApp();
        dispose();
    }
}

