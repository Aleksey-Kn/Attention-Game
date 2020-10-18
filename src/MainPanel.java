import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Timer;

public class MainPanel extends JPanel {
    Color strColor = Color.BLUE;
    Color[] colorStorage = new Color[]{Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA};
    LinkedList<Color> workingColors = new LinkedList<>();
    Main frame;
    JLabel time;
    int t;
    final private int h;
    final private int w1;
    private final int[] leftStrX;
    private final int[] StrY;
    private final int[] rightStrX;
    Timer timer;
    boolean started = false;

    MainPanel(Main frame, int reit, int[] fine) {
        this.frame = frame;
        int w = frame.getWidth();
        h = frame.getWidth();
        w1 = w / 2;
        StrY = new int[]{h - 300, h - 280, h - 290, h - 290, h - 310, h - 310, h - 320};
        rightStrX = new int[]{w1 + 80, w1 + 110, w1 + 110, w1 + 170, w1 + 170, w1 + 110, w1 + 110};
        leftStrX = new int[]{w1 - 80, w1 - 110, w1 - 110, w1 - 170, w1 - 170, w1 - 110, w1 - 110};
        setBounds(0, 0, frame.getWidth(), frame.getHeight());
        setLayout(null);

        updateWorkingList(reit, fine);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (started) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_1:
                            if (!colorStorage[0].equals(workingColors.getFirst())) {
                                fine[1] = (fine[1] == 0 ? 1 : fine[1] * 2);
                                strColor = Color.red;
                            } else {
                                strColor = Color.green;
                            }
                            workingColors.removeFirst();
                            repaint();
                            break;
                        case KeyEvent.VK_2:
                            if (!colorStorage[1].equals(workingColors.getFirst())) {
                                fine[1] = (fine[1] == 0 ? 1 : fine[1] * 2);
                                strColor = Color.red;
                            } else {
                                strColor = Color.green;
                            }
                            workingColors.removeFirst();
                            repaint();
                            break;
                        case KeyEvent.VK_3:
                            if (!colorStorage[2].equals(workingColors.getFirst())) {
                                fine[1] = (fine[1] == 0 ? 1 : fine[1] * 2);
                                strColor = Color.red;
                            } else {
                                strColor = Color.green;
                            }
                            workingColors.removeFirst();
                            repaint();
                            break;
                        case KeyEvent.VK_8:
                            if (!colorStorage[3].equals(workingColors.getFirst())) {
                                fine[1] = (fine[1] == 0 ? 1 : fine[1] * 2);
                                strColor = Color.red;
                            } else {
                                strColor = Color.green;
                            }
                            workingColors.removeFirst();
                            repaint();
                            break;
                        case KeyEvent.VK_9:
                            if (!colorStorage[4].equals(workingColors.getFirst())) {
                                fine[1] = (fine[1] == 0 ? 1 : fine[1] * 2);
                                strColor = Color.red;
                            } else {
                                strColor = Color.green;
                            }
                            workingColors.removeFirst();
                            repaint();
                            break;
                        case KeyEvent.VK_0:
                            if (!colorStorage[5].equals(workingColors.getFirst())) {
                                fine[1] = (fine[1] == 0 ? 1 : fine[1] * 2);
                                strColor = Color.red;
                            } else {
                                strColor = Color.green;
                            }
                            workingColors.removeFirst();
                            repaint();
                            break;
                    }
                    if (workingColors.isEmpty()) {
                        started = false;
                        String[] s = time.getText().split(":");
                        fine[0] = Integer.parseInt(s[0]) * -60 + Integer.parseInt(s[1]) * (s[0].charAt(0) == '-' ? 1 : -1);
                        fine[0] = (int) (Math.signum(fine[0]) * Math.pow(Math.abs(fine[0]), 1.5));
                        timer.cancel();
                        remove(time);
                        frame.contin();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        setVisible(true);
    }

    void start() {
        repaint();
        JLabel previousTimer = new JLabel("3");
        previousTimer.setBounds(300, 0, 200, 400);
        previousTimer.setFont(new Font("Arial", Font.BOLD, 300));
        add(previousTimer);
        timer.schedule(new PreviousTimerTask(timer, previousTimer, this), 1000, 1000);
    }

    void updateWorkingList(int reit, int[] fine) {
        timer = new Timer();
        Arrays.fill(fine, 0); // счёт и всё, что с ним связано
        Random random = new Random();
        for (int i = 0; i < reit / 25; i++) {
            workingColors.add(colorStorage[random.nextInt(colorStorage.length)]);
        }

        t = (int) Math.ceil(workingColors.size() * (1000f / reit));
        time = new JLabel((t / 60) + ":" + (t % 60));
        time.setBounds(5, 5, 100, 20);
        add(time);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int it = 0;
        for (int i = 1; i <= 3; i++) {
            g.drawString(Integer.toString(i), 100 + 80 * i, 600);
        }
        for (int i = 8; i < 11; i++) {
            g.drawString(Integer.toString(i % 10), 350 + 80 * (i - 7), 600);
        }
        for (int i = 0; i < 6; i++) {
            g.setColor(colorStorage[i]);
            g.fillOval(100 + 80 * (i + 1) + (i > 2 ? 10 : 0), 630, 15, 15);
        }
        for (Color color : workingColors) {
            if (it++ > 6) {
                break;
            }
            g.setColor(color);
            g.fillRect(w1 - 25, h - 250 - it * 75, 50, 50);
        }
        g.setColor(strColor);
        g.fillPolygon(rightStrX, StrY, rightStrX.length);
        g.fillPolygon(leftStrX, StrY, leftStrX.length);
    }
}
