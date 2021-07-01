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

    public static boolean checkColumn(Vector<Vector<Integer>> puzzle, int element) {

        Vector<Integer> temp;

        for (var member : puzzle) {
            temp = member;

            var isElementFound = searchLinearly(temp, element);
            if (!isElementFound) {
                return false;
            }
        }
        return true;
    }
}
