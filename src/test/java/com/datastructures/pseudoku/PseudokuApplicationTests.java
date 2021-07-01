package com.datastructures.pseudoku;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Queue;
import java.util.Vector;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PseudokuApplicationTests {

	@Test
	public void shouldReturnFourByFourVectorCreatedFromRowWithFourElements() {
		Vector<Integer> rows = new Vector<>();
		rows.add(1);
		rows.add(2);
		rows.add(3);
		rows.add(4);
		Vector<Vector<Integer>> expected = new Vector<>();
		expected.add(rows);
		expected.add(rows);
		expected.add(rows);
		expected.add(rows);

		Vector<Vector<Integer>> actual = Pseudoku.makeVector(rows);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void shouldPermuteVectorByOneToTheRight() {
		Vector<Integer> row = new Vector<>();
		row.add(2);
		row.add(4);
		row.add(1);
		row.add(3);
		var p = 1;
		Vector<Integer> expected = new Vector<>();
		expected.add(3);
		expected.add(2);
		expected.add(4);
		expected.add(1);

		Vector<Integer> actual = Pseudoku.permuteVector(row, p);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void shouldPermuteVectorByThreeToTheRight() {
		Vector<Integer> row = new Vector<>();
		row.add(2);
		row.add(4);
		row.add(1);
		row.add(3);
		var p = 3;
		var expected = new Vector<>();
		expected.add(4);
		expected.add(1);
		expected.add(3);
		expected.add(2);
		Vector<Integer> actual = Pseudoku.permuteVector(row, p);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void shouldPermuteRowByOneToTheRight() {
		Vector<Integer> rows = new Vector<>();
		rows.add(2);
		rows.add(4);
		rows.add(1);
		rows.add(3);
		var puzzle = Pseudoku.makeVector(rows);
		var expected = buildExpected();

		var actual = Pseudoku.permuteRow(puzzle, 1, 1, 1);

		assertThat(actual.get(0)).isEqualTo(expected);
		assertThat(actual.get(1)).isEqualTo(expected);
		assertThat(actual.get(2)).isEqualTo(expected);
		assertThat(actual.get(3)).isEqualTo(puzzle.get(3));
	}

	@Test
	public void shouldReturnTrueIfItemIsInVector() {
		Vector<Integer> vector = new Vector<>();
		vector.add(1);
		vector.add(2);
		vector.add(3);
		vector.add(4);

		var actual = Pseudoku.searchLinearly(vector, 3);

		assertThat(actual).isTrue();
	}

	@Test
	public void shouldReturnTrFalseIfItemIsNotInVector() {
		Vector<Integer> vector = new Vector<>();
		vector.add(1);
		vector.add(2);
		vector.add(3);
		vector.add(4);

		var actual = Pseudoku.searchLinearly(vector, 0);

		assertThat(actual).isFalse();
	}

	@Test
	public void shoudldReturnTrueIfElemenetIsInPuzzle() {
		Vector<Integer> rows = new Vector<>();
		rows.add(1);
		rows.add(2);
		rows.add(3);
		rows.add(4);
		var puzzle = Pseudoku.makeVector(rows);

		boolean actual = Pseudoku.checkColumn(puzzle, 1);

		assertThat(actual).isTrue();
	}

	@Test
	public void shoudldReturnFalseIfElemenetIsNotInPuzzle() {
		Vector<Integer> rows = new Vector<>();
		rows.add(1);
		rows.add(2);
		rows.add(3);
		rows.add(3);
		var puzzle = Pseudoku.makeVector(rows);

		boolean actual = Pseudoku.checkColumn(puzzle, 4);

		assertThat(actual).isFalse();
	}

	private Vector<Integer> buildExpected() {
		Vector<Integer> rows = new Vector<>();
		rows.add(3);
		rows.add(2);
		rows.add(4);
		rows.add(1);
		return rows;
	}
}
