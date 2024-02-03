import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int queenNum;
	static boolean[] visitedRow;
	static boolean[] visitedColumn;
	static boolean[] visitedDiagonalUp;
	static boolean[] visitedDiagonalDown;
	static int answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		queenNum = Integer.parseInt(br.readLine());

		visitedRow = new boolean[queenNum];
		visitedColumn = new boolean[queenNum];
		visitedDiagonalUp = new boolean[queenNum * 2 - 1];
		visitedDiagonalDown = new boolean[queenNum * 2 - 1];

		for (int i = 0; i < queenNum; i++) {
			recur(0, i);
		}

		System.out.println(answer / queenNum);
	}

	private static void recur(int row, int column) {
		if (row == queenNum) {
			answer++;
			return;
		}

		if (visitedRow[row] || visitedColumn[column] || visitedDiagonalUp[row + column]
				|| visitedDiagonalDown[row - column + queenNum - 1]) {
			return;
		}

		visitedRow[row] = true;
		visitedColumn[column] = true;
		visitedDiagonalUp[row + column] = true;
		visitedDiagonalDown[row - column + queenNum - 1] = true;
		
		for (int i = 0; i < queenNum; i++) {
			recur(row + 1, i);
		}
		visitedRow[row] = false;
		visitedColumn[column] = false;
		visitedDiagonalUp[row + column] = false;
		visitedDiagonalDown[row - column + queenNum - 1] = false;
	}
}
