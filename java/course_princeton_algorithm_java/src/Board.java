import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Board {
    private final int[][] data;
    private final int size;

    /** BOARD CONSTRUCTOR
     *  {
     *     row 0: [col 0, col 1..]
     *     row 1: ...
     *  }
     * @param tiles int[row][col] row ~ (0, n - 1) / col ~ (0, n - 1)
     *              value: blank = 0, normal [1, n^2 - 1]
     */
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

    public int dimension() {
        return size;
    }

    public int hamming() {
        int count = 0;
        int position;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                position = i * size + j;
                if (data[i][j] != position + 1 && data[i][j] != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public int manhattan() {
        int sum = 0;
        int position;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                position = i * size + j;
                if (data[i][j] == position + 1) continue;
                if (data[i][j] == 0) continue;
                int targetRow = (data[i][j] - 1) / size, targetCol = (data[i][j] - 1) % size;
                sum += Math.abs(targetRow - i) + Math.abs(targetCol - j);
            }
        }
        return sum;
    }

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

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board compare = (Board) y;
        if (this.dimension() != compare.dimension()) return false;
        return this.toString().equals(compare.toString());
    }

    public Iterable<Board> neighbors() {
        int[][] neighbors = findNeighbor();
        int[] zero = findBlank();
        Board[] children = new Board[neighbors.length];
        for (int i = 0; i < neighbors.length; i++) {
            children[i] = new Board(swap(zero, neighbors[i]));
        }
        return () -> new Iterator<Board>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index != children.length;
            }
            @Override
            public Board next() {
                if (!this.hasNext()) throw new NoSuchElementException();
                return children[index++];
            }
        };
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
        Board test = new Board(new int[][]{new int[]{8, 1, 3}, new int[]{4, 0, 2}, new int[]{7, 6, 5}});
        System.out.println(test.toString());
        System.out.println(test.twin().toString());
    }
}
