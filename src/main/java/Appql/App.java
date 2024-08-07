package Appql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.QuanLyBenhNhan;
import view.QuanLyLichTuVan;
import view.QuanLyLoaiTuVan;
import view.QuanLyNguoiTuVan;
import view.QuanLyPhongTuVan;
import view.LoginForm;

public class App {
    private JFrame frame;
    private JPanel panel;

    public App() {
        
    }

    public void showMainApp() {
        
        frame = new JFrame("Quản Lý Phòng Tư Vấn Khám Bệnh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        
        JButton btnQuanLyBenhNhan = new JButton("Quản Lý Bệnh Nhân");
        JButton btnQuanLyLoaiTuVan = new JButton("Quản Lý Loại Tư Vấn");
        JButton btnQuanLyLichTuVan = new JButton("Quản Lý Lịch Tư Vấn");
        JButton btnQuanLyNguoiTuVan = new JButton("Quản Lý Người Tư Vấn");
        JButton btnQuanLyPhongTuVan = new JButton("Quản Lý Phòng Tư Vấn");

        
        panel.add(btnQuanLyBenhNhan);
        panel.add(btnQuanLyLoaiTuVan);
        panel.add(btnQuanLyLichTuVan);
        panel.add(btnQuanLyNguoiTuVan);
        panel.add(btnQuanLyPhongTuVan);

        
        frame.add(panel, BorderLayout.CENTER);

        
        btnQuanLyBenhNhan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QuanLyBenhNhan quanLyBenhNhan = new QuanLyBenhNhan();
                quanLyBenhNhan.setVisible(true);
            }
        });

        btnQuanLyLoaiTuVan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QuanLyLoaiTuVan quanLyLoaiTuVan = new QuanLyLoaiTuVan();
                quanLyLoaiTuVan.setVisible(true);
            }
        });

        btnQuanLyLichTuVan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QuanLyLichTuVan quanLyLichTuVan = new QuanLyLichTuVan();
                quanLyLichTuVan.setVisible(true);
            }
        });

        btnQuanLyNguoiTuVan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QuanLyNguoiTuVan quanLyNguoiTuVan = new QuanLyNguoiTuVan();
                quanLyNguoiTuVan.setVisible(true);
            }
        });

        btnQuanLyPhongTuVan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QuanLyPhongTuVan quanLyPhongTuVan = new QuanLyPhongTuVan();
                quanLyPhongTuVan.setVisible(true);
            }
        });

        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        });
    }
}
