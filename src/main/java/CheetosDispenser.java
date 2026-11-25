public class CheetosDispenser extends SnackDispenseHandler {
    public CheetosDispenser(SnackDispenseHandler next) {
        super(next);
    }

    boolean handle(Snack snack) {
        return false;
    }
}
