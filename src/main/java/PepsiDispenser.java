public class PepsiDispenser extends SnackDispenseHandler {
    public PepsiDispenser(SnackDispenseHandler next) { super(next); }
    boolean handle(Snack snack) { return false; }
}
