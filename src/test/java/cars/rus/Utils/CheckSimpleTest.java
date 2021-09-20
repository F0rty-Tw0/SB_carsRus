package cars.rus.Utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CheckSimpleTest {
  CheckSimple checkSimple = new CheckSimple();

  @Test
  public void testIsSimple() {

    assertTrue(checkSimple.isSimple("simple"));
    assertFalse(checkSimple.isSimple(""));
  }
}
