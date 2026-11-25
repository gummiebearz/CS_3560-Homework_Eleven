import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SnackTest {
    private Snack newSnack;

    @BeforeEach
    void setUp() {
        newSnack = new Snack("Milk Tea", 1.5, 3);
    }

    @Test
    void testGetName() {
        assertEquals("Milk Tea", newSnack.getName());
    }

    @Test
    void testGetQuantity() {
        assertEquals(3, newSnack.getQuantity());
    }

    @Test
    void testGetPrice() {
        assertEquals(1.5, newSnack.getPrice(), 0.001);
    }

    @Test
    void testDecreaseQuantity() {
        newSnack.decreaseQuantity();
        assertEquals(2, newSnack.getQuantity());
    }

    @Test
    void testDecreaseQuantityToZero() {
        newSnack.decreaseQuantity();
        newSnack.decreaseQuantity();
        newSnack.decreaseQuantity();
        assertEquals(0, newSnack.getQuantity());
    }

    @Test
    void testDecreaseQuantityPastZero() {
        newSnack.decreaseQuantity();
        newSnack.decreaseQuantity();
        newSnack.decreaseQuantity();
        newSnack.decreaseQuantity();
        assertEquals(0, newSnack.getQuantity());
    }
}
