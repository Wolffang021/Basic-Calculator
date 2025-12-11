import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calculator extends JFrame {
    int buttonX = 0;
    int buttonY = 90;
    int maxLength = 10;
    String displayStr = "1287.4";
    String prevDisplayStr = "3247.88+";

    public Calculator() {
        this.setTitle("Basic Calulator");
        this.setSize(302, 488);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.BLACK);
        ImageIcon tempIcon = new ImageIcon("img/appIcon.png");
        Image icon = tempIcon.getImage();
        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.setLayout(null);

        JLabel prevDisplay = new JLabel(prevDisplayStr);
        prevDisplay.setBounds(0, 6, 284, 30);
        prevDisplay.setFont(new Font("dialog", Font.BOLD, 18));
        prevDisplay.setForeground(new Color(3, 30, 80));
        prevDisplay.setHorizontalAlignment(JLabel.RIGHT);
        this.add(prevDisplay);
        
        JLabel curDisplay = new JLabel(displayStr);
        curDisplay.setBounds(0, 34, 284, 60);
        curDisplay.setFont(new Font("dialog", Font.BOLD, 40));
        curDisplay.setForeground(new Color(5, 55, 140));
        curDisplay.setHorizontalAlignment(JLabel.RIGHT);
        this.add(curDisplay);
        
        String[] symbols = {"C", "←", "%", "÷", "7", "8", "9", "x", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
        JButton[] button = new JButton[20];
        for (int i = 0; i < 20; i++) {
            button[i] = new JButton(symbols[i]);
            button[i].setBounds(buttonX, buttonY, 72, 72);
            button[i].setBackground(new Color(5, 55, 140));
            button[i].setForeground(Color.BLACK);
            button[i].setFont(new Font("dialog", Font.BOLD, 32));
            this.add(button[i]);

            button[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Calculator.this.requestFocus();
                    String command = e.getActionCommand();
                    switch (command) {
                        case "C":
                            displayStr = "";
                            prevDisplayStr = "";
                            break;

                        case "←":
                            break;

                        case "%":
                            break;

                        case "+/-":
                            break;

                        case ".":
                            break;

                        case "÷":
                            break;

                        case "x":
                            break;

                        case "-":
                            break;

                        case "+":
                            break;

                        case "=":
                            break;
                    
                        default:
                            displayStr = displayStr + command;
                            break;
                    }

                    curDisplay.setText(displayStr);
                    prevDisplay.setText(prevDisplayStr);
                }
            });

            if (buttonX < 200) {
                buttonX += 72;
            }
            else {
                buttonX = 0;
                buttonY += 72;
            }
        }
        
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    boolean isValidNum(String numStr) {
        return true;
    }

    public static void main(String args[]) {
        new Calculator();
    }
}