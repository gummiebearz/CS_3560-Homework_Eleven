import java.util.List;

public class Tester {
    public static void main(String[] args) {
        Snack coke = new Snack("Coke", 2.50, 5);
        Snack pepsi = new Snack("Pepsi", 2.50, 5);
        Snack cheetos = new Snack("Cheetos", 1.75, 3);
        Snack doritos = new Snack("Doritos", 1.75, 3);
        Snack kitkat = new Snack("KitKat", 1.25, 4);
        Snack snickers = new Snack("Snickers", 1.25, 1);

        // The chain is built backwards so that it runs Coke -> Pepsi -> Cheetos -> Doritos -> KitKat -> Snickers
        SnackDispenseHandler snickersDispenser = new SnickersDispenser(null);
        SnackDispenseHandler kitkatDispenser = new KitkatDispenser(snickersDispenser);
        SnackDispenseHandler doritosDispenser = new DoritosDispenser(kitkatDispenser);
        SnackDispenseHandler cheetosDispenser = new CheetosDispenser(doritosDispenser);
        SnackDispenseHandler pepsiDispenser = new PepsiDispenser(cheetosDispenser);
        SnackDispenseHandler cokeDispenser = new CokeDispenser(pepsiDispenser);

        VendingMachine machine = new VendingMachine(
                List.of(coke, pepsi, cheetos, doritos, kitkat, snickers), cokeDispenser);

        System.out.println("=== 1. Buy Coke with exact money ===");
        machine.selectSnack(coke);
        machine.insertMoney(2.50);
        machine.dispenseSnack();
        printStatus(machine, coke);   // quantity goes down, machine is back to Idle

        System.out.println("\n=== 2. Buy Pepsi with too little money, then enough ===");
        machine.selectSnack(pepsi);
        machine.insertMoney(1.00);
        printStatus(machine, pepsi);   // still waiting for money
        machine.insertMoney(2.50);
        machine.dispenseSnack();
        printStatus(machine, pepsi);   // now it is dispensed

        System.out.println("\n=== 3. Buy Cheetos with more than enough money ===");
        machine.selectSnack(cheetos);
        machine.insertMoney(5.00);
        machine.dispenseSnack();
        printStatus(machine, cheetos);

        System.out.println("\n=== 4. Buy KitKat (request goes through most of the chain) ===");
        machine.selectSnack(kitkat);
        machine.insertMoney(1.25);
        machine.dispenseSnack();
        printStatus(machine, kitkat);

        System.out.println("\n=== 5. Buy the last Snickers (quantity hits 0) ===");
        machine.selectSnack(snickers);
        machine.insertMoney(1.25);
        machine.dispenseSnack();
        printStatus(machine, snickers);

        System.out.println("\n=== 6. Buy Snickers again (out of stock, money returned) ===");
        machine.selectSnack(snickers);
        machine.insertMoney(1.25);
        printMoney(machine);   // money is held until the purchase is resolved
        machine.dispenseSnack();
        printMoney(machine);   // sold out, so the money was returned
        printStatus(machine, snickers);
    }

    // Prints the machine's current state and how many of the given snack are left
    private static void printStatus(VendingMachine machine, Snack snack) {
        System.out.println("--- " + snack.getName() + " ---");
        System.out.println("  Machine state: " + machine.getState().getClass().getSimpleName());
        System.out.println("  Quantity left: " + snack.getQuantity());
    }

    // Prints how much money the machine is currently holding
    private static void printMoney(VendingMachine machine) {
        System.out.println("--- Money held ---");
        System.out.println("  $" + String.format("%.2f", machine.getInsertedMoney()));
    }
}
