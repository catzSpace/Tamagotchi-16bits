import javax.swing.*;
import java.awt.Image;
import java.awt.event.*;
import java.awt.*;

public class Game{

  public void selectPet(JPanel panel, String[] pets, SoundPlayer mainMusic){
    AddSprite spriteAdder = new AddSprite();

    spriteAdder.addSprite(panel, "assets/app/selecttitle.png", 55, 40, 440, 110);

    int col1X = 100;     // X para columna 1
    int col2X = 240;    // X para columna 2
    int startY = 230;   // Y inicial
    int spacingY = 140; // Espacio entre filas

   for (int i = 0; i < pets.length; i++) {
    String pet = pets[i];
    final String currentPet = pet;

    final String path = "assets/selectMenu/" + currentPet + ".gif";

    int x = (i % 2 == 0) ? col1X : col2X;
    int y = startY + (i / 2) * spacingY;


    spriteAdder.addClickableSpriteAnim(panel, path, x, y, 200, 200, () -> {
        System.out.println("Mascota seleccionada: " + currentPet);
        SoundPlayer musicPlayer = new SoundPlayer();
        musicPlayer.playEffectSound("assets/app/audio/effects/newGame.wav");
        mainMusic.stop();
    });
  }


  }

  public static void saved(){}

  // int
  public static void newGame(String petName){
    
  }
}
