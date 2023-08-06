import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SubtitlesPanel extends JPanel {
    private final ButtonActionListener listener = new ButtonActionListener();

    public SubtitlesPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public void display(SubtitlesParser.SubtitlesLine line) {
        this.removeAll();
        String[] words = line.line().split(" ");
        JButton[] buttons = new JButton[words.length];
        for (int i = 0; i < words.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setText(words[i]);
            buttons[i].addActionListener(listener);
            buttons[i].addMouseListener(listener);
            add(buttons[i]);
        }
    }

    private static class ButtonActionListener extends MouseAdapter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            System.out.println("Clicked " + button.getText());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            System.out.println("Entered " + ((JButton) e.getSource()).getText());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            System.out.println("Exited " + ((JButton) e.getSource()).getText());
        }
    }

}
