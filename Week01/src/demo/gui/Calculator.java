package demo.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class Calculator extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton btnGiai, btnDelete, btnExit, btnBlue, btnRed, btnYellow;
    private JRadioButton radCong, radTru, radNhan, radChia;
    private JTextField input1, input2, kq;
    private JLabel ltlTitle;

    
    public Calculator() {
        setTitle("Máy Tính");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());

        // north panel
        JPanel pnNorth = new JPanel();
        pnNorth.add(Box.createRigidArea(new Dimension(0, 50)));
        ltlTitle = new JLabel("Cộng Trừ Nhân Chia");
        ltlTitle.setFont(new Font("Arial", Font.BOLD, 16));
        ltlTitle.setForeground(Color.BLUE);
        pnNorth.add(ltlTitle);
        pnMain.add(pnNorth, BorderLayout.NORTH);

        // west panel
        JPanel pnWest = new JPanel();
        pnWest.setLayout(new GridLayout(7, 1, 0, 10));
        pnWest.setBackground(Color.decode("#C0C0C0"));
        pnWest.setPreferredSize(new Dimension(120, 0));
        pnMain.add(pnWest, BorderLayout.WEST);
        // border
        Border lineBorder = BorderFactory.createLineBorder(Color.decode("#00ffff"), 2);
        Border borderPnEast = BorderFactory.createTitledBorder(lineBorder, "Chọn tác vụ");
        pnWest.setBorder(borderPnEast);

        // btn
        btnGiai = new JButton("Giải");
        btnGiai.setMnemonic(KeyEvent.VK_G);

        btnDelete = new JButton("Xoá");
        btnDelete.setMnemonic(KeyEvent.VK_D);

        btnExit = new JButton("Thoát");
        btnExit.setMnemonic(KeyEvent.VK_E);

        pnWest.add(btnGiai);
        pnWest.add(btnDelete);
        pnWest.add(btnExit);

        // center panel
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
        pnMain.add(pnCenter, BorderLayout.CENTER);
        // border
        Border borderPnCenter = BorderFactory.createTitledBorder(lineBorder, "Tính Toán");
        pnCenter.setBorder(borderPnCenter);

        JLabel text1 = new JLabel("Nhập a: ");
        JLabel text2 = new JLabel("Nhập b: ");
        JLabel text3 = new JLabel("Kết quả: ");

        input1 = new JTextField(32);

        input2 = new JTextField(32);

        kq = new JTextField(32);
        // disable text field
        kq.setEditable(false);

        // Dòng 1
        JPanel pnRow1 = new JPanel();
        pnRow1.add(Box.createRigidArea(new Dimension(480, 10)));
        pnRow1.add(text1);
        pnRow1.add(input1);

        // Dòng 2
        JPanel pnRow2 = new JPanel();
        pnRow2.add(Box.createRigidArea(new Dimension(480, 10)));
        pnRow2.add(text2);
        pnRow2.add(input2);

        // Dòng 3
        JPanel pnRow3 = new JPanel();
        pnRow3.setLayout(new BoxLayout(pnRow3, BoxLayout.X_AXIS));
        pnRow3.add(Box.createHorizontalStrut(60));
        Border borderPnRow3 = BorderFactory.createTitledBorder(lineBorder, "Phép Toán");

        // panel con để tạo khoảng cách đẩy phần radio vào
        JPanel pnChild = new JPanel();
        pnChild.setLayout(new BoxLayout(pnChild, BoxLayout.Y_AXIS));
        pnChild.setBorder(borderPnRow3);

        pnRow3.add(pnChild);

        // Tạo radio
        radCong = new JRadioButton("Cộng", true);
        radTru = new JRadioButton("Trừ");
        radNhan = new JRadioButton("Nhân");
        radChia = new JRadioButton("Chia");
        // thêm radio vào nhóm để tránh bị chọn nhiều cái 1 lúc
        ButtonGroup btnGr = new ButtonGroup();
        btnGr.add(radCong);
        btnGr.add(radTru);
        btnGr.add(radNhan);
        btnGr.add(radChia);

        // dòng chứa 2 radio cộng, trừ
        JPanel pnChild1 = new JPanel();
        pnChild1.setLayout(new BoxLayout(pnChild1, BoxLayout.X_AXIS));
        pnChild1.add(radCong);
        pnChild1.add(Box.createHorizontalStrut(88));
        pnChild1.add(radTru);
        pnChild1.add(Box.createHorizontalStrut(88));

        // dòng chứa 2 radio nhân, chia
        JPanel pnChild2 = new JPanel();
        pnChild2.setLayout(new BoxLayout(pnChild2, BoxLayout.X_AXIS));
        pnChild2.add(radNhan);
        pnChild2.add(Box.createHorizontalStrut(88));
        pnChild2.add(radChia);
        pnChild2.add(Box.createHorizontalStrut(88));

        pnChild.add(pnChild1);
        pnChild.add(pnChild2);

        // Dòng 4
        JPanel pnRow4 = new JPanel();
        pnRow4.add(Box.createRigidArea(new Dimension(480, 10)));
        pnRow4.add(text3);
        pnRow4.add(kq);

        pnCenter.add(pnRow1);
        pnCenter.add(pnRow2);
        pnCenter.add(pnRow3);
        pnCenter.add(pnRow4);

        // South panel
        JPanel pnSouth = new JPanel();
        pnSouth.setBackground(Color.decode("#ffafaf"));
        pnSouth.setPreferredSize(new Dimension(600, 70));
        pnMain.add(pnSouth, BorderLayout.SOUTH);
        // btn
        btnBlue = new JButton("");
        btnBlue.setBackground(Color.decode("#3f48cc"));
        btnBlue.add(Box.createRigidArea(new Dimension(5, 25)));

        btnRed = new JButton("");
        btnRed.setBackground(Color.RED);
        btnRed.add(Box.createRigidArea(new Dimension(5, 25)));

        btnYellow = new JButton("");
        btnYellow.setBackground(Color.YELLOW);
        btnYellow.add(Box.createRigidArea(new Dimension(5, 25)));

        pnSouth.add(btnBlue);
        pnSouth.add(Box.createRigidArea(new Dimension(10, 0)));
        pnSouth.add(btnRed);
        pnSouth.add(Box.createRigidArea(new Dimension(10, 0)));
        pnSouth.add(btnYellow);

        // Thêm vào giao diện
        this.add(pnMain);

        // lắng nghe sự kiện
        btnExit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnGiai.addActionListener(this);
        btnRed.addActionListener(this);
        btnBlue.addActionListener(this);
        btnYellow.addActionListener(this);
    }

    public static void main(String[] args) {
        new Calculator().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        double a = 0, b = 0, result = 0;
        if (obj.equals(btnExit))
            System.exit(0);
        else if (obj.equals(btnBlue))
            ltlTitle.setForeground(Color.BLUE);
        else if (obj.equals(btnRed))
            ltlTitle.setForeground(Color.RED);
        else if (obj.equals(btnYellow))
            ltlTitle.setForeground(Color.YELLOW);
        else if (obj.equals(btnDelete)) {
            input1.setText("");
            input2.setText("");
            kq.setText("");
            radCong.setSelected(true);
            input1.requestFocus();
        } else if (obj.equals(btnGiai)) {
            // Kiểm tra đầu vào có rỗng
            if (!isNumber(input1))
                focusTextField(input1);
            else if (!isNumber(input2))
                focusTextField(input2);
            else {
                a = Double.parseDouble(input1.getText());
                b = Double.parseDouble(input2.getText());
                if (radCong.isSelected())
                    result = a + b;
                else if (radTru.isSelected())
                    result = a - b;
                else if (radNhan.isSelected())
                    result = a * b;
                else if (radChia.isSelected()) {
                    if (b != 0)
                        result = a / b;
                    else
                        focusTextField(input2);
                }
                kq.setText(String.valueOf(result));
            }
        }
    }

    public void focusTextField(JTextField text) {
        JOptionPane.showMessageDialog(this, "Lỗi nhập liệu " + text.getText());
        text.selectAll();
        text.requestFocus();
        return;
    }

    public boolean isNumber(JTextField text) {
        return text.getText().matches("^-?\\d*\\.?\\d+$");
    }
}