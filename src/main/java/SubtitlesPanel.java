import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubtitlesPanel extends JPanel {
    private JButton[] buttons;
    private ButtonActionListener listener = new ButtonActionListener();

    public SubtitlesPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public void display(SubtitlesParser.SubtitlesLine line) {
        this.removeAll();
        String[] words = line.line().split(" ");
        buttons = new JButton[words.length];
        for (int i = 0; i < words.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setText(words[i]);
            buttons[i].addActionListener(listener);
            add(buttons[i]);
        }
    }

    private static class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            System.out.println(button.getText());
        }
    }

}
