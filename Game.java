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
  private StatusBar bar = new StatusBar();
  private Timer autoSaveTimer;


  public void selectPetMenu(JPanel panel, String[] pets, SoundPlayer mainMusic, ScreenManager _screenManager){

    spriteAdder.addSprite(panel, "selectTitle", "assets/app/selecttitle.png", 10, 50, 440, 110);
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

  public void loadPetMenu(JPanel panel, String[] pets, SoundPlayer mainMusic, ScreenManager _screenManager){

    screenManager = _screenManager;

    int x = 20;         // X fijo para todos (centrado)
    int startY = 80;    // Y inicial
    int spacingY = 180;  // Más espacio para que no se encimen

    for (int i = 0; i < pets.length; i++) {
        String pet = pets[i];
        final String currentPet = pet.replace(".bin", "");

        final String path = "assets/selectMenu/" + currentPet + ".gif";

        int y = startY + i * spacingY;

        spriteAdder.addClickableSpriteAnim(panel, path, x, y, 260, 160, () -> {
            System.out.println("Mascota seleccionada: " + currentPet);
            musicPlayer.playEffectSound("assets/app/audio/effects/newGame.wav");
            mainMusic.stop();
            screenManager.loadScreen(() -> startGame(panel, currentPet, "loaded"));
        });
    }
  }


  private void newGame(JPanel panel, String petName){
    
    // EGG OPEN ANIMATION
    String introAnimPath = "assets/pets/animations/born-scene.gif";

    spriteAdder.addTemporaryGif(panel, introAnimPath, 100, 300, 300, 300, 5800, ()-> {
        screenManager.clearScreen();
        spriteAdder.addTemporaryGif(panel, "assets/app/transition.gif", 0, 0, 450, 800, 3800, () ->{
          screenManager.clearScreen();
          screenManager.loadScreen(() -> startGame(panel, petName, "new"));
        });
    });
  }

  private void statsButtonsOnScreen(JPanel panel, Pet pet){
    spriteAdder.addClickableSprite(panel, "assets/pets/actions/eat.png", 30, 550, 96, 88, () -> {
      pet.eat();
      musicPlayer.playEffectSound("assets/app/audio/effects/actionbtn.wav");
    });

    spriteAdder.addClickableSprite(panel, "assets/pets/actions/clean.png", 30, 650, 96, 88, () -> {
      pet.bathe();
      musicPlayer.playEffectSound("assets/app/audio/effects/actionbtn.wav");
    });

    spriteAdder.addClickableSprite(panel, "assets/pets/actions/sleep.png", 140, 550, 96, 88, () -> {
      pet.sleep();
      musicPlayer.playEffectSound("assets/app/audio/effects/actionbtn.wav");
    });

    spriteAdder.addClickableSprite(panel, "assets/pets/actions/play.png", 140, 650, 96, 88, () -> {
      pet.play();
      musicPlayer.playEffectSound("assets/app/audio/effects/actionbtn.wav");
    });
  }

  private void deadScene(JPanel panel, Pet pet, String petName){
    musicPlayer.stop();
    screenManager.clearScreen();
  }

  private void petStatsOnScreen(JPanel panel, Pet pet){
    double hunger = pet.getHunger();
    double sleep = pet.getSleep();
    double fun = pet.getFun();
    double cleanliness = pet.getCleanliness();

    bar.renderBar(panel, "hunger", hunger, 30, 85);
    bar.renderBar(panel, "sleep", sleep, 30, 115);
    bar.renderBar(panel, "fun", fun, 30, 145);
    bar.renderBar(panel, "cleanliness", cleanliness, 30, 175);

    // ISSUES BANNERS CONTAINER
    // spriteAdder.addSprite(panel, );

    // ISSUES BANNERS
    if (hunger <= 3){
      spriteAdder.addDescartableGif(panel, "needfood", "assets/app/needfood.gif", 0, 250, 200, 64, 4000);
    }

    if (sleep <= 3){
      spriteAdder.addDescartableGif(panel, "needsleep", "assets/app/needsleep.gif", 320, 580, 50,50, 1000);
    }

    if (fun <= 3){
      spriteAdder.addDescartableGif(panel, "needfun", "assets/app/needfun.gif", 285, 660, 50,50, 1000);
    }

    if (cleanliness <= 3){
      spriteAdder.addDescartableGif(panel, "needcleanliness", "assets/app/needcleanliness.gif", 365, 660, 50,50, 1000);
    } 

  }

  private void startGame(JPanel panel, String petName, String status) {

    // INTRO PET ANIMATION
    String petIntro = "assets/pets/animations/" + petName + "_intro.gif";
    String petMainAnimation = "assets/pets/animations/" + petName + ".gif";

    // FILE NAME
    String FileToSave = "data/" + petName + ".bin";

    // PET ANIMATION
    spriteAdder.addTemporaryGif(panel, petIntro, 125, 300, 200, 200, 3000, () -> {

      screenManager.clearScreen();
      spriteAdder.addGif(panel, "assets/app/bg.gif", -10, 60, 480, 660);
      spriteAdder.addGif(panel, petMainAnimation, 125, 300, 200, 200);
      spriteAdder.addSprite(panel, "issuesList", "assets/app/petissues.png", 270, 540, 156, 200);

      musicPlayer.playLoop("assets/app/audio/Game.wav");

      if (status == "new") {
        Pet pet = new Pet(petName);

        autoSaveTimer = new Timer(2000, e -> {
          petStatsOnScreen(panel, pet);
          pet.degradeStats();
          petUtils.savePetToFile(pet, FileToSave);
          petUtils.verifyPetConditions(pet, panel, () -> {
            autoSaveTimer.stop();
            deadScene(panel, pet, pet.getName());
          });
        });

        // ACTIONS BUTTONS
        statsButtonsOnScreen(panel, pet);

        autoSaveTimer.start();

      } else {

        Pet pet = petUtils.loadPetFromFile(FileToSave);

        autoSaveTimer = new Timer(2000, e -> {
          petStatsOnScreen(panel, pet);
          pet.degradeStats();
          petUtils.savePetToFile(pet, FileToSave);
          petUtils.verifyPetConditions(pet, panel, () -> {
            autoSaveTimer.stop();
            deadScene(panel, pet, pet.getName());
          });
        });

        // ACTIONS BUTTONS
        statsButtonsOnScreen(panel, pet);


        autoSaveTimer.start();
      }

    });
  }

}
