package cars.rus.Utils;

public class CheckSimple {
  public boolean isSimple(String type) {
    boolean simple = false;
    if (type != null && type.equals("simple")) {
      simple = true;
    }
    return simple;
  }
}
