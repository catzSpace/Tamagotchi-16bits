import javax.swing.*;
import java.awt.Image;
import java.awt.event.*;
import java.awt.*;
import javax.swing.Timer;

public class Game{

  private final SoundPlayer musicPlayer = new SoundPlayer();
  private final AddSprite spriteAdder = new AddSprite();
  private ScreenManager screenManager;
  private final PetUtils petUtils = new PetUtils();


  public void selectPet(JPanel panel, String[] pets, SoundPlayer mainMusic, ScreenManager _screenManager){

    spriteAdder.addSprite(panel, "assets/app/selecttitle.png", 10, 50, 440, 110);
    screenManager = _screenManager;

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
          screenManager.loadScreen(() -> newGame(panel, currentPet));
      });
    }
  }


  public void loadGame(JPanel panel, String petName){
    screenManager.clearScreen();
    screenManager.loadScreen(() -> startGame(panel, petName, "load"));
  }


  private void newGame(JPanel panel, String petName){
    String introAnimPath = "assets/pets/animations/born-scene.gif";

    spriteAdder.addTemporaryGif(panel, introAnimPath, 100, 300, 300, 300, 5800, ()-> {
        screenManager.clearScreen();
        spriteAdder.addTemporaryGif(panel, "assets/app/transition.gif", 0, 0, 450, 800, 3800, () ->{
          screenManager.clearScreen();
          screenManager.loadScreen(() -> startGame(panel, petName, "new"));
        });
    });
  }

  private void startGame(JPanel panel, String petName, String status) {
    String petIntro = "assets/pets/animations/" + petName + "_intro.gif";
    String petMainAnimation = "assets/pets/animations/" + petName + ".gif";
    String FileToSave = "data/" + petName + ".bin";
    spriteAdder.addTemporaryGif(panel, petIntro, 125, 300, 200, 200, 3000, () -> {

      screenManager.clearScreen();
      spriteAdder.addGif(panel, petMainAnimation, 125, 300, 200, 200);

      musicPlayer.playLoop("assets/app/audio/Game.wav");

      if (status == "new") {
        Pet pet = new Pet(petName);

        Timer autoSaveTimer = new Timer(2000, e -> {
          pet.degradeStats();
          petUtils.savePetToFile(pet, FileToSave);
          System.out.println(pet);
          if (pet.getFun() == 8.2){
            System.out.println("xd");
          }
        });

        autoSaveTimer.start();

      } else {
        Pet pet = petUtils.loadPetFromFile("petdata.bin");
        System.out.println(pet);

        Timer autoSaveTimer = new Timer(2000, e -> {
          pet.degradeStats();
          petUtils.savePetToFile(pet, FileToSave);
          System.out.println(pet);
        });

        autoSaveTimer.start();
      }

    });
  }

}
