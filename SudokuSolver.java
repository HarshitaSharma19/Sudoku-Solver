import java.util.Scanner;

public class SudokuSolver {
    public static void main(String[] args) {
        int[][] puzzle = new int[9][9];
        Scanner scanner = new Scanner(System.in);

        // Reading the Sudoku puzzle input
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = scanner.nextInt();
            }
        }
        scanner.close();

        // Solve the puzzle and display the result
        if (solve(puzzle)) {
            printPuzzle(puzzle);
        } else {
            System.out.println("\n\nNO SOLUTION FOUND\n\n");
        }
    }

    // Function to check if a guess is valid
    public static boolean valid(int[][] puzzle, int row, int column, int guess) {
        int cornerX = (row / 3) * 3;
        int cornerY = (column / 3) * 3;

        for (int x = 0; x < 9; x++) {
            if (puzzle[row][x] == guess) return false;
            if (puzzle[x][column] == guess) return false;
            if (puzzle[cornerX + (x % 3)][cornerY + (x / 3)] == guess) return false;
        }
        return true;
    }

    // Function to find an empty cell in the puzzle
    public static boolean findEmptyCell(int[][] puzzle, int[] row, int[] column) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (puzzle[x][y] == 0) {
                    row[0] = x;
                    column[0] = y;
                    return true;
                }
            }
        }
        return false;
    }

    // Recursive function to solve the Sudoku puzzle
    public static boolean solve(int[][] puzzle) {
        int[] row = {0};
        int[] column = {0};

        if (!findEmptyCell(puzzle, row, column)) return true;

        for (int guess = 1; guess <= 9; guess++) {
            if (valid(puzzle, row[0], column[0], guess)) {
                puzzle[row[0]][column[0]] = guess;

                if (solve(puzzle)) return true;
                puzzle[row[0]][column[0]] = 0;
            }
        }
        return false;
    }

    // Function to print the Sudoku puzzle
    public static void printPuzzle(int[][] puzzle) {
        System.out.println("\n+-----+-----+-----+-----+-----+-----+");

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                System.out.print("|" + puzzle[x][y]);
                if (y % 3 == 2) {
                    System.out.print("| ::: ");
                }
            }
            System.out.println();
            if (x % 3 == 2) {
                System.out.println("\n+-----+-----+-----+-----+-----+-----+");
            }
        }
    }
}
