import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main extends JFrame {
    int reit;
    int[] fine = new int[2];
    MainPanel panel;
    private final File file = new File("reit.txt");
    private final Font font = new Font("Calibri", Font.PLAIN, 36);

    Main(){
        super("Attention game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 10, 800, 700);
        setLayout(null);
        setFocusable(true);

        try {
            reit = new Scanner(file).nextInt();
        }catch (FileNotFoundException | NoSuchElementException e){
            reit = 1000;
        }

        JLabel label = new JLabel("Ваш рейтинг: " + reit);
        label.setFont(font);
        label.setBounds(250, 150, 400, 300);
        add(label);
        JButton start = new JButton("Начать");
        start.setBounds(300, 450, 200, 150);
        start.addActionListener(l -> {
            getContentPane().removeAll();
            repaint();
            panel = new MainPanel(this, reit, fine);
            add(panel);
            panel.start();
        });
        add(start);

        setVisible(true);
    }

    void contin (){
        getContentPane().removeAll();
        repaint();
        int delta = -(fine[0] + fine[1]) / 2;
        JLabel label = new JLabel("Ваш рейтинг: ");
        label.setFont(font);
        label.setBounds(250, 0, 250, 300);
        add(label);
        JLabel reiting = new JLabel((reit + delta) + "(" + delta + ")");
        reiting.setFont(font);
        reiting.setBounds(500, 0, 250, 300);
        if(delta >= 0){
            reiting.setForeground(Color.green);
        }
        else{
            reiting.setForeground(Color.red);
        }
        add(reiting);
        JLabel time = new JLabel((fine[0] < 0? "Бонус за время: ": "Штраф за время: ") + Math.abs(fine[0]));
        time.setBounds(100, 100, 700, 300);
        time.setFont(font);
        add(time);
        JLabel shtraf = new JLabel("Штраф за неправильные ответы: " + fine[1]);
        shtraf.setBounds(100, 200, 7000, 300);
        shtraf.setFont(font);
        add(shtraf);
        reit += delta;
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(reit);
            writer.close();
        }
        catch (FileNotFoundException e){}
        JButton exit = new JButton("Выход");
        exit.addActionListener(l -> System.exit(0));
        exit.setBounds(500, 550, 200, 100);
        add(exit);
        JButton start = new JButton("Заново");
        start.setBounds(100, 550, 200, 100);
        start.addActionListener(l -> {
            getContentPane().removeAll();
            repaint();
            panel.updateWorkingList(reit, fine);
            add(panel);
            panel.start();
        });
        add(start);
    }

    public static void main(String[] args) {
        new Main();
    }
}
