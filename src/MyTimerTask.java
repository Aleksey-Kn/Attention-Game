import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    JLabel label;
    int s, m;
    boolean minus = false;
    Color strColor;

    MyTimerTask(JLabel importLabel, int t, Color color){
        label = importLabel;
        m = t / 60;
        s = t % 60;
        strColor = color;
    }

    @Override
    public void run() {
        strColor = Color.BLUE;
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
