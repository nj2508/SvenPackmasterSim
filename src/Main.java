import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LootItem[] tierOnePool = {
                new LootItem("Wolf Tooth", 0, "guaranteed", new int[] {1, 4})
        };
        Sven one = new Sven(1, tierOnePool);

        LootItem[] tierTwoPool = {
                new LootItem("Wolf Tooth", 0, "guaranteed", new int[] {9, 19}),
                new LootItem("Hamster Wheel", 2000, "main", new int[] {1, 1}),
                new LootItem("Spirit Rune", 83, "bonus", new int[] {1, 1})
        };
        Sven two = new Sven(2, tierTwoPool);

        LootItem[] tierThreePool = {
                new LootItem("Wolf Tooth", 0, "guaranteed", new int[] {30, 51}),
                new LootItem("Hamster Wheel", 2000, "main", new int[] {2, 5}),
                new LootItem("Spirit Rune", 333, "bonus", new int[] {1, 1}),
                new LootItem("Critical VI Book", 50, "main", new int[] {1, 1}),
                new LootItem("Furball", 100, "main", new int[] {1, 1}),
                new LootItem("Red Claw Egg", 5, "main", new int[] {1, 1})
        };
        Sven three = new Sven(3, tierThreePool);

        LootItem[] tierFourPool = {
                new LootItem("Wolf Tooth", 0, "guaranteed", new int[] {50, 65}),
                new LootItem("Hamster Wheel", 2000, "main", new int[] {4, 6}),
                new LootItem("Spirit Rune", 833, "bonus", new int[] {1, 1}),
                new LootItem("Critical VI Book", 100, "main", new int[] {1, 1}),
                new LootItem("Furball", 200, "main", new int[] {1, 1}),
                new LootItem("Red Claw Egg", 15, "main", new int[] {1, 1}),
                new LootItem("Couture Rune", 30, "bonus", new int[] {1, 1}),
                new LootItem("Overflux Capacitor", 5, "bonus", new int[] {1, 1}),
                new LootItem("Grizzly Salmon", 7, "main", new int[] {1, 1})
        };
        Sven four = new Sven(4, tierFourPool);

        Sven[] svenArr = {one, two, three, four};
        System.out.println("What tier would you like to do?");
        int chosen = in.nextInt();
        System.out.println("How many trials would you like to do?");
        int trials = in.nextInt();

        for (Sven tier : svenArr) {
            if (tier.getTier() == chosen) {
                displayRewards(CalcLoot.weightedRandomness(tier.getLootPool(), 1, trials));
            }
        }
    }
    public static void displayRewards(ArrayList<LootItem> loot) {
        HashMap<LootItem, Integer> finalRewards = new HashMap<>();
        HashMap<LootItem, Integer> sum = new HashMap<>();
        for (LootItem l : loot ) {
            sum.put(l, 0);
        }
        for (LootItem l : loot) {
            int count = Collections.frequency(loot, l);
            finalRewards.put(l, count);
            sum.put(l, sum.get(l) + l.getAmount());
        }
        for (Map.Entry<LootItem, Integer> entry : finalRewards.entrySet()) {
            System.out.println(entry.getKey().getName() + " dropcount: " + entry.getValue() + ", amount = " + sum.get(entry.getKey()));
        }
    }
}
