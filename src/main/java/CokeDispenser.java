public class CokeDispenser extends SnackDispenseHandler {
    public CokeDispenser(SnackDispenseHandler next) { super(next); }
    boolean handle(Snack snack) { return false; }
}
