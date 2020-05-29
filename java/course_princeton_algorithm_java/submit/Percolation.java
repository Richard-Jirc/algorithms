import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    WeightedQuickUnionUF site;
    boolean[] open;
    int open_num;

    static int size;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        int length = n * n + 2;
        size = n;
        this.site = new WeightedQuickUnionUF(length);
        this.open = new boolean[length];
        this.open[0] = true;
        this.open[length - 1] = true;
        for (int i=1; i<n+1; i++) {
            this.site.union(0, i);
            this.site.union(i + n*(n-1), n*n - 1);
        }
    }

    public void open(int x, int y) {
        if (x < 1 && y < 1 && x > size && y > size) throw new IllegalArgumentException();
        if (this.isOpen(x, y)) return;

        this.open[position(x, y)] = true;
        open_num++;

        int[] neighbor = openNeighbor(x, y);
        for (int i: neighbor) {
            if (i == -1) continue;
            if (!this.open[i]) this.site.union(i, position(x, y));
        }
    }

    public boolean isOpen(int x, int y) {
        return this.open[position(x, y)];
    }

    public boolean isFull(int x, int y) {
        return this.site.find(position(x, y)) == 0;
    }

    public int numberOfOpenSites() {
        return this.open_num;
    }

    public boolean percolates() {
        return this.site.find(size * size) == 0;
    }

    private static int position(int x, int y) {
        if (x < 1 && y < 1 && x > size && y > size) return -1;
        return (y - 1) * size + x;
    }

    private static int[] openNeighbor(int x, int y) {
        int[] list = new int[4];
        if (position(x-1, y) == -1) {
            list[0] = -1;
        } else {
            list[0] = position(x-1, y);
        }
        if (position(x, y-1) == -1) {
            list[1] = -1;
        } else {
            position(x, y-1);
        }
        if (position(x+1, y) == -1) {
            list[2] = -1;
        } else {
            list[2] = position(x+1, y);
        }
        if (position(x, y+1) == -1) {
            list[3] = -1;
        } else {
            list[3] = position(x, y+1);
        }
        return list;
    }

//    private static void print(boolean[] list, int size) {
//        for(int i=0; i<list.length; i++) {
//            System.out.print(list[i] + " ");
//            if (i == 0) System.out.println();
//            else if (i % size == 0) System.out.println();
//            if (i == list.length - 1) System.out.println();
//        }
//        System.out.println();
//    }

    public static void main(String[] args) {
        int n = 5;
        Percolation test = new Percolation(n);
        test.open(1,5);
        test.open(1,4);
        test.open(1,3);
        test.open(1,2);
        test.open(1,1);
//        print(test.open, n);
//        System.out.println(test.percolates());
    }
}
