import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class PreviousTimerTask extends TimerTask {
    int now = 2;
    Timer timer;
    JLabel label;
    TimerTask timerTask;
    MainPanel panel;

    PreviousTimerTask(Timer t, JLabel l, TimerTask mtt, MainPanel panel){
        timer = t;
        label = l;
        timerTask = mtt;
        this.panel = panel;
    }

    @Override
    public void run() {
        if(now > 0){
            label.setText(Integer.toString(now--));
        }
        else{
            timer.cancel();
            panel.remove(label);
            timer = new Timer(true);
            panel.started = true;
            panel.repaint();
            timer.schedule(timerTask , 1000, 1000);
        }
    }
}
