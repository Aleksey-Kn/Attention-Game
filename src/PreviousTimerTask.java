import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class PreviousTimerTask extends TimerTask {
    private int now = 2;
    private Timer timer;
    private JLabel label;
    private MainPanel panel;

    PreviousTimerTask(Timer t, JLabel l, MainPanel panel){
        timer = t;
        label = l;
        this.panel = panel;
    }

    @Override
    public void run() {
        if(now > 0){
            label.setText(Integer.toString(now--));
        }
        else{
            panel.remove(label);
            panel.started = true;
            panel.repaint();
            cancel();
        }
    }
}
