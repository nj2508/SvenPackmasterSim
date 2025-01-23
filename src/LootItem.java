public class LootItem {
    private String name;
    private int weight;
    private String pool;
    private int[] amountRange;
    private int amount;
    public LootItem(String n, int w, String p, int[] a) {
        this.name = n;
        this.weight = w;
        this.pool = p;
        this.amountRange = a;
    }
    public String getName() {
        return this.name;
    }
    public int getWeight() {
        return this.weight;
    }
    public String getPool() {
        return this.pool;
    }
    public int[] getRange() {
        return this.amountRange;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getAmount() {
        return this.amount;
    }
}
