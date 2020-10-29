import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingDeque;

public class MainPanel extends JPanel {
    private Color strColor = Color.BLUE;
    private final Color[] colorStorage = new Color[]{Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA};
    private final LinkedBlockingDeque<Color> workingColors = new LinkedBlockingDeque<>();
    private final Main frame;
    final MyLabel time;
    private int score;
    final private int h;
    final private int w1;
    private final int[] leftStrX;
    private final int[] StrY;
    private final int[] rightStrX;
    boolean started = false;
    private String cause;
    private Thread adder;
    private int lives;
    private final JLabel labelScore = new JLabel();

    MainPanel(Main frame) {
        this.frame = frame;
        int w = frame.getWidth();
        h = frame.getWidth();
        w1 = w / 2;
        StrY = new int[]{h - 300, h - 280, h - 290, h - 290, h - 310, h - 310, h - 320};
        rightStrX = new int[]{w1 + 80, w1 + 110, w1 + 110, w1 + 170, w1 + 170, w1 + 110, w1 + 110};
        leftStrX = new int[]{w1 - 80, w1 - 110, w1 - 110, w1 - 170, w1 - 170, w1 - 110, w1 - 110};
        setBounds(0, 0, frame.getWidth(), frame.getHeight());
        setLayout(null);

        labelScore.setBounds(5, 5, 80, 20);
        add(labelScore);
        time = new MyLabel(getWidth(), getHeight());
        add(time);
        updateWorkingList();

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (started && !workingColors.isEmpty()) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_1:
                            if (!colorStorage[0].equals(workingColors.getFirst())) {
                                strColor = Color.red;
                                if (--lives == 0) {
                                    adder.interrupt();
                                    exit("Неправильные ответы");
                                }
                            } else {
                                strColor = Color.green;
                                score += (workingColors.size() < 8 ? 3 : 1);
                            }
                            workingColors.removeFirst();
                            repaint();
                            break;
                        case KeyEvent.VK_2:
                            if (!colorStorage[1].equals(workingColors.getFirst())) {
                                strColor = Color.red;
                                if (--lives == 0) {
                                    adder.interrupt();
                                    exit("Неправильные ответы");
                                }
                            } else {
                                strColor = Color.green;
                                score += (workingColors.size() < 8 ? 3 : 1);
                            }
                            workingColors.removeFirst();
                            repaint();
                            break;
                        case KeyEvent.VK_3:
                            if (!colorStorage[2].equals(workingColors.getFirst())) {
                                strColor = Color.red;
                                if (--lives == 0) {
                                    adder.interrupt();
                                    exit("Неправильные ответы");
                                }
                            } else {
                                strColor = Color.green;
                                score += (workingColors.size() < 8 ? 3 : 1);
                            }
                            workingColors.removeFirst();
                            repaint();
                            break;
                        case KeyEvent.VK_8:
                            if (!colorStorage[3].equals(workingColors.getFirst())) {
                                strColor = Color.red;
                                if (--lives == 0) {
                                    adder.interrupt();
                                    exit("Неправильные ответы");
                                }
                            } else {
                                strColor = Color.green;
                                score += (workingColors.size() < 8 ? 3 : 1);
                            }
                            workingColors.removeFirst();
                            repaint();
                            break;
                        case KeyEvent.VK_9:
                            if (!colorStorage[4].equals(workingColors.getFirst())) {
                                strColor = Color.red;
                                if (--lives == 0) {
                                    adder.interrupt();
                                    exit("Неправильные ответы");
                                }
                            } else {
                                strColor = Color.green;
                                score += (workingColors.size() < 8 ? 3 : 1);
                            }
                            workingColors.removeFirst();
                            repaint();
                            break;
                        case KeyEvent.VK_0:
                            if (!colorStorage[5].equals(workingColors.getFirst())) {
                                strColor = Color.red;
                                if (--lives == 0) {
                                    adder.interrupt();
                                    exit("Неправильные ответы");
                                }
                            } else {
                                strColor = Color.green;
                                score += (workingColors.size() < 8 ? 3 : 1);
                            }
                            workingColors.removeFirst();
                            repaint();
                            break;
                    }
                    time.setCount(workingColors.size());
                    labelScore.setText("Score: " + getScore());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        setVisible(true);
    }

    void start() {
        Timer timer = new Timer();
        repaint();
        JLabel previousTimer = new JLabel("3");
        previousTimer.setBounds(300, 0, 200, 400);
        previousTimer.setFont(new Font("Arial", Font.BOLD, 300));
        add(previousTimer);
        timer.schedule(new PreviousTimerTask(timer, previousTimer, this), 1000, 1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                strColor = Color.BLUE;
            }
        }, 250, 250);
        adder = new Adder(time, workingColors, this);
        adder.start();
    }

    void updateWorkingList() {
        score = 0;
        lives = 3;
        Random random = new Random();
        workingColors.clear();
        for (int i = 0; i < 20; i++) {
            workingColors.add(colorStorage[random.nextInt(colorStorage.length)]);
        }
        time.setCount(20);
        labelScore.setText("Score: 0");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int it = 0;
        g.drawString("Lives:", getWidth() - 180, 40);
        g.setColor(Color.green);
        for (int i = 0; i < lives; i++) {
            g.fillOval(getWidth() - 55 - i * 30, 20, 25, 25);
        }
        g.setColor(Color.BLACK);
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

    public int getScore() {
        return (int) Math.pow((float) score / 10, 1.2);
    }

    public void exit(String c) {
        if (started) {
            started = false;
            cause = c;
            frame.contin();
        }
    }

    public String getCause() {
        return cause;
    }
}
