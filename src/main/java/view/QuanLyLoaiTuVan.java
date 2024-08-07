package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QuanLyLoaiTuVan extends JFrame {
    private JTextField txtTenLoai;
    private JButton btnThem, btnCapNhat, btnXoa, btnTimKiem;
    private JList<LoaiTuVan> listLoaiTuVan;
    private DefaultListModel<LoaiTuVan> listModel;
    private ArrayList<LoaiTuVan> danhSachLoaiTuVan;
    private int nextId = 1;

    public QuanLyLoaiTuVan() {
        setTitle("Quản Lý Loại Tư Vấn");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

       
        JPanel panelForm = new JPanel(new GridLayout(2, 2));
        txtTenLoai = new JTextField();

        panelForm.add(new JLabel("Tên Loại"));
        panelForm.add(txtTenLoai);

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
        listLoaiTuVan = new JList<>(listModel);
        add(new JScrollPane(listLoaiTuVan), BorderLayout.SOUTH);

        
        danhSachLoaiTuVan = new ArrayList<>();
        danhSachLoaiTuVan.add(new LoaiTuVan(nextId++, "Tư Vấn Tâm Lý"));
        danhSachLoaiTuVan.add(new LoaiTuVan(nextId++, "Tư Vấn Sức Khỏe"));

        loadLoaiTuVanList();

        
        btnThem.addActionListener(e -> themLoaiTuVan());
        btnCapNhat.addActionListener(e -> capNhatLoaiTuVan());
        btnXoa.addActionListener(e -> xoaLoaiTuVan());
        btnTimKiem.addActionListener(e -> timKiemLoaiTuVan());
    }

    private void loadLoaiTuVanList() {
        listModel.clear();
        for (LoaiTuVan loaiTuVan : danhSachLoaiTuVan) {
            listModel.addElement(loaiTuVan);
        }
    }

    private void themLoaiTuVan() {
        String tenLoai = txtTenLoai.getText();
        danhSachLoaiTuVan.add(new LoaiTuVan(nextId++, tenLoai));
        loadLoaiTuVanList();
        clearFields();
    }

    private void capNhatLoaiTuVan() {
        int selectedIndex = listLoaiTuVan.getSelectedIndex();
        if (selectedIndex != -1) {
            LoaiTuVan selectedLoaiTuVan = listModel.getElementAt(selectedIndex);

            JTextField txtTenLoai = new JTextField(selectedLoaiTuVan.getTenLoai());

            JPanel panel = new JPanel(new GridLayout(2, 2));
            panel.add(new JLabel("Tên Loại"));
            panel.add(txtTenLoai);

            int result = JOptionPane.showConfirmDialog(this, panel, "Cập Nhật Loại Tư Vấn",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                selectedLoaiTuVan.setTenLoai(txtTenLoai.getText());
                listModel.set(selectedIndex, selectedLoaiTuVan);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một mục để cập nhật.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void xoaLoaiTuVan() {
        int selectedIndex = listLoaiTuVan.getSelectedIndex();
        if (selectedIndex != -1) {
            danhSachLoaiTuVan.remove(selectedIndex);
            loadLoaiTuVanList();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một mục để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void timKiemLoaiTuVan() {
        String keyword = JOptionPane.showInputDialog(this, "Nhập từ khóa tìm kiếm:", "Tìm Kiếm Loại Tư Vấn", JOptionPane.QUESTION_MESSAGE);
        if (keyword != null && !keyword.trim().isEmpty()) {
            ArrayList<LoaiTuVan> resultList = new ArrayList<>();
            for (LoaiTuVan loaiTuVan : danhSachLoaiTuVan) {
                if (loaiTuVan.getTenLoai().contains(keyword)) {
                    resultList.add(loaiTuVan);
                }
            }
            if (!resultList.isEmpty()) {
                listModel.clear();
                for (LoaiTuVan loaiTuVan : resultList) {
                    listModel.addElement(loaiTuVan);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả phù hợp.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearFields() {
        txtTenLoai.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuanLyLoaiTuVan().setVisible(true));
    }

    private class LoaiTuVan {
        private int id;
        private String tenLoai;

        public LoaiTuVan(int id, String tenLoai) {
            this.id = id;
            this.tenLoai = tenLoai;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTenLoai() {
            return tenLoai;
        }

        public void setTenLoai(String tenLoai) {
            this.tenLoai = tenLoai;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Tên Loại: " + tenLoai;
        }
    }
}


