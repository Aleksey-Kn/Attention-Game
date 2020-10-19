import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class Adder extends Thread{
    private float time = 1500;
    private final JLabel counter;
    private final LinkedBlockingDeque<Color> list;
    private final MainPanel mainPanel;
    private final Color[] colorStorage = new Color[]{Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA};

    Adder(JLabel count, LinkedBlockingDeque<Color> list, MainPanel mainPanel){
        counter = count;
        this.list = list;
        this.mainPanel = mainPanel;
        setDaemon(true);
    }

    @Override
    public void run() {
        Random random = new Random();
        while (list.size() < 50){
            list.addLast(colorStorage[random.nextInt(colorStorage.length)]);
            counter.setText(String.valueOf(list.size()));
            synchronized (mainPanel){
                mainPanel.repaint();
            }
            if(list.size() > 6) {
                try {
                    sleep((long) time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(time > 250) {
                time *= 0.99;
            }
        }
        mainPanel.exit("Слишком медленно");
    }
}
