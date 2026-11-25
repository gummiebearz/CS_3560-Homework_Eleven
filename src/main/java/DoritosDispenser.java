public class DoritosDispenser extends SnackDispenseHandler {
    public DoritosDispenser(SnackDispenseHandler next) {
        super(next);
    }

    boolean handle(Snack snack) {
        return false;
    }
}
