import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF site;
    private final boolean[] open;
    private int openNum;
    private final int size;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        int length = n * n + 2;
        size = n;
        site = new WeightedQuickUnionUF(length);
        open = new boolean[length];
        open[0] = true;
        open[length - 1] = true;
//        for (int i=1; i<n+1; i++) {
//            site.union(0, i);
//            site.union(i + n*(n-1), n*n + 1);
//        }
    }

    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) throw new IllegalArgumentException();
        if (this.isOpen(row, col)) return;

        open[this.position(row, col)] = true;
        openNum++;

        int[] neighbor = this.openNeighbor(row, col);
        for (int i: neighbor) {
            if (i == -1) continue;
            if (open[i]) site.union(i, this.position(row, col));
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) throw new IllegalArgumentException();
        return open[position(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (col < 1 || row < 1 || col > size || row > size) throw new IllegalArgumentException();
        if (!this.isOpen(row, col)) return false;
        return site.find(this.position(row, col)) == site.find(0);
    }

    public int numberOfOpenSites() {
        return openNum;
    }

    public boolean percolates() {
        return site.find(size * size + 1) == site.find(0);
    }

    private int position(int row, int col) {
        if (col < 1 || col > size) return -1;
        if (row == 0) return 0;
        else if (row == size + 1) return size * size + 1;
        return (row - 1) * size + col;
    }

    private int[] openNeighbor(int row, int col) {
        int[] list = new int[4];
        int[] value = {this.position(row, col - 1), this.position(row - 1, col), this.position(row, col + 1), this.position(row + 1, col)};
        for (int k = 0; k < 4; k++) {
            if (value[k] == -1) {
                list[k] = -1;
            } else {
                list[k] = value[k];
            }
        }
//        for(int i: list) System.out.print(i + ",");
//        System.out.println();
        return list;
    }

//    private static void print(boolean[] list, int size) {
//        for(int i=0; i<list.length; i++) {
//            System.out.print(list[i] + " ");
//            if (i == 0) System.out.println();
//            else if (i % size == 0) System.out.println();
//            if (i == list.length - 1) System.out.println();
//        }
//    }


    public static void main(String[] args) {
        int n = 6;
        Percolation test = new Percolation(n);
        test.open(1, 6);
//        test.open(6, 2);
//        test.open(6, 1);
//        System.out.println(test.site.find(0));
        System.out.println("Full? " + test.isFull(1,6));
        System.out.println("Perc? " + test.percolates());
    }
}
