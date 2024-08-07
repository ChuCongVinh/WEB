package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuanLyPhongTuVan extends JFrame {
    private JButton btnQuanLyBenhNhan, btnQuanLyNguoiTuVan, btnQuanLyLoaiTuVan, btnQuanLyLichTuVan, btnQuanLyPhongTuVan;

    public QuanLyPhongTuVan() {
        setTitle("Hệ Thống Quản Lý Phòng Tư Vấn Khám Bệnh");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 1));

        btnQuanLyBenhNhan = new JButton("Quản Lý Bệnh Nhân");
        btnQuanLyNguoiTuVan = new JButton("Quản Lý Người Tư Vấn");
        btnQuanLyLoaiTuVan = new JButton("Quản Lý Loại Tư Vấn");
        btnQuanLyLichTuVan = new JButton("Quản Lý Lịch Tư Vấn");
        btnQuanLyPhongTuVan = new JButton("Quản Lý Phòng Tư Vấn");

        add(btnQuanLyBenhNhan);
        add(btnQuanLyNguoiTuVan);
        add(btnQuanLyLoaiTuVan);
        add(btnQuanLyLichTuVan);

        btnQuanLyBenhNhan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new QuanLyBenhNhan().setVisible(true);
            }
        });

        btnQuanLyNguoiTuVan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new QuanLyNguoiTuVan().setVisible(true);
            }
        });

        btnQuanLyLoaiTuVan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new QuanLyLoaiTuVan().setVisible(true);
            }
        });

        btnQuanLyPhongTuVan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                JOptionPane.showMessageDialog(null, "Đang mở chức năng Quản Lý Phòng Tư Vấn");
            }
        });

        btnQuanLyLichTuVan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new QuanLyLichTuVan().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuanLyPhongTuVan app = new QuanLyPhongTuVan();
            app.setVisible(true);
        });
    }
}
