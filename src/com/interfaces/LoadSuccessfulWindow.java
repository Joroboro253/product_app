package com.interfaces;

import com.interfaces.utils.ButtonView;

import javax.swing.*;
import java.awt.*;

public class LoadSuccessfulWindow extends JFrame {

    private JButton okButton;

    public LoadSuccessfulWindow() {
        setTitle("Load Successful");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        JLabel label = new JLabel("Products loaded successfully", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(0, 0, 0));


        okButton = ButtonView.create("Ok");
        okButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);

        panel.add(label, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}

