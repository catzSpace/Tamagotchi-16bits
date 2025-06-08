import javax.swing.*;
import java.awt.Image;
import java.awt.event.*;
import java.awt.*;

public class Game{

  private final SoundPlayer musicPlayer = new SoundPlayer();
  private final AddSprite spriteAdder = new AddSprite();
  private ScreenManager screenManager;


  public void selectPet(JPanel panel, String[] pets, SoundPlayer mainMusic){

    spriteAdder.addSprite(panel, "assets/app/selecttitle.png", 10, 50, 440, 110);

    int col1X = 85;     // X para columna 1
    int col2X = 200;    // X para columna 2
    int startY = 220;   // Y inicial
    int spacingY = 116; // Espacio entre filas

   for (int i = 0; i < pets.length; i++) {
      String pet = pets[i];
      final String currentPet = pet;

      final String path = "assets/selectMenu/" + currentPet + ".gif";

      int x = (i % 2 == 0) ? col1X : col2X;
      int y = startY + (i / 2) * spacingY;


      spriteAdder.addClickableSpriteAnim(panel, path, x, y, 160, 160, () -> {
          System.out.println("Mascota seleccionada: " + currentPet);
          musicPlayer.playEffectSound("assets/app/audio/effects/newGame.wav");
          mainMusic.stop();
          //screenManager.loadScreen(this::newGame(panel, currentPet));
      });
    }
  }

  public static void saved(){}

  // int
  private static void newGame(JPanel panel, String petName){
    //String path = "assets/pets/animations/" + petName + "_intro.gif";
    //spriteAdder.addTemporaryGif(panel, path);
    //musicPlayer.playLoop("assets/app/audio/Game.wav");
  }
}
