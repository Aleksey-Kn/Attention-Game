import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    private final JLabel label;
    private int s, m;
    private boolean minus = false;
    private final MainPanel panel;

    MyTimerTask(JLabel importLabel, int t, MainPanel p){
        label = importLabel;
        m = t / 60;
        s = t % 60;
        panel = p;
    }

    @Override
    public void run() {
        if(panel.strColor != Color.BLUE) {
            panel.strColor = Color.BLUE;
            panel.repaint();
        }
        if(!minus) {
            if (s > 0) {
                s--;
            } else if (m > 0) {
                m--;
                s = 59;
            } else {
                minus = true;
                label.setForeground(Color.RED);
            }
        }
        else {
            if(s < 60){
                s++;
            }
            else{
                m++;
                s = 0;
            }
        }
        label.setText((minus? "-":"") + m + ":" + s);
    }
}
