import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;

public class Board {

    private final int[][] data;
    private final int size;

    // tiles = int[row][col] row ~ (0, n - 1) / col ~ (0, n - 1)
    // {
    // row 0: [col 0, col 1..]
    // row 1: ...
    // }
    // value: blank = 0, normal [1, n^2 - 1]
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
                if (data[i][j] != position + 1) count++;
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

                int targetRow = data[i][j] / size, targetCol = data[i][j] % size;
                if (targetCol == 0) targetCol = size;
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

    public boolean equals(Board y) {
        return this.toString().equals(y.toString());
    }

    public Iterable<Board> neighbors() {
        return new neighborIterable();
    }

    private class neighborIterable implements Iterable<Board>{
        Board[] children;
        public neighborIterable() {
            int[][] neighbors = findNeighbor();
            int[] zero = findBlank();
            children = new Board[neighbors.length];
            for (int i = 0; i < neighbors.length; i++) {
                children[i] = new Board(swap(zero, neighbors[i]));
            }
        }
        @Override
        public Iterator<Board> iterator() {
            return new Iterator<Board>() {
                int index = 0;
                @Override
                public boolean hasNext() {
                    return index != children.length;
                }
                @Override
                public Board next() {
                    return children[index++];
                }
            };
        }
    }

    public Board twin() {
        int[] randomOne = this.findNeighbor()[0];
        int[] zero = this.findBlank();
        return new Board(swap(zero, randomOne));
    }

    // just the [row, col] of blank element.
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

    // returns all the swappable neighbor
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

    // return a new data list for duplicate Board object.
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
        int[][] array = new int[5][5];
        int k = 1;
        for (int[] i : array) {
            int p = 0;
            for (int j : i) {
                i[p++] = k;
                k++;
            }
        }
        array[3][3] = 0;
        Board test = new Board(array);
        System.out.println(test.toString());
    }
}
