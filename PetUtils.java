import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class PetUtils {

    private Timer levelUpTimer;
    private boolean esperandoSubirNivel = false;

    private Timer deathTimer;
    private boolean esperandoMuerte = false;

    private final AddSprite spriteAdder = new AddSprite();

    public static void savePetToFile(Pet pet, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(pet);
            System.out.println("Pet saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving pet: " + e.getMessage());
        }
    }

    public static Pet loadPetFromFile(String filename) {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (Pet) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading pet: " + e.getMessage());
            return null;
        }
    }

    public void verifyPetConditions(Pet pet, JPanel panel, Runnable onDeath) {
        boolean statsAltos = pet.getHunger() > 6 &&
                             pet.getSleep() > 6 &&
                             pet.getFun() > 6 &&
                             pet.getCleanliness() > 6;

        boolean statsBajos = pet.getHunger() < 2 ||
                             pet.getSleep() < 2 ||
                             pet.getFun() < 2 ||
                             pet.getCleanliness() < 2;

        // LEVEL UP
        if (statsAltos && !esperandoSubirNivel) {
            esperandoSubirNivel = true;
            levelUpTimer = new Timer(5000, e -> {
                if (pet.getHunger() > 6 && pet.getSleep() > 6 &&
                    pet.getFun() > 6 && pet.getCleanliness() > 6) {
                    pet.levelUp();
                    System.out.println("🐾 Nivel subido");
                }
                esperandoSubirNivel = false;
            });
            levelUpTimer.setRepeats(false);
            levelUpTimer.start();
        } else if (!statsAltos && esperandoSubirNivel) {
            levelUpTimer.stop();
            esperandoSubirNivel = false;
        }

        // DEAD
        if (statsBajos && !esperandoMuerte) {
            esperandoMuerte = true;
            deathTimer = new Timer(5000, e -> {
                if (pet.getHunger() < 2 || pet.getSleep() < 2 ||
                    pet.getFun() < 2 || pet.getCleanliness() < 2) {
                    System.out.println("☠️ Pet ha muerto");
                    onDeath.run();
                }
                esperandoMuerte = false;
            });
            deathTimer.setRepeats(false);
            deathTimer.start();
        } else if (!statsBajos && esperandoMuerte) {
            deathTimer.stop();
            esperandoMuerte = false;
        }
    }
}
