package to_tinh_crush;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class To_tinh_crush extends JFrame {

    private boolean heartbeatEffect = false; // Biến để xác định xem có bật hiệu ứng đập trái tim không
    private long startHeartbeatTime; // Thời gian bắt đầu hiệu ứng đập trái tim
    private final int HEART_SIZE = 50; // Kích thước trái tim
    private final int CRUSH_X = 200; // Tọa độ x của hình ảnh crush
    private final int CRUSH_Y = 100; // Tọa độ y của hình ảnh crush
    private int heartX; // Tọa độ x của trái tim màu đỏ
    private int heartY; // Tọa độ y của trái tim màu đỏ
    private Timer timer;

    public To_tinh_crush() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Tỏ tình hoành tráng");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));

        // Nền frame màu hồng
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.PINK);
        mainPanel.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Mài yêu ta không để tao còn đi yêu con khác =))");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon crushImage = new ImageIcon("crush_image.jpg"); // Thay đường dẫn bằng đường dẫn thật của ảnh crush
        JLabel crushImageLabel = new JLabel(crushImage);

        JButton sendButton = new JButton("yes i do");
        sendButton.setFont(new Font("Arial", Font.BOLD, 20));
        sendButton.setBackground(Color.RED);
        sendButton.setForeground(Color.WHITE);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiệu ứng đổi màu nền khi gửi tình cảm
                mainPanel.setBackground(Color.MAGENTA);

                // Hiệu ứng chuyển động cho chữ và hình ảnh
                messageLabel.setText("<html><font color='blue'>hâm dở khonnan miết quen</font></html>");
                crushImageLabel.setIcon(new ImageIcon("another_crush_image.jpg")); // Thay đổi ảnh nếu muốn

                // Hiệu ứng trái tim đập xung quanh hình ảnh crush
                startHeartbeatEffect();

                // Thông báo khi gửi tình cảm
                JOptionPane.showMessageDialog(To_tinh_crush.this,
                        "Chúc mừng! Bạn đã tỏ tìn thềnh công!",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        mainPanel.add(messageLabel, BorderLayout.NORTH);
        mainPanel.add(crushImageLabel, BorderLayout.CENTER);
        mainPanel.add(sendButton, BorderLayout.SOUTH);

        // Thêm listener để cập nhật vị trí trái tim màu đỏ khi frame thay đổi kích thước
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                heartX = getWidth() / 2 - HEART_SIZE / 2;
                heartY = getHeight() / 2 - HEART_SIZE / 2;
                repaint();
            }
        });

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void startHeartbeatEffect() {
    // Thiết lập biến để bật hiệu ứng đập trái tim
    heartbeatEffect = true;
    startHeartbeatTime = System.currentTimeMillis();

    // Thiết lập timer để di chuyển trái tim đập xung quanh hình ảnh crush
    timer = new Timer(50, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Di chuyển trái tim đến ngẫu nhiên xung quanh vị trí của crush
            int dx = (int) (Math.random() * 10) - 5;
            int dy = (int) (Math.random() * 10) - 5;
            heartX = CRUSH_X + dx;
            heartY = CRUSH_Y + dy;

            // Khi trái tim đập ra khỏi vị trí của crush, tạo trái tim mới ngay tại vị trí crush
            if (heartX < 100 || heartX > 300 || heartY < 50 || heartY > 150) {
                heartX = CRUSH_X;
                heartY = CRUSH_Y;
            }

            // Vẽ lại frame để cập nhật vị trí của trái tim
            repaint();
        }
    });

    // Bắt đầu timer
    timer.start();
}


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Vẽ trái tim nếu hiệu ứng đập đang được bật
        if (heartbeatEffect) {
            g.setColor(Color.RED);
            g.fillOval(heartX, heartY, HEART_SIZE, HEART_SIZE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            To_tinh_crush loveConfession = new To_tinh_crush();
            loveConfession.setVisible(true);
        });
    }
}
