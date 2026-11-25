import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoritosDispenserTest {
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

    private Snack doritos;
    private Snack kitkat;

    @BeforeEach
    void setUp() {
        doritos = new Snack("Doritos", 1.75, 3);
        kitkat = new Snack("KitKat", 1.25, 3);
    }

    @Test
    void testHandleDispensesDoritosWhenInStock() {
        DoritosDispenser dispenser = new DoritosDispenser(null);
        assertTrue(dispenser.handle(doritos));
    }

    @Test
    void testHandleDecreasesDoritosQuantityWhenDispensed() {
        DoritosDispenser dispenser = new DoritosDispenser(null);
        dispenser.handle(doritos);
        assertEquals(2, doritos.getQuantity());
    }

    @Test
    void testHandleDoesNotDispenseDoritosWhenOutOfStock() {
        Snack emptyDoritos = new Snack("Doritos", 1.75, 0);
        DoritosDispenser dispenser = new DoritosDispenser(null);
        assertFalse(dispenser.handle(emptyDoritos));
        assertEquals(0, emptyDoritos.getQuantity());
    }

    @Test
    void testHandleDoesNotPassOutOfStockDoritosToNext() {
        Snack emptyDoritos = new Snack("Doritos", 1.75, 0);
        StubNext next = new StubNext(true);
        DoritosDispenser dispenser = new DoritosDispenser(next);
        assertFalse(dispenser.handle(emptyDoritos));
        assertNull(next.lastHandled);
    }

    @Test
    void testHandlePassesOtherSnackToNext() {
        StubNext next = new StubNext(true);
        DoritosDispenser dispenser = new DoritosDispenser(next);
        assertTrue(dispenser.handle(kitkat));
        assertSame(kitkat, next.lastHandled);
        assertEquals(3, kitkat.getQuantity());
    }

    @Test
    void testHandleReturnsNextResultForOtherSnack() {
        StubNext next = new StubNext(false);
        DoritosDispenser dispenser = new DoritosDispenser(next);
        assertFalse(dispenser.handle(kitkat));
        assertSame(kitkat, next.lastHandled);
    }

    @Test
    void testHandleReturnsFalseForOtherSnackAtEndOfChain() {
        DoritosDispenser dispenser = new DoritosDispenser(null);
        assertFalse(dispenser.handle(kitkat));
        assertEquals(3, kitkat.getQuantity());
    }
}
