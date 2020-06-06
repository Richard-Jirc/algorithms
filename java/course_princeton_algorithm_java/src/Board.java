public class Board {

    private final int[][] data;
    private final int size;

    public Board(int[][] tiles) {
        size = tiles.length;
        data = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(tiles[i], 0, data[i], 0, size);
        }
    }

    public String toString() {
        StringBuilder result;
        result = new StringBuilder(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.append(" ").append(data[i][j]);
            }
            result.append("\n");
        }
        return result.toString();
    }
//
//    public int dimension() {
//
//    }
//
//    public int hamming() {
//
//    }
//
//    public int manhattan() {
//
//    }
//
//    public boolean isGoal() {
//
//    }
//
//    public boolean equals(Object y) {
//
//    }
//
//    public Iterable<Board> neightbors() {
//
//    }
//
//    public Board twin() {
//
//    }

    public static void main(String[] args) {
        int[][] array = new int[5][5];
        Board test = new Board(array);
        System.out.print(test.toString());
    }
}
