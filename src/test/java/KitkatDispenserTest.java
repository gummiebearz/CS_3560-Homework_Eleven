import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KitkatDispenserTest {
    private static class StubNext extends SnackDispenseHandler {
        private final boolean result;
        private Snack lastHandled;

        StubNext(boolean result) {
            super(null);
            this.result = result;
        }

        @Override
        boolean handle(Snack snack) {
            lastHandled = snack;
            return result;
        }
    }

    private Snack kitkat;
    private Snack snickers;

    @BeforeEach
    void setUp() {
        kitkat = new Snack("KitKat", 1.25, 3);
        snickers = new Snack("Snickers", 1.25, 3);
    }

    @Test
    void testHandleDispensesKitkatWhenInStock() {
        KitkatDispenser dispenser = new KitkatDispenser(null);
        assertTrue(dispenser.handle(kitkat));
    }

    @Test
    void testHandleDecreasesKitkatQuantityWhenDispensed() {
        KitkatDispenser dispenser = new KitkatDispenser(null);
        dispenser.handle(kitkat);
        assertEquals(2, kitkat.getQuantity());
    }

    @Test
    void testHandleDoesNotDispenseKitkatWhenOutOfStock() {
        Snack emptyKitkat = new Snack("KitKat", 1.25, 0);
        KitkatDispenser dispenser = new KitkatDispenser(null);
        assertFalse(dispenser.handle(emptyKitkat));
        assertEquals(0, emptyKitkat.getQuantity());
    }

    @Test
    void testHandleDoesNotPassOutOfStockKitkatToNext() {
        Snack emptyKitkat = new Snack("KitKat", 1.25, 0);
        StubNext next = new StubNext(true);
        KitkatDispenser dispenser = new KitkatDispenser(next);
        assertFalse(dispenser.handle(emptyKitkat));
        assertNull(next.lastHandled);
    }

    @Test
    void testHandlePassesOtherSnackToNext() {
        StubNext next = new StubNext(true);
        KitkatDispenser dispenser = new KitkatDispenser(next);
        assertTrue(dispenser.handle(snickers));
        assertSame(snickers, next.lastHandled);
        assertEquals(3, snickers.getQuantity());
    }

    @Test
    void testHandleReturnsNextResultForOtherSnack() {
        StubNext next = new StubNext(false);
        KitkatDispenser dispenser = new KitkatDispenser(next);
        assertFalse(dispenser.handle(snickers));
        assertSame(snickers, next.lastHandled);
    }

    @Test
    void testHandleReturnsFalseForOtherSnackAtEndOfChain() {
        KitkatDispenser dispenser = new KitkatDispenser(null);
        assertFalse(dispenser.handle(snickers));
        assertEquals(3, snickers.getQuantity());
    }
}
