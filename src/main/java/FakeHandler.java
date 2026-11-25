public class FakeHandler extends SnackDispenseHandler {
    public FakeHandler(SnackDispenseHandler next) {}
    public boolean handle(Snack snack) { return false; }
}
