public class Sven {
    private int tier;
    private LootItem[] lootpool;
    public Sven (int t, LootItem[] lp) {
        this.tier = t;
        this.lootpool = lp;
    }
    public int getTier() {
        return this.tier;
    }
    public LootItem[] getLootPool() {
        return this.lootpool;
    }
}
