

public class QuickUnionWeighted {

    int[] list, weight;

    QuickUnionWeighted(int size) {
        list = new int[size];
        for(int i=0; i<size; i++) list[i] = i;

        weight = new int[size];
        for(int j=0; j<size; j++) weight[j] = 1;
    }

    public void union(int a, int b) {
        int root_a = root(a);
        int root_b = root(b);
        if (this.weight[root_a] >= this.weight[root_b]) {
            this.list[root_b] = root_a;
            this.weight[root_a] += this.weight[root_b];
        } else {
            this.list[root_a] = root_b;
            this.weight[root_b] += this.weight[root_a];
        }
    }

    private int root(int index) {
        while(this.list[index] != index) index = this.list[index];
        return index;
    }

    public boolean check(int a, int b) {
//        print(this.list);
//        print(this.weight);
        return this.root(a) == this.root(b);
    }

    private void print(int[] list) {

        for (int value : list) System.out.print(value + ",");
        System.out.println();
    }

    public static void main(String[] args) {
        QuickUnionWeighted test = new QuickUnionWeighted(15);
        test.union(4,3);
        test.union(3,8);
        test.union(6,5);
        test.union(9,4);
        test.union(2,1);
        test.union(5,0);
        test.union(7,2);
        test.union(6,1);
        test.union(7,3);
        long start = System.nanoTime();
        System.out.println(test.check(1, 9));
        long end = System.nanoTime();
        System.out.println(end - start);

    }
}
