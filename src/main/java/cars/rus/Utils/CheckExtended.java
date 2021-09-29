package cars.rus.Utils;

public class CheckExtended {
  public boolean isExtended(String type) {
    boolean extended = false;
    if (type != null && type.equals("extended")) {
      extended = true;
    }
    return extended;
  }
}
