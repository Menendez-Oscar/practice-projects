
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Menendez on 11/14/2014.
 */
public class Node implements Comparable {

    private int level;
    static int counter = 0;
    int number;
    private double bound;
    private int profit;
    private int weight;
    private List<Integer> itemsUsed = new ArrayList<Integer>();

    public Node(int profit, double bound, int weight, List itemsUsed, int level) {
        this.profit = profit;
        this.bound = bound;
        this.weight = weight;
        this.itemsUsed.addAll(itemsUsed);
        this.level = level;
        counter++;
        this.number = counter;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setBound(double bound) {
        this.bound = bound;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public double getBound() {
        return bound;
    }

    public int getProfit() {
        return profit;
    }

    public int getWeight() {
        return weight;
    }

    public List<Integer> getItemsUsed() {
        return itemsUsed;
    }

    public void addToUsedList(int used) {
        itemsUsed.add(used);
    }

    @Override
    public int compareTo(Object o) {
        Node other = (Node)o;
        return this.bound > other.bound ? -1 : this.bound < other.bound ? 1 : 0;
    }

    public String info(){
        return "<Node " + number + ": items:" + itemsUsed.toString()
                + " level: " + level + " profit: " + profit
                + " weight: " + weight + " bound: " + String.format("%.1f",bound) + ">";
    }
}
