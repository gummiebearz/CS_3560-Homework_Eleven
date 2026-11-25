public class DispensingSnack implements StateOfVendingMachine {
    public void selectSnack(VendingMachine machine, Snack snack) {}
    public void insertMoney(VendingMachine machine, double amount) {}
    public void dispenseSnack(VendingMachine machine) {
        // Hand the selected snack to the chain; if nobody dispensed it, give the money back
        boolean dispensed = machine.getDispenseHandler().handle(machine.getSelectedSnack());
        if (!dispensed) {
            machine.returnMoney();
        }
        machine.setState(new Idle());
    }
}
