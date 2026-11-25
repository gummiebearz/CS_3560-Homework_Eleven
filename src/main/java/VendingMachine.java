import java.util.List;

public class VendingMachine {
    private StateOfVendingMachine state;
    private List<Snack> availableSnacks;
    private Snack selectedSnack;
    private double insertedMoney;
    private SnackDispenseHandler dispenseHandler;

    public VendingMachine(List<Snack> availableSnacks, SnackDispenseHandler dispenseHandler) {}
    void setState(StateOfVendingMachine state) {}
    StateOfVendingMachine getState() { return null; }
    public Snack getSelectedSnack() { return null; }
    public void selectSnack(Snack snack) {}
    public void insertMoney(double amount) {}
    public void dispenseSnack() {}
    void setSelectedSnack(Snack s) {}
    void setInsertedMoney(double amt) {}
    void returnMoney() {}
    public double getInsertedMoney() { return 0.0; }
    public SnackDispenseHandler getDispenseHandler() { return null; }
}
