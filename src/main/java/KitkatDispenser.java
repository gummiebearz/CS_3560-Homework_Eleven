public class KitkatDispenser extends SnackDispenseHandler {
    public KitkatDispenser(SnackDispenseHandler next) {
        super(next);
    }

    boolean handle(Snack snack) {
        return false;
    }
}
