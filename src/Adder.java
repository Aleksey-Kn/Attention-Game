import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class Adder extends Thread {
    private double time = 1250;
    private final MyLabel counter;
    private final LinkedBlockingDeque<Color> list;
    private final MainPanel mainPanel;
    private final Color[] colorStorage = new Color[]{Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA};

    Adder(MyLabel count, LinkedBlockingDeque<Color> list, MainPanel mainPanel) {
        counter = count;
        this.list = list;
        this.mainPanel = mainPanel;
        setDaemon(true);
    }

    @Override
    public void run() {
        Random random = new Random();
        while (list.size() < 50) {
            if (isInterrupted()) {
                return;
            }
            list.addLast(colorStorage[random.nextInt(colorStorage.length)]);
            counter.setCount(list.size());
            if (list.size() > 7) {
                try {
                    sleep((int) time);
                } catch (InterruptedException e) {
                    return;
                }
            } else {
                synchronized (mainPanel) {
                    mainPanel.repaint();
                }
            }
            if (time > 450) {
                time = (time > 1000 ? 0.99 * time : time - 3);
            }
        }
        mainPanel.exit("Слишком медленно");
    }
}
