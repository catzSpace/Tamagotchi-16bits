import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PhoneLikeWindow().setVisible(true));
    }
}


class PhoneLikeWindow extends JFrame {

    private final JPanel panel;
    private final ScreenManager screenManager;
    private final AddSprite spriteAdder = new AddSprite();
    private final Game game = new Game();
    private final SoundPlayer musicPlayer = new SoundPlayer();

    public String[] listPets = {
      "paracaidas",
      "corazon",
      "ojo",
      "fire",
      "s",
      "cyclo",
      "jerry",
      "dino"
    };

    public PhoneLikeWindow() {
        setTitle("Tamagotchi");
        setSize(450, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel(null);
        panel.setBackground(Color.BLACK);
        setContentPane(panel);

        screenManager = new ScreenManager(panel);

        showIntroScreen();

        musicPlayer.playLoop("assets/app/audio/HomeScreen.wav");
    }


    private void showIntroScreen() {

        spriteAdder.addTemporaryGif(panel, "assets/app/introanimated.gif", 60, 260, 330, 230, 6000, () -> {
          screenManager.loadScreen(this::showMainMenu);
          //System.out.println("a");
        });

    }

    private void showMainMenu() {

        spriteAdder.addGif(panel, "assets/app/MainDraw.gif", 20, 80, 410, 250);

        spriteAdder.addClickableSprite(panel, "assets/app/continue.png", 105, 500, 250, 85, () -> {
            System.out.println("continue");
            if (true){
              SoundPlayer musicPlayer = new SoundPlayer();
              musicPlayer.playEffectSound("assets/app/audio/effects/invalid.wav");
            }
        });

        spriteAdder.addClickableSprite(panel, "assets/app/newgame.png", 105, 600, 250, 85, () -> {
            System.out.println("newgame");
            screenManager.loadScreen(() -> game.selectPet(panel, listPets, musicPlayer, screenManager));
            SoundPlayer musicPlayer = new SoundPlayer();
            musicPlayer.playEffectSound("assets/app/audio/effects/valid.wav");
        });
    }


    private void showLogGame(){ /* TODO */ }
}
