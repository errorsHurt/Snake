package gui;

import database.DataAccess;
import database.MySQLCon;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Login {

    public static String playerName;
    public static String playerPassword;
    public String ipSever = getExternalIPv4();
    public static JLabel logInfo = new JLabel("Benutzername oder Passwort falsch!");
    public static JFrame jFrame;
    public static JLabel regInfo = new JLabel("Benutzer wurde erstellt");
    private final int width = 250;
    private final int height = 200;

    public void create() {


        jFrame = new JFrame("Snake-Login");
        jFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        jFrame.setSize(width, height);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        JTextField unField = new JTextField("Username");
        unField.setPreferredSize(new Dimension(200, 24));
        unField.setVisible(true);
        jFrame.add(unField);

        JTextField pwField = new JTextField("Password");
        pwField.setPreferredSize(new Dimension(200, 24));
        pwField.setVisible(true);
        jFrame.add(pwField);

        JTextField ipField = new JTextField("Server-IP");
        ipField.setPreferredSize(new Dimension(200, 24));
        ipField.setText(ipSever);
        ipField.setVisible(true);
        jFrame.add(ipField);

        logInfo.setPreferredSize(new Dimension(215, 10));
        logInfo.setAlignmentX(FlowLayout.CENTER);
        logInfo.setForeground(Color.RED);
        logInfo.setVisible(false);
        jFrame.add(logInfo);

        regInfo.setPreferredSize(new Dimension(215, 10));
        regInfo.setAlignmentX(FlowLayout.CENTER);
        regInfo.setForeground(Color.GREEN);
        regInfo.setVisible(false);
        jFrame.add(regInfo);


        JButton regButton = new JButton("Register");
        regButton.setFocusPainted(false);
        regButton.setSize(100, 50);
        regButton.setVisible(true);
        regButton.addActionListener(e -> {
            Register register = new Register();
            register.create();
        });
        jFrame.add(regButton);

        JButton logButton = new JButton("Login");
        logButton.setFocusPainted(false);
        logButton.setSize(100, 50);
        logButton.setVisible(true);
        logButton.addActionListener(e -> {
            regInfo.setVisible(false);
            MySQLCon con = new MySQLCon(ipField.getText());
            DataAccess.confirmPlayer(unField.getText(), pwField.getText());
        });
        jFrame.add(logButton);

        jFrame.setVisible(true);

    }

    private String getExternalIPv4() {

        try {
            URL wmIp = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(wmIp.openStream()));
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Get the ip yourself...";
    }
}
