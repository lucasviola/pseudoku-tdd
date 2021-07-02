package com.datastructures.pseudoku;

import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
	public void linearSearch_returnsTrueIfFindsItem() {
		Vector<Integer> vector = new Vector<>();
		vector.add(1);
		vector.add(2);
		vector.add(3);
		vector.add(4);

		var actual = Pseudoku.searchLinearly(vector, 3);

		assertThat(actual).isTrue();
	}

	@Test
	public void linearSearch_returnsFalseIfDoestNotFindItem() {
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
		rows.add(2);
		rows.add(4);
		rows.add(1);
		rows.add(3);
		var puzzle = Pseudoku.makeVector(rows);

		boolean actual = Pseudoku.checkRow(puzzle, 1);

		assertThat(actual).isTrue();
	}

	@Test
	public void shouldBuildTemp() {
		Vector<Vector<Integer>> puzzle = new Vector<>();
		puzzle.add(buildRow(1, 2, 4, 3));
		puzzle.add(buildRow(2, 4, 3, 1));
		puzzle.add(buildRow(4, 1, 2, 3));
		puzzle.add(buildRow(3, 2, 4, 1));
		var j = 1;
		var expectedTemp = new Vector<Vector<Integer>>();
		expectedTemp.add(buildRow(1, 2, 4, 3));
		expectedTemp.add(buildRow(2, 4, 3, 1));
		expectedTemp.add(buildRow(4, 1, 2, 3));
		expectedTemp.add(buildRow(3, 2, 4, 1));

		Vector<Vector<Integer>> actualTemp = Pseudoku.buildTempFromNumberAndPuzzle(j, puzzle);

		assertThat(actualTemp).isEqualTo(expectedTemp);
	}

	private Vector<Integer> buildRow(int i, int i1, int i2, int i3) {
		var row = new Vector<Integer>();
		row.add(i);
		row.add(i1);
		row.add(i2);
		row.add(i3);
		return row;
	}

	@Test
	public void shoudldReturnFalseIfElementIsnotinRow() {
		Vector<Integer> rows = new Vector<>();
		rows.add(1);
		rows.add(2);
		rows.add(3);
		rows.add(3);
		var puzzle = Pseudoku.makeVector(rows);

		boolean actual = Pseudoku.checkRow(puzzle, 4);

		assertThat(actual).isFalse();
	}

	@Test
	public void shoudldReturnFalseIfElemenetIsNotInRow() {
		Vector<Integer> rows = new Vector<>();
		rows.add(1);
		rows.add(2);
		rows.add(3);
		rows.add(0);
		var puzzle = Pseudoku.makeVector(rows);

		boolean actual = Pseudoku.checkRow(puzzle, 4);

		assertThat(actual).isFalse();
	}

	@Test
	public void shouldReturnTrueIfAndOnlyIfAllNumbersFromOneToFourAreInColumnAndRow() {
		Vector<Integer> rows = new Vector<>();
		rows.add(1);
		rows.add(2);
		rows.add(3);
		rows.add(4);
		var puzzle = Pseudoku.makeVector(rows);

		boolean actual = Pseudoku.colCheck(puzzle);

		assertThat(actual).isTrue();
	}

	@Test
	public void shouldReturnFalseIfElementIsNotInPuzzle() {
		Vector<Integer> rows = new Vector<>();
		rows.add(1);
		rows.add(2);
		rows.add(3);
		rows.add(4);
		var puzzle = Pseudoku.makeVector(rows);
		puzzle.get(3).clear();
		puzzle.get(3).add(1);
		puzzle.get(3).add(2);
		puzzle.get(3).add(3);

		boolean actual = Pseudoku.colCheck(puzzle);

		assertThat(actual).isFalse();
	}

	@Test
	public void shouldReturnTrueIf2By2SubgridContainsAllNumbersFromOneToFour() {
		Vector<Integer> rows = new Vector<>();
		Vector<Vector<Integer>> puzzle = new Vector<>();
		puzzle.add(buildRow(1, 2, 0, 0));
		puzzle.add(buildRow(3, 4, 0, 0));
		puzzle.add(buildRow(0, 0, 0, 0));
		puzzle.add(buildRow(0, 0, 0, 0));

		boolean actual = Pseudoku.checkGrid(puzzle, 1, 1, 2, 2);

		assertThat(actual).isEqualTo(true);
	}

//	@Test
//	public void shouldReturnSolvedPuzzle() {
//		Vector<Integer> rows = new Vector<>();
//		rows.add(1);
//		rows.add(2);
//		rows.add(3);
//		rows.add(4);
//		Vector<Integer> expected = new Vector<>();
//		expected.add(3);
//		expected.add(2);
//		expected.add(4);
//		expected.add(1);
//
//		Vector<Vector<Integer>> solution = Pseudoku.makeSolution(rows);
//
//		assertThat(solution).isEqualTo(expected);
//	}


	private Vector<Integer> buildExpected() {
		Vector<Integer> rows = new Vector<>();
		rows.add(3);
		rows.add(2);
		rows.add(4);
		rows.add(1);
		return rows;
	}
}
