import edu.princeton.cs.algs4.Stack;

public class Board {
    private final int[][] data;
    private final char[] list;
    private final int size;
    private int hamming, manhattan;

    /** BOARD CONSTRUCTOR
     *  [ 0:_, 1:_, ... ]
     * @param tiles int[row][col] row ~ (0, n - 1) / col ~ (0, n - 1)
     *              value: blank = 0, normal [1, n^2 - 1]
     */
    public Board(int[][] tiles) {
        size = tiles.length;
        data = new int[size][size];
        list = new char[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                list[i * size + j] = (char) tiles[i][j];
            }
        }
        for (int i = 0; i < size; i++) {
            System.arraycopy(tiles[i], 0, data[i], 0, size);
        }
        int position;

        /* HAMMING distance */
        for (int i = 0; i < list.length; i++) {
            if (list[i] != i + 1) hamming++;
        }

        /* MANHATTAN distance */
        for (int i = 0; i < list.length; i++) {
            int targetRow = (list[i] - 1) / size, targetCol = (list[i] - 1) % size;
            int currentRow = i / size, currentCol = i % size;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                position = i * size + j;
                if (data[i][j] == position + 1) continue;
                if (data[i][j] == 0) continue;
                int targetRow = (data[i][j] - 1) / size, targetCol = (data[i][j] - 1) % size;
                manhattan += Math.abs(targetRow - i) + Math.abs(targetCol - j);
            }
        }
    }

    public String toString() {
        StringBuilder result;
        result = new StringBuilder(size + "\n");
        int count = 0;
        for (char i : list) {
            
            result.append(" ").append(i + "0");
            if (count % size == size - 1) result.append("\n");
            count++;
        }
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                result.append(" ").append(data[i][j]);
//            }
//            result.append("\n");
//        }
        return result.toString();
    }

    public int dimension() {
        return size;
    }

    public int hamming() { return hamming; }

    public int manhattan() { return manhattan; }

    // check if in place
    public boolean isGoal() {
        int position;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                position = i * size + j;
                if (i == size - 1 && j == size - 1) {
                    if (data[i][j] != 0) return false;
                } else {
                    if (data[i][j] != position + 1) return false;
                }

            }
        }
        return true;
    }

    /** EQUALS METHOD
     *  overrides object method.
     *  you need to check the type and null cases,
     *  before casting the object to the Board type.
     */
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board compare = (Board) y;
        if (this.dimension() != compare.dimension()) return false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.data[i][j] != compare.data[i][j]) return false;
            }
        }
        return true;
    }

    /** NEIGHBORS iterable FUNCTION
     *  you can just return a data structure that implements Iterable!
     */
    public Iterable<Board> neighbors() {
        int[][] neighbors = findNeighbor();
        int[] zero = findBlank();
        Stack<Board> children = new Stack<>();
        for (int[] neighbor : neighbors) {
            children.push(new Board(swap(zero, neighbor)));
        }
        return children;
    }

    public Board twin() {
        int[] zero = findBlank();
        Stack<int[]> positions = new Stack<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == zero[0] && j == zero[1]) continue;
                positions.push(new int[]{i, j});
                if (positions.size() == 2) break;
            }
            if (positions.size() == 2) break;
        }
        int[] first, second;
        first = positions.pop();
        second = positions.pop();
        return new Board(swap(first, second));
    }

    /** HELPER: BLANK POSITION FINDER
     * @return [row, col] of blank element.
     */
    private int[] findBlank() {
        int[] zero = new int[2];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (data[i][j] == 0) {
                    zero[0] = i;
                    zero[1] = j;
                    break;
                }
            }
        }
        return zero;
    }

    /** HELPER: NEIGHBOR Board ARRAY FINDER
     * @return int[][] of all the swappable neighbor
     */
    private int[][] findNeighbor() {
        Stack<int[]> result = new Stack<>();
        int[] zero = this.findBlank();
        int row = zero[0], col = zero[1];

        if (row % size == size - 1) result.push(new int[]{row - 1, col});
        else if (row % size == 0) result.push(new int[]{row + 1, col});
        else {
            result.push(new int[]{row + 1, col});
            result.push(new int[]{row - 1, col});
        }
        if (col % size == size - 1) result.push(new int[]{row, col - 1});
        else if (col % size == 0) result.push(new int[]{row, col + 1});
        else {
            result.push(new int[]{row, col + 1});
            result.push(new int[]{row, col - 1});
        }
        int count = result.size();
        int[][] neighbors = new int[count][2];
        for (int i = 0; i < count; i++) {
            neighbors[i] = result.pop();
        }
        return neighbors;
    }

    /** HELPER: Board ARRAY SWAPPER
     * @return a new data list for duplicate Board object
     */
    private int[][] swap(int[] location1, int[] location2) {
        int[][] list = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(data[i], 0, list[i], 0, size);
        }
        int value1 = list[location1[0]][location1[1]];
        int value2 = list[location2[0]][location2[1]];
        list[location1[0]][location1[1]] = value2;
        list[location2[0]][location2[1]] = value1;
        return list;
    }

    public static void main(String[] args) {
        int[][] hey = new int[][]{new int[]{15, 2, 3}, new int[]{0, 7, 6}, new int[]{5, 4, 8}};
        Board test = new Board(hey);

        System.out.println(test.toString());
    }
}
