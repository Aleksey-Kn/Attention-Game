import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Timer;

public class MainPanel extends JPanel {
    Color strColor = Color.BLUE;
    Color[] colorStorage = new Color[]{Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA};
    Color[] colors = new Color[6];
    LinkedList<Color> workingColors = new LinkedList<>();
    Main frame;
    JLabel time;
    int t;
    Timer timer = new Timer(true);
    boolean started = false;

    MainPanel(Main frame, int reit, int[] fine) {
        this.frame = frame;
        setBounds(0, 0, frame.getWidth(), frame.getHeight());
        setLayout(null);

        int stopper = (reit < 1000? 2: (reit > 1700? 0: 1));
        Random random = new Random();
        Set<Color> setColors = new HashSet<>(6 - stopper * 2);
        while (setColors.size() < 6 - stopper * 2){
            setColors.add(colorStorage[random.nextInt(6)]);
        }
        colorStorage = new Color[setColors.size()];
        setColors.toArray(colorStorage);
        t = stopper;
        for(Color color: setColors){
            colors[t++] = color;
        }
        for(int i = 0; i < reit / 25; i++){
            workingColors.add(colorStorage[random.nextInt(colorStorage.length)]);
        }

        t = (int)Math.ceil(workingColors.size() * (1000f / reit));
        time = new JLabel((t / 60) + ":" + (t % 60));
        time.setBounds(5, 5, 100, 20);
        add(time);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(started) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_1:
                            if (colors[0] != null) {
                                if (!colors[0].equals(workingColors.getFirst())) {
                                    fine[1] = (fine[1] == 0 ? 1 : fine[1] * 2);
                                    strColor = Color.red;
                                } else {
                                    strColor = Color.green;
                                }
                                workingColors.removeFirst();
                                repaint();
                            }
                            break;
                        case KeyEvent.VK_2:
                            if (colors[1] != null) {
                                if (!colors[1].equals(workingColors.getFirst())) {
                                    fine[1] = (fine[1] == 0 ? 1 : fine[1] * 2);
                                    strColor = Color.red;
                                } else {
                                    strColor = Color.green;
                                }
                                workingColors.removeFirst();
                                repaint();
                            }
                            break;
                        case KeyEvent.VK_3:
                            if (colors[2] != null) {
                                if (!colors[2].equals(workingColors.getFirst())) {
                                    fine[1] = (fine[1] == 0 ? 1 : fine[1] * 2);
                                    strColor = Color.red;
                                } else {
                                    strColor = Color.green;
                                }
                                workingColors.removeFirst();
                                repaint();
                            }
                            break;
                        case KeyEvent.VK_8:
                            if (colors[3] != null) {
                                if (!colors[3].equals(workingColors.getFirst())) {
                                    fine[1] = (fine[1] == 0 ? 1 : fine[1] * 2);
                                    strColor = Color.red;
                                } else {
                                    strColor = Color.green;
                                }
                                workingColors.removeFirst();
                                repaint();
                            }
                            break;
                        case KeyEvent.VK_9:
                            if (colors[4] != null) {
                                if (!colors[4].equals(workingColors.getFirst())) {
                                    fine[1] = (fine[1] == 0 ? 1 : fine[1] * 2);
                                    strColor = Color.red;
                                } else {
                                    strColor = Color.green;
                                }
                                workingColors.removeFirst();
                                repaint();
                            }
                            break;
                        case KeyEvent.VK_0:
                            if (colors[5] != null) {
                                if (!colors[5].equals(workingColors.getFirst())) {
                                    fine[1] = (fine[1] == 0 ? 1 : fine[1] * 2);
                                    strColor = Color.red;
                                } else {
                                    strColor = Color.green;
                                }
                                workingColors.removeFirst();
                                repaint();
                            }
                            break;
                    }
                    if (workingColors.isEmpty()) {
                        String[] s = time.getText().split(":");
                        fine[0] = Integer.parseInt(s[0]) * -60 + Integer.parseInt(s[1]) * (s[0].charAt(0) == '-' ? 1 : -1);
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
        MyTimerTask mtt = new MyTimerTask(time, t, strColor);
        timer.schedule(new PreviousTimerTask(timer, previousTimer, mtt, this), 1000, 1000);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int it = 0, w = frame.getWidth(), h = frame.getWidth();
        for(int i = 1; i <= 3; i++){
            g.drawString(Integer.toString(i), 100 + 80 * i, 600);
        }
        for(int i = 8; i < 11; i++){
            g.drawString(Integer.toString(i % 10), 350 + 80 * (i - 7), 600);
        }
        for(int i = 0; i < 6; i++){
            if(colors[i] != null){
                g.setColor(colors[i]);
                g.fillOval(100 + 80 * (i + 1) + (i > 2? 10: 0), 630, 15, 15);
            }
        }
        for(Color color: workingColors){
            if(it++ > 6){
                break;
            }
            g.setColor(color);
            g.fillRect(w / 2 - 25, h - 250 - it * 75, 50, 50);
        }
    }
}
