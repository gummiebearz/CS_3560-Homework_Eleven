import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SnackDispenseHandlerTest {
    private static class StubHandler extends SnackDispenseHandler {
        StubHandler(SnackDispenseHandler next) {
            super(next);
        }

        @Override
        boolean handle(Snack snack) {
            return false;
        }
    }

    @Test
    void testConstructorStoresNextHandler() {
        StubHandler next = new StubHandler(null);
        StubHandler handler = new StubHandler(next);
        assertSame(next, handler.next);
    }
}
