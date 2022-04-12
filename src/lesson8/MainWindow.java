package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//использовать паттерн Singleton для уникальности основного окна приложения
public class MainWindow extends JFrame {
    private static final int MIN_WIDTH = 450;
    private static final int MIN_HEIGHT = 120;
    private static MainWindow mainWindow;
    private static int counter;

    private static MainWindow getMainWindow(int width, int height) {
        if (mainWindow == null) {
            mainWindow = new MainWindow(width, height);
        }
        return mainWindow;
    }

    private static void refreshCounterView(JLabel counterView) {
        counterView.setText(String.valueOf(counter));
    }

    public static void showMainWindow() {
        showMainWindow(MIN_WIDTH, MIN_HEIGHT);
    }

    public static void showMainWindow(int width, int height) {
        getMainWindow(width, height).setVisible(true);
    }

    private MainWindow(int width, int height) {
        setTitle("CounterApp Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Math.max(width, MIN_WIDTH), Math.max(height, MIN_HEIGHT));
        setLocationRelativeTo(null);
        Font font = new Font("Arial", Font.BOLD, 30);
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout());

        JButton incrementButton = new JButton(">");
        JButton decrementButton = new JButton("<");
        JButton addTenButton = new JButton("+10");
        JButton decreaseTenButton = new JButton("-10");
        JButton clearButton = new JButton("Clear");
        JLabel counterView = new JLabel(String.valueOf(counter));
        counterView.setHorizontalAlignment(SwingConstants.CENTER);
        counterView.setFont(font);

        ActionListener listener = (ActionEvent e) -> {
            Object source = e.getSource();
            if (source == incrementButton) {
                counter++;
            } else if (source == decrementButton) {
                counter--;
            } else if (source == addTenButton) {
                counter += 10;
            } else if (source == decreaseTenButton) {
                counter -= 10;
            } else if (source == clearButton) {
                counter = 0;
            }
            refreshCounterView(counterView);
        };

        incrementButton.addActionListener(listener);
        decrementButton.addActionListener(listener);
        addTenButton.addActionListener(listener);
        decreaseTenButton.addActionListener(listener);
        clearButton.addActionListener(listener);

        innerPanel.add(incrementButton, BorderLayout.EAST);
        innerPanel.add(decrementButton, BorderLayout.WEST);
        innerPanel.add(counterView, BorderLayout.CENTER);

        add(innerPanel, BorderLayout.CENTER);
        add(addTenButton, BorderLayout.EAST);
        add(decreaseTenButton, BorderLayout.WEST);
        add(clearButton, BorderLayout.SOUTH);
    }
}
