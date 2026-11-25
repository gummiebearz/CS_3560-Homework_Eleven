public class Idle implements StateOfVendingMachine {
    public void selectSnack(VendingMachine machine, Snack snack) {
        // Save snack selection and wait for money
        machine.setSelectedSnack(snack);
        machine.setState(new WaitingForMoney());
    }
    public void insertMoney(VendingMachine machine, double amount) {}
    public void dispenseSnack(VendingMachine machine) {}
}
