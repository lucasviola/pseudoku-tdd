package com.datastructures.pseudoku;

import java.util.*;

public class Pseudoku {

    public static Vector<Vector<Integer>> makeVector(Vector<Integer> rows) {
        var matrix = new Vector<Vector<Integer>>();
        matrix.add(rows);
        matrix.add(rows);
        matrix.add(rows);
        matrix.add(rows);

        return matrix;
    }

    public static Vector<Integer> permuteVector(Vector<Integer> row, int p) {
        Deque<Integer> queue = new LinkedList<>(); //needs to be an AbstractQueue or just Queue

        for (Integer element : row) {
            queue.add(element);
        }

        Integer store;
        for (var i = 0; i < p; i++) {
            store = queue.getLast();
            queue.removeLast();
            queue.push(store);
        }

        Vector<Integer> permutedRows = new Vector<>();

        for (Integer element : queue) {
            permutedRows.add(element);
        }

        return permutedRows;
    }

    public static Vector<Vector<Integer>> permuteRow(Vector<Vector<Integer>> puzzle, int i, int i1, int i2) {
        Vector<Vector<Integer>> permutedPuzzle = new Vector<>();

        permutedPuzzle.add(permuteVector(puzzle.get(0), i));
        permutedPuzzle.add(permuteVector(puzzle.get(1), i1));
        permutedPuzzle.add(permuteVector(puzzle.get(2), i2));
        permutedPuzzle.add(permuteVector(puzzle.get(2), 0));

        return permutedPuzzle;
    }

    public static boolean searchLinearly(Vector<Integer> vector, int element) {

        for (var i = 0; i < vector.size(); i++) {
            if (vector.get(i) == element) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkRow(Vector<Vector<Integer>> puzzle, int j) {

        for (var i = 0; i <= 3; i++) {

            var isElementFound = searchLinearly(puzzle.get(i), j);
            if (!isElementFound) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkColumn2(Vector<Vector<Integer>> puzzle, int j) {
        Vector<Vector<Integer>> temp = buildTempFromNumberAndPuzzle(j, puzzle);

        for (var i = 0; i <= 3; i++) {

            var isElementFound = searchLinearly(temp.get(0), j);
            if (!isElementFound) {
                return false;
            }
        }

        return true;
    }

    public static boolean colCheck(Vector<Vector<Integer>> puzzle) {
        boolean isElementFound;

        for (var i = 0; i <= 3; i++) {

            isElementFound = checkRow(puzzle, i+1);
            if (!isElementFound) {
                return false;
            }

            for (var j = 0; j <= 3; j++) {

                isElementFound = checkRow(puzzle, j+1);
                if (!isElementFound) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkGrid(Vector<Vector<Integer>> puzzle, int row1, int col1, int row2, int col2) {
        return false;
    }

    public static boolean checkGrids(Vector<Vector<Integer>> puzzle) {

        for (var i = 0; i <= 1; i++) {
            for (var j = 0; j <= 1; j++) {
                if (!checkGrid(puzzle, 1+2*i, 1+2*j, 2+2*i, 2+2*j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Vector<Vector<Integer>> makeSolution(Vector<Integer> rows) {
        Vector<Vector<Integer>> puzzle = makeVector(rows);

        var everySubgridContainEveryNumber = checkGrids(puzzle);
        var everyColumnContainEveryNumber = colCheck(puzzle);

//        while (!everySubgridContainEveryNumber || !everyColumnContainEveryNumber) {
//            puzzle = permuteRow(puzzle, 1, 1, 1);
//        }

        return puzzle;
    }

    public static Vector<Vector<Integer>> buildTempFromNumberAndPuzzle(int j, Vector<Vector<Integer>> puzzle) {

        var temp = new Vector<Vector<Integer>>();

        for (var i = 0; i <= 3; i++) {
            if (i+1 == j) {
                temp.insertElementAt(puzzle.get(j-1), j-1);
            } else {
                temp.insertElementAt(puzzle.get(i), i);
            }

        }

        return temp;
    }
}
