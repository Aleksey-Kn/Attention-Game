import javax.swing.*;
import java.awt.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends JFrame {
    Main(){
        super("Attention game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 10, 800, 700);
        setLayout(null);

        int reit;
        try {
            reit = new Scanner(new File("reit.txt")).nextInt();
        }catch (FileNotFoundException e){
            reit = 1000;
        }

        JLabel label = new JLabel("Ваш рейтинг: " + reit);
        label.setFont(new Font("Calibri", Font.PLAIN, 36));
        label.setBounds(200, 100, 400, 300);
        add(label);

    }

    public static void main(String[] args) {
        new Main();
    }
}
