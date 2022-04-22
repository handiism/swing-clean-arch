package ui;

import entity.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Login extends JFrame {
    private final service.User userService;

    private JTextField username;
    private JTextField password;
    private JButton login;
    private JPanel panel;
    private JLabel register;

    public Login(service.User userService) {
        super("Login");
        this.userService = userService;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.pack();

        initListener();
    }

    void initListener() {
        login.addActionListener(e -> {
            if (username.getText().length() == 0 || password.getText().length() == 0) {
                Dialog dialog = new Dialog("Please Fill An Empty Field");
                dialog.setVisible(true);
                return;
            }

            boolean usernameIsExist =
                    userService.isUsernameAvailable(username.getText());
            if (usernameIsExist) {
                Dialog dialog = new Dialog("Username Not Found");
                dialog.setVisible(true);
                return;
            }

            entity.User user = new User(username.getText(), password.getText());
            Boolean logged = userService.login(user);
            String text;
            if (logged) {
                text = "Login Success";
            } else {
                text = "Wrong Password";
            }

            Dialog dialog = new Dialog(text);
            dialog.setVisible(true);
        });

        register.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Register register = new Register(userService);
                register.setVisible(true);
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
