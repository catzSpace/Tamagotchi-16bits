import javax.swing.*;

public class ScreenManager {

    private final JPanel panel;

    public ScreenManager(JPanel panel) {
        this.panel = panel;
    }

    public void loadScreen(Runnable screenBuilder) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        screenBuilder.run();
    }

    public void clearScreen(){
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    public JPanel getPanel() {
        return panel;
    }
}
