import javax.swing.*;
import java.awt.*;

class Calculator extends JFrame {
    int buttonX = 0;
    int buttonY = 80;

    public Calculator() {
        this.setTitle("Basic Calulator");
        this.setSize(400, 600);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(120, 128, 122));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        
        String[] symbols = {"C", "()", "%", "÷", "7", "8", "9", "x", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
        JButton[] button = new JButton[20];
        for (int i = 0; i < 20; i++) {
            button[i] = new JButton(symbols[i]);
            button[i].setBounds(buttonX, buttonY, 96, 96);
            button[i].setBackground(new Color(188, 196, 190));
            button[i].setForeground(Color.BLACK);
            Font tempFont = button[i].getFont();
            Font buttonFont = tempFont.deriveFont(20f);
            button[i].setFont(buttonFont);

            this.add(button[i]);

            if (buttonX < 200) {
                buttonX += 96;
            }
            else {
                buttonX = 0;
                buttonY += 96;
            }
        }
        
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new Calculator();
    }
}