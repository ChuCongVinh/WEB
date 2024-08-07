package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuanLyNguoiTuVan extends JFrame {
    private JTextField txtTen, txtChuyenMon, txtTimKiem;
    private JButton btnThem, btnCapNhat, btnXoa, btnTimKiem;
    private JList<NguoiTuVan> listNguoiTuVan;
    private DefaultListModel<NguoiTuVan> listModel;
    private ArrayList<NguoiTuVan> danhSachNguoiTuVan;

    public QuanLyNguoiTuVan() {
        setTitle("Quản Lý Người Tư Vấn");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(3, 2));
        txtTen = new JTextField();
        txtChuyenMon = new JTextField();
        txtTimKiem = new JTextField();

        panelForm.add(new JLabel("Tên"));
        panelForm.add(txtTen);
        panelForm.add(new JLabel("Chuyên Môn (cách nhau bởi dấu phẩy)"));
        panelForm.add(txtChuyenMon);
        panelForm.add(new JLabel("Tìm kiếm theo tên"));
        panelForm.add(txtTimKiem);

        add(panelForm, BorderLayout.NORTH);

        JPanel panelButtons = new JPanel(new GridLayout(1, 4));
        btnThem = new JButton("Thêm");
        btnCapNhat = new JButton("Cập Nhật");
        btnXoa = new JButton("Xóa");
        btnTimKiem = new JButton("Tìm Kiếm");

        panelButtons.add(btnThem);
        panelButtons.add(btnCapNhat);
        panelButtons.add(btnXoa);
        panelButtons.add(btnTimKiem);

        add(panelButtons, BorderLayout.CENTER);

        listModel = new DefaultListModel<>();
        listNguoiTuVan = new JList<>(listModel);
        add(new JScrollPane(listNguoiTuVan), BorderLayout.SOUTH);

        danhSachNguoiTuVan = new ArrayList<>();
        danhSachNguoiTuVan.add(new NguoiTuVan("Bác Sĩ Nguyến Văn Bình", Arrays.asList("Tim Mạch", "Hô Hấp")));
        danhSachNguoiTuVan.add(new NguoiTuVan("Bác Sĩ Trần Đình Tùng", Arrays.asList("Nội Tiết", "Tiêu Hóa")));
        
        loadNguoiTuVanList();

        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                themNguoiTuVan();
            }
        });

        btnCapNhat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                capNhatNguoiTuVan();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xoaNguoiTuVan();
            }
        });

        btnTimKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timKiemNguoiTuVan();
            }
        });

        listNguoiTuVan.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && listNguoiTuVan.getSelectedIndex() != -1) {
                NguoiTuVan selectedNguoiTuVan = listNguoiTuVan.getSelectedValue();
                if (selectedNguoiTuVan != null) {
                    txtTen.setText(selectedNguoiTuVan.getTen());
                    txtChuyenMon.setText(String.join(",", selectedNguoiTuVan.getChuyenMon()));
                }
            }
        });
    }

    private void loadNguoiTuVanList() {
        listModel.clear();
        for (NguoiTuVan nguoiTuVan : danhSachNguoiTuVan) {
            listModel.addElement(nguoiTuVan);
        }
    }

    private void themNguoiTuVan() {
        try {
            String ten = txtTen.getText();
            String chuyenMonStr = txtChuyenMon.getText();
            List<String> chuyenMon = Arrays.asList(chuyenMonStr.split(","));

            NguoiTuVan nguoiTuVan = new NguoiTuVan(ten, chuyenMon);
            danhSachNguoiTuVan.add(nguoiTuVan);
            listModel.addElement(nguoiTuVan);
            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void capNhatNguoiTuVan() {
        try {
            int selectedIndex = listNguoiTuVan.getSelectedIndex();
            if (selectedIndex != -1) {
                NguoiTuVan selectedNguoiTuVan = listNguoiTuVan.getSelectedValue();
                if (selectedNguoiTuVan != null) {
                    selectedNguoiTuVan.setTen(txtTen.getText());
                    selectedNguoiTuVan.setChuyenMon(Arrays.asList(txtChuyenMon.getText().split(",")));
                    listNguoiTuVan.repaint();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn người tư vấn để cập nhật");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void xoaNguoiTuVan() {
        try {
            int selectedIndex = listNguoiTuVan.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
                danhSachNguoiTuVan.remove(selectedIndex);
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn người tư vấn để xóa");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void timKiemNguoiTuVan() {
        String searchText = txtTimKiem.getText().toLowerCase();
        listModel.clear();
        for (NguoiTuVan nguoiTuVan : danhSachNguoiTuVan) {
            if (nguoiTuVan.getTen().toLowerCase().contains(searchText)) {
                listModel.addElement(nguoiTuVan);
            }
        }
    }

    private void clearForm() {
        txtTen.setText("");
        txtChuyenMon.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuanLyNguoiTuVan app = new QuanLyNguoiTuVan();
            app.setVisible(true);
        });
    }

    private class NguoiTuVan {
        private static int currentId = 1; // ID bắt đầu từ 1

        private String id;
        private String ten;
        private List<String> chuyenMon;

        public NguoiTuVan(String ten, List<String> chuyenMon) {
            this.id = String.valueOf(currentId++); // Tự động tăng ID
            this.ten = ten;
            this.chuyenMon = chuyenMon;
        }

        public String getId() {
            return id;
        }

        public String getTen() {
            return ten;
        }

        public void setTen(String ten) {
            this.ten = ten;
        }

        public List<String> getChuyenMon() {
            return chuyenMon;
        }

        public void setChuyenMon(List<String> chuyenMon) {
            this.chuyenMon = chuyenMon;
        }

        @Override
        public String toString() {
            return "NguoiTuVan{" +
                    "ID='" + id + '\'' +
                    ", Tên='" + ten + '\'' +
                    ", Chuyên Môn=" + String.join(", ", chuyenMon) +
                    '}';
        }
    }
}
