package ui;

import entity.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Register extends JFrame {
    private final service.User userService;
    private JPanel panel;
    private JTextField username;
    private JTextField password;
    private JButton register;
    private JLabel login;

    public Register(service.User userService) {
        super("Register");
        this.userService = userService;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.pack();
        initListener();
    }

    void initListener() {
        register.addActionListener(e -> {
            if (username.getText().length() == 0 || password.getText().length() == 0) {
                Dialog dialog = new Dialog("Please Fill An Empty Field");
                dialog.setVisible(true);
                return;
            }

            Boolean usernameExist =
                    userService.isUsernameAvailable(username.getText());
            if (!usernameExist) {
                Dialog dialog = new Dialog("Username Is Already In Use");
                dialog.setVisible(true);
                return;
            }

            entity.User user = new User(username.getText(), password.getText());
            entity.User temp = userService.register(user);

            String text;
            if (temp != null) {
                text = "User Registration Success";
            } else {
                text = "User Registration Failed";
            }

            Dialog dialog = new Dialog(text);
            dialog.setVisible(true);
        });

        login.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Login login = new Login(userService);
                login.setVisible(true);
                dispose();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }
}
