package com.interfaces.utils;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class ButtonView {

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private static final String FONT_NAME = "Arial";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_SIZE = 14;

    private static final Color BUTTON_NORMAL = new Color(70, 130, 180);
    private static final Color BUTTON_HOVER = new Color(100, 149, 237);
    private static final Color BUTTON_PRESSED = new Color(50, 100, 150);


    private JButton button;

    public ButtonView(String title) {
        this.button = create(title);
    }

    public ButtonView(String title, ActionListener action) {
        this.button = create(title);
        this.button.addActionListener(action);
    }

    public static JButton create(String title) {
        JButton button = new JButton(title);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);

        addHoverEffect(button);

        return button;
    }

    private static void addHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_NORMAL);
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_PRESSED);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER);
            }
        });
    }
}
