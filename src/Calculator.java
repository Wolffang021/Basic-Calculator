import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calculator extends JFrame {
    int buttonX = 0;
    int buttonY = 90;
    int maxLength = 10;
    char prevOperaton = ' ';
    String displayStr = "0";
    String prevDisplayStr = "";

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
        prevDisplay.setForeground(new Color(41, 56, 82));
        prevDisplay.setHorizontalAlignment(JLabel.RIGHT);
        this.add(prevDisplay);
        
        JLabel curDisplay = new JLabel(displayStr);
        curDisplay.setBounds(0, 34, 284, 60);
        curDisplay.setFont(new Font("dialog", Font.BOLD, 40));
        curDisplay.setForeground(new Color(60, 83, 120));
        curDisplay.setHorizontalAlignment(JLabel.RIGHT);
        this.add(curDisplay);
        
        String[] symbols = {"C", "←", "%", "÷", "7", "8", "9", "x", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
        JButton[] button = new JButton[20];
        for (int i = 0; i < 20; i++) {
            button[i] = new JButton(symbols[i]);
            button[i].setBounds(buttonX, buttonY, 72, 72);
            button[i].setBackground(new Color(60, 83, 120));
            button[i].setForeground(new Color(21, 26, 31));
            button[i].setFont(new Font("dialog", Font.BOLD, 32));
            this.add(button[i]);

            button[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Calculator.this.requestFocus();
                    Boolean fractionInput = false;

                    String command = e.getActionCommand();
                    switch (command) {
                        case "C":
                            displayStr = "0";
                            prevDisplayStr = "";
                            prevOperaton = ' ';
                            break;

                        case "←":
                            if (displayStr.equals("NaN") || displayStr.equals("Infinity") || displayStr.equals("Error")) {
                                displayStr = "0";
                            }

                            if (displayStr.length() > 0) {
                                displayStr = displayStr.substring(0, displayStr.length() - 1);

                                if (displayStr.equals("") || displayStr.equals("-")) {
                                    displayStr = "0";
                                }
                            }
                            break;

                        case "%":
                            if (displayStr.length() > 0) {
                                displayStr = String.format("%.10f", Double.parseDouble(displayStr) / 100).replaceAll("0+$", "");
                            }
                            break;

                        case "+/-":
                            if (displayStr.length() > 0 && !displayStr.equals("0")) {
                                displayStr = String.format("%.10f", 0 - Double.parseDouble(displayStr)).replaceAll("0+$", "");
                            }
                            break;

                        case ".":
                            if (displayStr.equals("NaN") || displayStr.equals("Infinity") || displayStr.equals("Error")) {
                                displayStr = "0";
                            }

                            int temp = displayStr.contains("-") ? 11 : 10;

                            if (displayStr.length() < temp && !displayStr.contains(".")) {
                                displayStr = displayStr.concat(".");
                                fractionInput = true;
                            }
                            break;

                        case "÷":
                            if (prevOperaton == ' ') {
                                prevDisplayStr = displayStr + " ÷";
                            }
                            else {
                                prevDisplayStr = Operate(prevDisplayStr.substring(0, prevDisplayStr.length() - 2), displayStr, prevOperaton).concat(" ÷");
                            }
                            prevOperaton = '÷';
                            displayStr = "0";
                            break;

                        case "x":
                            if (prevOperaton == ' ') {
                                prevDisplayStr = displayStr + " x";
                            }
                            else {
                                prevDisplayStr = Operate(prevDisplayStr.substring(0, prevDisplayStr.length() - 2), displayStr, prevOperaton).concat(" x");
                            }
                            prevOperaton = 'x';
                            displayStr = "0";
                            break;

                        case "-":
                            if (prevOperaton == ' ') {
                                prevDisplayStr = displayStr + " -";
                            }
                            else {
                                prevDisplayStr = Operate(prevDisplayStr.substring(0, prevDisplayStr.length() - 2), displayStr, prevOperaton).concat(" -");
                            }
                            prevOperaton = '-';
                            displayStr = "0";
                            break;

                        case "+":
                            if (prevOperaton == ' ') {
                                prevDisplayStr = displayStr + " +";
                            }
                            else {
                                prevDisplayStr = Operate(prevDisplayStr.substring(0, prevDisplayStr.length() - 2), displayStr, prevOperaton).concat(" +");
                            }
                            prevOperaton = '+';
                            displayStr = "0";
                            break;

                        case "=":
                            if (!prevDisplayStr.equals("") && prevOperaton != ' ') {
                                String tempStr = displayStr;
                                displayStr = Operate(prevDisplayStr.substring(0, prevDisplayStr.length() - 2), displayStr, prevOperaton);
                                prevDisplayStr = tempStr;
                                prevOperaton = ' ';
                            }
                            break;
                    
                        default:
                            if (displayStr.contains("-") || displayStr.contains(".")) {
                                maxLength = 11;
                                if (displayStr.contains("-") && displayStr.contains(".")) {
                                    maxLength = 12;
                                }
                            }
                            else {
                                maxLength = 10;
                            }

                            if (displayStr.equals("NaN") || displayStr.equals("Infinity") || displayStr.equals("Error")) {
                                displayStr = "0";
                            }

                            if (displayStr.length() < maxLength) {
                                if (displayStr.equals("0")){
                                    displayStr = command;
                                }
                                else {
                                    displayStr += command;
                                }
                            }
                            break;
                    }

                    if (displayStr.length() > 0) {
                        if (displayStr.charAt(displayStr.length() - 1) == '.' && !fractionInput) {
                            displayStr = displayStr.substring(0, displayStr.length() - 1);
                        }

                        if (displayStr.equals("-0")) {
                            displayStr = "0";
                        }
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

        JLabel creditDisplay = new JLabel("made by Shourjo");
        creditDisplay.setBounds(2, 55, 284, 60);
        creditDisplay.setFont(new Font("dialog", Font.BOLD, 6));
        creditDisplay.setForeground(new Color(30, 31, 33));
        creditDisplay.setHorizontalAlignment(JLabel.LEFT);
        this.add(creditDisplay);

        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    String Operate(String num1, String num2, char operation) {
        String answer = "Error";
        switch (operation) {
            case '÷':
                answer = String.format("%.10f", Double.parseDouble(num1) / Double.parseDouble(num2)).replaceAll("0+$", "");
                break;

            case 'x':
                answer = String.format("%.10f", Double.parseDouble(num1) * Double.parseDouble(num2)).replaceAll("0+$", "");
                break;

            case '-':
                answer = String.format("%.10f", Double.parseDouble(num1) - Double.parseDouble(num2)).replaceAll("0+$", "");
                break;

            case '+':
                answer = String.format("%.10f", Double.parseDouble(num1) + Double.parseDouble(num2)).replaceAll("0+$", "");
                break;
        }

        if (answer.charAt(answer.length() - 1) == '.') {
            answer = answer.substring(0, answer.length() - 1);
        }

        return answer;
    }

    public static void main(String args[]) {
        new Calculator();
    }
}