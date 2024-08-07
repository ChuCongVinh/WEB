package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class QuanLyBenhNhan extends JFrame {
    class BenhNhan {
        private String maBenhNhan;
        private String tenBenhNhan;
        private Calendar ngaySinh;

        public BenhNhan(String maBenhNhan, String tenBenhNhan, Calendar ngaySinh) {
            this.maBenhNhan = maBenhNhan;
            this.tenBenhNhan = tenBenhNhan;
            this.ngaySinh = ngaySinh;
        }

        public String getMaBenhNhan() {
            return maBenhNhan;
        }

        public String getTenBenhNhan() {
            return tenBenhNhan;
        }

        public void setTenBenhNhan(String tenBenhNhan) {
            this.tenBenhNhan = tenBenhNhan;
        }

        public Calendar getNgaySinh() {
            return ngaySinh;
        }

        public void setNgaySinh(Calendar ngaySinh) {
            this.ngaySinh = ngaySinh;
        }

        @Override
        public String toString() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return "Mã BN: " + maBenhNhan + ", Tên: " + tenBenhNhan + ", Ngày sinh: " + sdf.format(ngaySinh.getTime());
        }
    }

    class LichKham {
        private BenhNhan benhNhan;
        private Calendar thoiGianBatDau;
        private Calendar thoiGianKetThuc;
        private String phongTuVan;
        private String nguoiTuVan;

        public LichKham(BenhNhan benhNhan, Calendar thoiGianBatDau, Calendar thoiGianKetThuc, String phongTuVan, String nguoiTuVan) {
            this.benhNhan = benhNhan;
            this.thoiGianBatDau = thoiGianBatDau;
            this.thoiGianKetThuc = thoiGianKetThuc;
            this.phongTuVan = phongTuVan;
            this.nguoiTuVan = nguoiTuVan;
        }

        public BenhNhan getBenhNhan() {
            return benhNhan;
        }

        public Calendar getThoiGianBatDau() {
            return thoiGianBatDau;
        }

        public Calendar getThoiGianKetThuc() {
            return thoiGianKetThuc;
        }

        public String getPhongTuVan() {
            return phongTuVan;
        }

        public String getNguoiTuVan() {
            return nguoiTuVan;
        }

        @Override
        public String toString() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return "BN: " + benhNhan.getTenBenhNhan() + ", Từ: " + sdf.format(thoiGianBatDau.getTime()) + " Đến: " + sdf.format(thoiGianKetThuc.getTime())
                    + ", Phòng: " + phongTuVan + ", Người tư vấn: " + nguoiTuVan;
        }
    }

    private JTextField txtMaBenhNhan, txtTenBenhNhan, txtNgaySinh, txtThoiGianBatDau, txtThoiGianKetThuc, txtTimKiem;
    private JButton btnThem, btnCapNhat, btnDatLich, btnTimKiem;
    private JComboBox<String> cboPhongTuVan, cboNguoiTuVan;
    private JList<BenhNhan> listBenhNhan;
    private JList<LichKham> listLichKham;
    private DefaultListModel<BenhNhan> benhNhanModel;
    private DefaultListModel<LichKham> lichKhamModel;
    private ArrayList<BenhNhan> danhSachBenhNhan;
    private ArrayList<LichKham> danhSachLichKham;

    public QuanLyBenhNhan() {
        danhSachBenhNhan = new ArrayList<>();
        danhSachLichKham = new ArrayList<>();

        setTitle("Quản Lý Bệnh Nhân");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(8, 2));
        txtMaBenhNhan = new JTextField();
        txtTenBenhNhan = new JTextField();
        txtNgaySinh = new JTextField();
        txtThoiGianBatDau = new JTextField();
        txtThoiGianKetThuc = new JTextField();
        txtTimKiem = new JTextField();
        cboPhongTuVan = new JComboBox<>(new String[]{"Tư Vấn Tâm Lý", "Tư Vấn Sức Khỏe", "Tư Vấn Dinh Dưỡng"});
        cboNguoiTuVan = new JComboBox<>(new String[]{"Tiến sĩ Lê Văn Lợi", "Tiến Sĩ Đinh Hoàng Việt", "Thạc sĩ Nguyễn Thị Quyên"});

        panelForm.add(new JLabel("Mã Bệnh Nhân:"));
        panelForm.add(txtMaBenhNhan);
        panelForm.add(new JLabel("Tên Bệnh Nhân:"));
        panelForm.add(txtTenBenhNhan);
        panelForm.add(new JLabel("Ngày Sinh (ngày/tháng/năm):"));
        panelForm.add(txtNgaySinh);
        panelForm.add(new JLabel("Thời Gian Bắt Đầu (ngày/tháng/năm giờ:phút):"));
        panelForm.add(txtThoiGianBatDau);
        panelForm.add(new JLabel("Thời Gian Kết Thúc (ngày/tháng/năm giờ:phút):"));
        panelForm.add(txtThoiGianKetThuc);
        panelForm.add(new JLabel("Phòng Tư Vấn:"));
        panelForm.add(cboPhongTuVan);
        panelForm.add(new JLabel("Người Tư Vấn:"));
        panelForm.add(cboNguoiTuVan);
        panelForm.add(new JLabel("Tìm kiếm bệnh nhân:"));
        panelForm.add(txtTimKiem);

        add(panelForm, BorderLayout.NORTH);

        JPanel panelButtons = new JPanel(new GridLayout(1, 4));
        btnThem = new JButton("Thêm");
        btnCapNhat = new JButton("Cập Nhật");
        btnDatLich = new JButton("Đặt Lịch");
        btnTimKiem = new JButton("Tìm kiếm");

        panelButtons.add(btnThem);
        panelButtons.add(btnCapNhat);
        panelButtons.add(btnDatLich);
        panelButtons.add(btnTimKiem);

        add(panelButtons, BorderLayout.CENTER);

        benhNhanModel = new DefaultListModel<>();
        listBenhNhan = new JList<>(benhNhanModel);
        lichKhamModel = new DefaultListModel<>();
        listLichKham = new JList<>(lichKhamModel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(listBenhNhan), new JScrollPane(listLichKham));
        add(splitPane, BorderLayout.SOUTH);

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themBenhNhan();
            }
        });

        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capNhatBenhNhan();
            }
        });

        btnDatLich.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                datLichKham();
            }
        });

        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiemBenhNhan();
            }
        });

        listBenhNhan.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && listBenhNhan.getSelectedIndex() != -1) {
                BenhNhan selectedBenhNhan = listBenhNhan.getSelectedValue();
                if (selectedBenhNhan != null) {
                    txtMaBenhNhan.setText(selectedBenhNhan.getMaBenhNhan());
                    txtTenBenhNhan.setText(selectedBenhNhan.getTenBenhNhan());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    txtNgaySinh.setText(sdf.format(selectedBenhNhan.getNgaySinh().getTime()));
                }
            }
        });
    }

    private void themBenhNhan() {
        try {
            String maBenhNhan = txtMaBenhNhan.getText().trim();
            String tenBenhNhan = txtTenBenhNhan.getText().trim();
            String ngaySinhStr = txtNgaySinh.getText().trim();

            // Debug thông tin nhập liệu
            System.out.println("Mã BN: " + maBenhNhan);
            System.out.println("Tên BN: " + tenBenhNhan);
            System.out.println("Ngày sinh: " + ngaySinhStr);

            // Kiểm tra nhập liệu trống
            if (maBenhNhan.isEmpty() || tenBenhNhan.isEmpty() || ngaySinhStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar ngaySinh = Calendar.getInstance();
            ngaySinh.setTime(sdf.parse(ngaySinhStr));

            // Debug ngày sinh
            System.out.println("Ngày sinh sau khi parse: " + sdf.format(ngaySinh.getTime()));

            BenhNhan existingBenhNhan = null;
            for (BenhNhan bn : danhSachBenhNhan) {
                if (bn.getMaBenhNhan().equals(maBenhNhan)) {
                    existingBenhNhan = bn;
                    break;
                }
            }

            if (existingBenhNhan == null) {
                BenhNhan benhNhan = new BenhNhan(maBenhNhan, tenBenhNhan, ngaySinh);
                danhSachBenhNhan.add(benhNhan);
                benhNhanModel.addElement(benhNhan);
                JOptionPane.showMessageDialog(this, "Thêm bệnh nhân thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Bệnh nhân đã tồn tại!");
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ!");
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi!");
            ex.printStackTrace();
        }
    }

    private void capNhatBenhNhan() {
        try {
            String maBenhNhan = txtMaBenhNhan.getText().trim();
            String tenBenhNhan = txtTenBenhNhan.getText().trim();
            String ngaySinhStr = txtNgaySinh.getText().trim();

            if (maBenhNhan.isEmpty() || tenBenhNhan.isEmpty() || ngaySinhStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar ngaySinh = Calendar.getInstance();
            ngaySinh.setTime(sdf.parse(ngaySinhStr));

            BenhNhan existingBenhNhan = null;
            for (BenhNhan bn : danhSachBenhNhan) {
                if (bn.getMaBenhNhan().equals(maBenhNhan)) {
                    existingBenhNhan = bn;
                    break;
                }
            }

            if (existingBenhNhan != null) {
                existingBenhNhan.setTenBenhNhan(tenBenhNhan);
                existingBenhNhan.setNgaySinh(ngaySinh);
                listBenhNhan.repaint();
                JOptionPane.showMessageDialog(this, "Cập nhật bệnh nhân thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Bệnh nhân không tồn tại!");
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi!");
            ex.printStackTrace();
        }
    }

    private void datLichKham() {
        try {
            BenhNhan benhNhan = listBenhNhan.getSelectedValue();
            if (benhNhan == null) {
                JOptionPane.showMessageDialog(this, "Chọn bệnh nhân trước khi đặt lịch!");
                return;
            }

            String thoiGianBatDauStr = txtThoiGianBatDau.getText().trim();
            String thoiGianKetThucStr = txtThoiGianKetThuc.getText().trim();
            String phongTuVan = (String) cboPhongTuVan.getSelectedItem();
            String nguoiTuVan = (String) cboNguoiTuVan.getSelectedItem();

            if (thoiGianBatDauStr.isEmpty() || thoiGianKetThucStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập thời gian bắt đầu và kết thúc!");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Calendar thoiGianBatDau = Calendar.getInstance();
            Calendar thoiGianKetThuc = Calendar.getInstance();
            thoiGianBatDau.setTime(sdf.parse(thoiGianBatDauStr));
            thoiGianKetThuc.setTime(sdf.parse(thoiGianKetThucStr));

            for (LichKham lk : danhSachLichKham) {
                if (lk.getPhongTuVan().equals(phongTuVan) && lk.getNguoiTuVan().equals(nguoiTuVan) &&
                        thoiGianBatDau.before(lk.getThoiGianKetThuc()) && thoiGianKetThuc.after(lk.getThoiGianBatDau())) {
                    JOptionPane.showMessageDialog(this, "Phòng và người tư vấn đã được đặt trong khoảng thời gian này!");
                    return;
                }
            }

            LichKham lichKham = new LichKham(benhNhan, thoiGianBatDau, thoiGianKetThuc, phongTuVan, nguoiTuVan);
            danhSachLichKham.add(lichKham);
            lichKhamModel.addElement(lichKham);
            JOptionPane.showMessageDialog(this, "Đặt lịch khám thành công!");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Thời gian không hợp lệ!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi!");
            ex.printStackTrace();
        }
    }

    private void timKiemBenhNhan() {
        String tenBenhNhan = txtTimKiem.getText().trim().toLowerCase();
        BenhNhan foundBenhNhan = null;
        for (BenhNhan bn : danhSachBenhNhan) {
            if (bn.getTenBenhNhan().toLowerCase().contains(tenBenhNhan)) {
                foundBenhNhan = bn;
                break;
            }
        }

        if (foundBenhNhan != null) {
            listBenhNhan.setSelectedValue(foundBenhNhan, true);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy bệnh nhân!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuanLyBenhNhan().setVisible(true);
            }
        });
    }
}
