
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Oscar Menendez
 *         November 15 2014
 */
public class Knapsack {

    static int maxWeight;
    static int n;
    static int[][] data; //array contains array with profit,weight
    static Queue<Node> usableNodes = new PriorityQueue<Node>();
    static Node root;
    static Node bestNode;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		String file;

        System.out.println("Enter name of data file");
		file = sc.nextLine();
        
        readData(file);
        sort(data);
        printFile(data);
        root = new Node(0, calcBound(0, 0, 0, true), 0, new ArrayList(), 0);
        usableNodes.offer(root);
        bestNode = root;
        branchAndBound();
    }

    /**
     *
     */
    private static void branchAndBound() {
        Node node = null;
        Node right = null;
        Node left = null;
        while (!usableNodes.isEmpty()) {
            node = usableNodes.poll();
            if (node.getBound() >= bestNode.getProfit()) {
                if (node.getLevel() < n) {
                    System.out.println("Exploring " + node.info());
                    left = new Node(node.getProfit(), calcBound(node.getProfit(), node.getLevel(), node.getWeight(), false),
                            node.getWeight(), node.getItemsUsed(), node.getLevel() + 1);
                    System.out.println("\tLeft child is " + left.info());//---> left child

                    right = new Node(node.getProfit(), calcBound(node.getProfit(), node.getLevel(), node.getWeight(), true),
                            node.getWeight(), node.getItemsUsed(), node.getLevel() + 1);
                    right.setProfit(node.getProfit() + data[node.getLevel()][0]);
                    right.setWeight(node.getWeight() + data[node.getLevel()][1]);
                    right.addToUsedList(right.getLevel());

                    if (left.getWeight() > maxWeight) {
                        System.out.println("\t\tpruned because too heavy");
                    } else {
                        if (left.getProfit() > bestNode.getProfit()) {
                            bestNode = left;
                        }
                        if(right.getWeight() == maxWeight){
                        	 System.out.println("\t\texplore further");
                        }
                        else{
                        System.out.println("\t\texplore further");
                        usableNodes.offer(left);
                   		}
                    }
                    System.out.println("\tRight child is " + right.info());//---> right child

                    if (right.getWeight() > maxWeight) {
                        System.out.println("\t\tpruned because too heavy");
                    } else {
                        if (right.getProfit() > bestNode.getProfit()) {
                            bestNode = right;
                        }
                        if(right.getWeight() == maxWeight){
                        	 System.out.println("\t\texplore further");
                        }
                        else{
                        System.out.println("\t\texplore further");
                        usableNodes.offer(right);
                    	}
                    }
                    System.out.println("\t\tnote achievable profit of " + bestNode.getProfit());
                }
            } else {
            	System.out.println("Exploring " + node.info());
                System.out.println("\tpruned, don't explore children, because bound is smaller than known achievable profit\n");
            }
        }
        System.out.println("\n---->Best Node " + bestNode.info());
    }

    private static void printFile(int[][] data) {
        System.out.println("Capacity of knapsack is " + maxWeight);
        System.out.println("after sorting, items are:");
        for (int i = 0; i < data.length; i++)
            System.out.println((i + 1) + ": " + data[i][0] + " " + data[i][1]);
    }

    /**
     * calculates bound for new node
     *
     * @param using, tells whether the item will be used
     * @return bound for new node
     */
    private static double calcBound(int profit, int lvl, int weight, boolean using) {
        double newBound = (double)profit;
        int index;
        if (using)
            index = lvl;
        else {
            index = lvl+1;
        }
        for (int i = index; i < data.length && weight < maxWeight; i++) {
            if ((weight + data[i][1]) > maxWeight && weight < maxWeight) {
                newBound += (maxWeight - weight) * ((double)data[i][0] / (double)data[i][1]);
                weight += (maxWeight - weight);
            } else if ((weight + data[i][1]) <= maxWeight) {
                weight += data[i][1];
                newBound += data[i][0];
            }
        }
        return newBound;
    }

    private static void readData(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            n = sc.nextInt();
            data = new int[n][2];
            maxWeight = sc.nextInt();
            for (int i = 0; i < n; i++) {
                data[i][0] = sc.nextInt();
                data[i][1] = sc.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File " + fileName + " does not exist");
        }
    }

    /**
     * Sorts(using bubble sort) 2d array containing the data(items,weights)
     *
     * @param array 2d to be sorted
     */
    public static void sort(int[][] array) {
        int tmp[];
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++)
                if (array[i][0] / array[i][1] < array[j][0] / array[j][1]) {
                    tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
        }
    }
}
