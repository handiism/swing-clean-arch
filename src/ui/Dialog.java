package ui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Dialog extends JDialog {
    private JPanel contentPane;
    private JButton ok;
    private JLabel text;

    public Dialog(String text) {
        this.text.setText(text);
        setContentPane(contentPane);
        setModal(true);
        this.pack();
        this.setLocationRelativeTo(null);
        getRootPane().setDefaultButton(ok);
        initListener();
    }

    private void initListener() {
        ok.addActionListener(e -> dispose());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
}
