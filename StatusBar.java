import javax.swing.*;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.DoubleStream;

public class StatusBar{

  private AddSprite spriteAdder = new AddSprite();

  public void renderBar(JPanel panel, String name, double value, int x, int y){
    double compareValues[] = {10.0, 9.0, 8.0, 7.0, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0};
    boolean isValueInBar = DoubleStream.of(compareValues).anyMatch(v -> v == value);

    if (isValueInBar){
      int intValue = (int)value;
      String per = Integer.toString(intValue);
      String path = "assets/app/bars/bar" + per + "0p.png";
      spriteAdder.addSprite(panel, name, path, x, y, 177, 24);
    }
  }

};
