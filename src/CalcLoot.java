import java.util.*;

public class CalcLoot {

    public static void main(String[] args) {
        LootItem[] options = {
                new LootItem("Guaranteed", 0, "guaranteed", new int[] {33, 41}),
                new LootItem("Main 1", 2500, "main", new int[] {53, 65}),
                new LootItem("Main 2", 1500, "main", new int[] {1, 11}),
                new LootItem("Main 3", 200, "main", new int[] {2, 6}),
                new LootItem("Main 4", 50, "main", new int[] {1, 1}),
                new LootItem("Bonus 1", 20, "bonus", new int[] {1, 4}),
                new LootItem("Bonus 2", 20, "bonus", new int[] {2, 5})
        };
        int trials = 1;
        HashMap<LootItem, Integer> finalRewards = new HashMap<>();
        ArrayList<LootItem> loot = weightedRandomness(options, 4, 200);
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
    public static ArrayList<LootItem> weightedRandomness(LootItem[] lootPool, int dropcount, int trials) {
        ArrayList<LootItem> fullRewardList = new ArrayList<>();
        for (int t = 1; t <= trials; t++) {
            ArrayList<LootItem> rewardList = new ArrayList<>();
            Random random = new Random();
            ArrayList<LootItem> mainPool = new ArrayList<>();
            ArrayList<LootItem> bonusPool = new ArrayList<>();
            ArrayList<LootItem> guaranteedPool = new ArrayList<>();

            for (LootItem l : lootPool) {
                if (l.getPool().equals("main")) {
                    mainPool.add(l);
                } else if (l.getPool().equals("bonus")) {
                    bonusPool.add(l);
                } else if (l.getPool().equals("guaranteed")) {
                    guaranteedPool.add(l);
                }
            }

            // main pool
            int[] cumProbMain = new int[mainPool.size()];
            for (int i = 0; i < mainPool.size(); i++) {
                cumProbMain[i] = mainPool.get(i).getWeight();
            }

            int[] upperLimitMain = new int[mainPool.size()];
            int lowerSumMain = 0;
            int[] lowerLimitMain = new int[mainPool.size()];

            for (int i = 0; i < mainPool.size() ; i++) {
                lowerLimitMain[i] = lowerSumMain;
                upperLimitMain[i] = lowerSumMain + mainPool.get(i).getWeight();
                lowerSumMain = lowerSumMain + mainPool.get(i).getWeight();
            }

            for (int i = 0; i < dropcount; i++) {
                double RNG = 12000 * random.nextDouble();

                for (int k = 0; k < upperLimitMain.length; k++) {
                    if (RNG > lowerLimitMain[k] && RNG < upperLimitMain[k]) {
                        LootItem reward = mainPool.get(k);
                        rewardList.add(mainPool.get(k));
                        int amount = 0;
                        if (reward.getRange()[0] == reward.getRange()[1]) {
                            amount = reward.getRange()[0];
                        } else {
                            amount = random.nextInt(reward.getRange()[1] - reward.getRange()[0]) + reward.getRange()[0];
                        }
                        reward.setAmount(amount);
                    }
                }
            }

            // bonus loot
            double[] cumProbBonus = new double[bonusPool.size()];
            for (int i = 0; i < bonusPool.size(); i++) {
                cumProbBonus[i] = bonusPool.get(i).getWeight();
            }

            double[] upperLimitBonus = new double[bonusPool.size()];
            double lowerSumBonus = 0;
            double[] lowerLimitBonus = new double[bonusPool.size()];

            for (int i = 0; i < bonusPool.size() ; i++) {
                lowerLimitBonus[i] = lowerSumBonus;
                upperLimitBonus[i] = lowerSumBonus + bonusPool.get(i).getWeight();
                lowerSumBonus = lowerSumBonus + bonusPool.get(i).getWeight();
            }
            double RNG = 12000 * random.nextDouble();

            for (int k = 0; k < upperLimitBonus.length; k++) {
                if (RNG > lowerLimitBonus[k] && RNG < upperLimitBonus[k]) {
                    LootItem reward = bonusPool.get(k);
                    rewardList.add(reward);
                    int amount = 0;
                    if (reward.getRange()[0] == reward.getRange()[1]) {
                        amount = reward.getRange()[0];
                    } else {
                        amount = random.nextInt(reward.getRange()[1] - reward.getRange()[0]) + reward.getRange()[0];
                    }
                    reward.setAmount(amount);
                }
            }

            // guaranteed loot
            for (LootItem l : guaranteedPool) {
                l.setAmount(random.nextInt(l.getRange()[1] - l.getRange()[0]) + l.getRange()[0]);
                rewardList.add(l);
            }

            if (trials == 1) {
                return rewardList;
            } else {
                for (LootItem l : rewardList) {
                    fullRewardList.add(l);
                }
            }
        }

        return fullRewardList;
    }
}