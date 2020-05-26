/* *****************************************************************************
 *  Name: Jirc
 *  Date: 2.2
 *  Description: W1Task
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF grid;
    private final int size;
    private int openSiteNum;
    private boolean[] recordOpen;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Create Error");

        size = n; // copy n to private attr size
        openSiteNum = 0;

        grid = new WeightedQuickUnionUF(n * n + 2);
        recordOpen = new boolean[n * n + 2];

        for (int i = 0; i < recordOpen.length; i++) {
            recordOpen[i] = false;
        }
        this.recordOpen[0] = true;
        this.recordOpen[n * n + 1] = true;
    }

    public void open(int row, int col) {
        if (!(this.isValid(row, col))) throw new IllegalArgumentException("Index Error");
        if (this.isOpen(row, col)) {
            return;
        }

        // System.out.println("opened row" + row + ", col" + col);
        int k = this.size;

        int site = (row - 1) * k + col; // its position in the array.

        recordOpen[site] = true;
        openSiteNum++;

        // horizontally connect
        if (col % k == 1) {
            if (recordOpen[site + 1]) this.grid.union(site, site + 1);
        }
        else if (col % k == 0) {
            if (recordOpen[site - 1]) this.grid.union(site, site - 1);
        }
        else {
            if (recordOpen[site + 1]) grid.union(site, site + 1);
            if (recordOpen[site - 1]) grid.union(site, site - 1);
        }

        // vertically connect
        if (row % k == 1) {
            if (recordOpen[site + k]) grid.union(site, site + k);
            grid.union(0, site);
        }
        else if (row % k == 0) {
            if (recordOpen[site - k]) grid.union(site, site - k);
            grid.union(site, k * k + 1);
        }
        else {
            if (recordOpen[site + k]) grid.union(site, site + k);
            if (recordOpen[site - k]) grid.union(site, site - k);
        }
    }

    public boolean isOpen(int row, int col) {
        if (!(this.isValid(row, col))) throw new IllegalArgumentException("Index Error");
        int position = (row - 1) * this.size + col;
        return this.recordOpen[position];
    }

    public boolean isFull(int row, int col) {
        if (!(this.isValid(row, col))) throw new IllegalArgumentException("Index Error");
        int position = (row - 1) * this.size + col;
        return (this.grid.find(position) == this.grid.find(0)) && this.isOpen(row, col);
    }

    public int numberOfOpenSites() {
        return openSiteNum;
    }

    public boolean percolates() {
        return this.grid.find(0) == this.grid.find(size * size + 1);
    }

    private boolean isValid(int row, int col) {
        return (1 <= row && row <= this.size && 1 <= col && col <= this.size);
    }

    private void showGrid() {
        System.out.println(this.recordOpen[0]);
        for (int i = 0; i < this.size; i++) {
            for (int j = 1; j <= this.size; j++) {
                System.out.print(this.recordOpen[j + this.size * i]);
                String str;
                if (this.isFull(i + 1, j)) str = "* ";
                else str = " ";
                System.out.print(str);
                if (this.recordOpen[j + this.size * i]) System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println(this.recordOpen[(this.size) * (this.size) + 1]);
    }


    public static void main(String[] args) {

        int size = 5;

        Percolation test = new Percolation(size);

        do {
            int row = StdRandom.uniform(1, size + 1);
            int col = StdRandom.uniform(1, size + 1);
            test.open(row, col);
        } while (!test.percolates());

        test.showGrid();


        System.out.println("Bottom? " + test.grid.find(size * size + 1));
        System.out.println("UpUpUp? " + test.grid.find(0));
        System.out.println("Perco? " + test.percolates());
        System.out.println("Opened? " + test.numberOfOpenSites());

    }
}
