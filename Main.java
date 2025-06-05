import javax.swing.*;
import java.awt.*;

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
        setSize(540, 960);
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

        spriteAdder.addTemporaryGif(panel, "assets/app/introanimated.gif", 80, 320, 380, 260, 6000, () -> {
          screenManager.loadScreen(this::showMainMenu);
          //System.out.println("a");
        });

    }

    private void showMainMenu() {
        spriteAdder.addClickableSprite(panel, "assets/app/continue.png", 120, 600, 300, 100, () -> {
            System.out.println("continue");
            if (true){
              SoundPlayer musicPlayer = new SoundPlayer();
              musicPlayer.playEffectSound("assets/app/audio/effects/invalid.wav");
            }
        });

        spriteAdder.addClickableSprite(panel, "assets/app/newgame.png", 120, 720, 300, 100, () -> {
            System.out.println("newgame");
            screenManager.loadScreen(() -> game.selectPet(panel, listPets, musicPlayer));  // también puedes pasar screenManager
            SoundPlayer musicPlayer = new SoundPlayer();
            musicPlayer.playEffectSound("assets/app/audio/effects/valid.wav");
        });
    }
}
