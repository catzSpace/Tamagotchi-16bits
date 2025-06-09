import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class PetUtils {

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
}
