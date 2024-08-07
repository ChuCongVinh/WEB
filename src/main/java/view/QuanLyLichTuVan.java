package view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class QuanLyLichTuVan extends JFrame {
    private JTextField txtThoiGian, txtPhongTuVan, txtNguoiTuVan, txtBenhNhan;
    private JButton btnThem, btnCapNhat, btnXoa, btnTimKiem;
    private JList<LichTuVan> listLichTuVan;
    private DefaultListModel<LichTuVan> listModel;
    private ArrayList<LichTuVan> danhSachLichTuVan;
    private int nextId = 1;

    public QuanLyLichTuVan() {
        setTitle("Quản Lý Lịch Tư Vấn");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JPanel panelForm = new JPanel(new GridLayout(4, 2));
        txtThoiGian = new JTextField();
        txtPhongTuVan = new JTextField();
        txtNguoiTuVan = new JTextField();
        txtBenhNhan = new JTextField();

        panelForm.add(new JLabel("Thời Gian"));
        panelForm.add(txtThoiGian);
        panelForm.add(new JLabel("Phòng Tư Vấn"));
        panelForm.add(txtPhongTuVan);
        panelForm.add(new JLabel("Người Tư Vấn"));
        panelForm.add(txtNguoiTuVan);
        panelForm.add(new JLabel("Bệnh Nhân"));
        panelForm.add(txtBenhNhan);

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
        listLichTuVan = new JList<>(listModel);
        add(new JScrollPane(listLichTuVan), BorderLayout.SOUTH);

        
        danhSachLichTuVan = new ArrayList<>();
        danhSachLichTuVan.add(new LichTuVan(nextId++, LocalDate.now(), "8:00 - 9:00", "Phòng 1", "BS Nguyễn Văn Bình", "Nguyễn Đình Tri"));
        danhSachLichTuVan.add(new LichTuVan(nextId++, LocalDate.now().plusDays(1), "9:00 - 10:00", "Phòng 2", "BS Trần Đình Tùng", "Trần Thị Bình"));

        loadLichTuVanList();

        
        btnThem.addActionListener(e -> themLichTuVan());
        btnCapNhat.addActionListener(e -> capNhatLichTuVan());
        btnXoa.addActionListener(e -> xoaLichTuVan());
        btnTimKiem.addActionListener(e -> timKiemLichTuVan());
    }

    private void loadLichTuVanList() {
        listModel.clear();
        for (LichTuVan lichTuVan : danhSachLichTuVan) {
            listModel.addElement(lichTuVan);
        }
    }

    private void themLichTuVan() {
        String thoiGian = txtThoiGian.getText();
        String phongTuVan = txtPhongTuVan.getText();
        String nguoiTuVan = txtNguoiTuVan.getText();
        String benhNhan = txtBenhNhan.getText();
        danhSachLichTuVan.add(new LichTuVan(nextId++, LocalDate.now(), thoiGian, phongTuVan, nguoiTuVan, benhNhan));
        loadLichTuVanList();
        clearFields();
    }

    private void capNhatLichTuVan() {
        int selectedIndex = listLichTuVan.getSelectedIndex();
        if (selectedIndex != -1) {
            LichTuVan selectedLichTuVan = listModel.getElementAt(selectedIndex);

            JTextField txtThoiGian = new JTextField(selectedLichTuVan.getThoiGian());
            JTextField txtPhongTuVan = new JTextField(selectedLichTuVan.getPhongTuVan());
            JTextField txtNguoiTuVan = new JTextField(selectedLichTuVan.getNguoiTuVan());
            JTextField txtBenhNhan = new JTextField(selectedLichTuVan.getBenhNhan());

            JPanel panel = new JPanel(new GridLayout(4, 2));
            panel.add(new JLabel("Thời Gian"));
            panel.add(txtThoiGian);
            panel.add(new JLabel("Phòng Tư Vấn"));
            panel.add(txtPhongTuVan);
            panel.add(new JLabel("Người Tư Vấn"));
            panel.add(txtNguoiTuVan);
            panel.add(new JLabel("Bệnh Nhân"));
            panel.add(txtBenhNhan);

            int result = JOptionPane.showConfirmDialog(this, panel, "Cập Nhật Lịch Tư Vấn",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                selectedLichTuVan.setThoiGian(txtThoiGian.getText());
                selectedLichTuVan.setPhongTuVan(txtPhongTuVan.getText());
                selectedLichTuVan.setNguoiTuVan(txtNguoiTuVan.getText());
                selectedLichTuVan.setBenhNhan(txtBenhNhan.getText());
                listModel.set(selectedIndex, selectedLichTuVan);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một mục để cập nhật.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void xoaLichTuVan() {
        int selectedIndex = listLichTuVan.getSelectedIndex();
        if (selectedIndex != -1) {
            danhSachLichTuVan.remove(selectedIndex);
            loadLichTuVanList();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một mục để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void timKiemLichTuVan() {
        String keyword = JOptionPane.showInputDialog(this, "Nhập từ khóa tìm kiếm:", "Tìm Kiếm Lịch Tư Vấn", JOptionPane.QUESTION_MESSAGE);
        if (keyword != null && !keyword.trim().isEmpty()) {
            ArrayList<LichTuVan> resultList = new ArrayList<>();
            for (LichTuVan lichTuVan : danhSachLichTuVan) {
                if (lichTuVan.getThoiGian().contains(keyword)
                        || lichTuVan.getPhongTuVan().contains(keyword)
                        || lichTuVan.getNguoiTuVan().contains(keyword)
                        || lichTuVan.getBenhNhan().contains(keyword)) {
                    resultList.add(lichTuVan);
                }
            }
            if (!resultList.isEmpty()) {
                listModel.clear();
                for (LichTuVan lichTuVan : resultList) {
                    listModel.addElement(lichTuVan);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả phù hợp.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearFields() {
        txtThoiGian.setText("");
        txtPhongTuVan.setText("");
        txtNguoiTuVan.setText("");
        txtBenhNhan.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuanLyLichTuVan().setVisible(true));
    }

    private class LichTuVan {
        private int id;
        private LocalDate ngayTuVan;
        private String thoiGian;
        private String phongTuVan;
        private String nguoiTuVan;
        private String benhNhan;

        public LichTuVan(int id, LocalDate ngayTuVan, String thoiGian, String phongTuVan, String nguoiTuVan, String benhNhan) {
            this.id = id;
            this.ngayTuVan = ngayTuVan;
            this.thoiGian = thoiGian;
            this.phongTuVan = phongTuVan;
            this.nguoiTuVan = nguoiTuVan;
            this.benhNhan = benhNhan;
        }

        public int getId() {
            return id;
        }

        public LocalDate getNgayTuVan() {
            return ngayTuVan;
        }

        public String getThoiGian() {
            return thoiGian;
        }

        public String getPhongTuVan() {
            return phongTuVan;
        }

        public String getNguoiTuVan() {
            return nguoiTuVan;
        }

        public String getBenhNhan() {
            return benhNhan;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Ngày Tư Vấn: " + ngayTuVan + ", Thời Gian: " + thoiGian +
                    ", Phòng Tư Vấn: " + phongTuVan + ", Người Tư Vấn: " + nguoiTuVan +
                    ", Bệnh Nhân: " + benhNhan;
        }

        public void setThoiGian(String thoiGian) {
            this.thoiGian = thoiGian;
        }

        public void setPhongTuVan(String phongTuVan) {
            this.phongTuVan = phongTuVan;
        }

        public void setNguoiTuVan(String nguoiTuVan) {
            this.nguoiTuVan = nguoiTuVan;
        }

        public void setBenhNhan(String benhNhan) {
            this.benhNhan = benhNhan;
        }
    }
}
