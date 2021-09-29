package cars.rus.Utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CheckExtendedTest {
  CheckExtended checkExtended = new CheckExtended();

  @Test
  public void testIsExtended() {
    assertTrue(checkExtended.isExtended("extended"));
    assertFalse(checkExtended.isExtended(""));
  }
}
