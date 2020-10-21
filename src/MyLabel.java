import javax.swing.*;
import java.awt.*;

public
class MyLabel extends JLabel {
    private int count;

    MyLabel(int weight, int height){
        setBounds(5, height - 65, weight - 10, 30);
    }

    public void setCount(int count) {
        this.count = count;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color((int)(count * 5.1), (int)(255 - (count * 5.1)), 0));
        g.fillRect(0, 0, getWidth() - (count * getWidth() / 50), 30);
    }
}