public class SnickersDispenser extends SnackDispenseHandler {
    public SnickersDispenser(SnackDispenseHandler next) {
        super(next);
    }

    boolean handle(Snack snack) {
        return false;
    }
}
