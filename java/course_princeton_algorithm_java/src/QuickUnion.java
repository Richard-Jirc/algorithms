import java.util.Arrays;

public class QuickUnion {

    private int[] list;

    QuickUnion(int size) {
        list = new int[size];
        for(int i=0; i<size; i++) {
            list[i] = i;
        };
    }

    public void union(int parent, int target) {
        this.list[this.root(target)] = this.root(parent);
    }

    private int root(int index) {
        while(this.list[index] != index) {
            index = this.list[index];
        }
        return index;
    }

    public boolean check(int a, int b) {
        print(this.list);
        return this.root(a) == this.root(b);
    }

    private static void print(int[] list) {
        for (int value : list) System.out.print(value + ",");
    }

    public static void main(String[] args) {

    }
}
