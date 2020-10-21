import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main extends JFrame {
    private int reit;
    private MainPanel panel;
    private final File file = new File("reit.txt");
    private final Font font = new Font("Calibri", Font.PLAIN, 36);

    Main(){
        super("Attention game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 5, 800, 720);
        setLayout(null);
        setFocusable(true);

        try {
            reit = new Scanner(file).nextInt();
        }catch (FileNotFoundException | NoSuchElementException e){
            reit = 0;
        }

        JLabel label = new JLabel("Ваш рекорд: " + reit);
        label.setFont(font);
        label.setBounds(250, 150, 400, 300);
        add(label);
        JButton start = new JButton("Начать");
        start.setBounds(300, 450, 200, 150);
        start.addActionListener(l -> {
            getContentPane().removeAll();
            repaint();
            panel = new MainPanel(this);
            add(panel);
            panel.start();
        });
        add(start);

        setVisible(true);
    }

    void contin (){
        getContentPane().removeAll();
        repaint();
        JLabel label = new JLabel("Ваш счёт: ");
        label.setFont(font);
        label.setBounds(20, 0, 250, 100);
        add(label);
        JLabel cause = new JLabel("Причина поражения: " + panel.getCause());
        cause.setFont(font);
        cause.setBounds(20, 100, 700, 100);
        add(cause);
        JLabel reiting = new JLabel(String.valueOf(panel.getScore()));
        reiting.setFont(font);
        reiting.setBounds(400, 0, 250, 100);
        add(reiting);
        reit = Math.max(reit, panel.getScore());
        JLabel shtraf = new JLabel("Рекорд: " + reit);
        shtraf.setBounds(20, 200, 700, 100);
        shtraf.setFont(font);
        add(shtraf);
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(reit);
            writer.close();
        }
        catch (FileNotFoundException ignored){}
        JButton exit = new JButton("Выход");
        exit.addActionListener(l -> System.exit(0));
        exit.setBounds(500, 550, 200, 100);
        add(exit);
        JButton start = new JButton("Заново");
        start.setBounds(100, 550, 200, 100);
        start.addActionListener(l -> {
            getContentPane().removeAll();
            panel.updateWorkingList();
            add(panel);
            getContentPane().repaint();
            panel.start();
        });
        add(start);
        repaint();
    }

    public static void main(String[] args) {
        new Main();
    }
}
