import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static class PointMNH {
		int tomatoM;
		int tomatoN;
		int tomatoH;

		public PointMNH(int m, int n, int h) {
			this.tomatoM = m;
			this.tomatoN = n;
			this.tomatoH = h;
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int tomatoM = sc.nextInt();
		int tomatoN = sc.nextInt();
		int tomatoH = sc.nextInt();

		int[][][] tomatoBox = new int[tomatoM][tomatoN][tomatoH];

		for (int k = 0; k < tomatoH; k++) {
			for (int i = 0; i < tomatoN; i++) {
				for (int j = 0; j < tomatoM; j++) {
					tomatoBox[j][i][k] = sc.nextInt();
				}
			}
		}

		Queue<PointMNH> q = new LinkedList<>();
		for (int k = 0; k < tomatoH; k++) {
			for (int i = 0; i < tomatoN; i++) {
				for (int j = 0; j < tomatoM; j++) {
					if (tomatoBox[j][i][k] == 1) {
						q.add(new PointMNH(j, i, k));
					}
				}
			}
		}

		int[] dx = { 1, 0, 0, -1, 0, 0 };
		int[] dy = { 0, 1, 0, 0, -1, 0 };
		int[] dh = { 0, 0, 1, 0, 0, -1 };

		//boolean[][][] visited = new boolean[tomatoM][tomatoN][tomatoH];

		int count = 0;

		if (q.size() == 0) {
			System.out.println(-1);
		} else {
			while (!q.isEmpty()) {
				count++;
				int size = q.size();
				for (int j = 0; j < size; j++) {
					PointMNH xyz = q.poll();
					int x = xyz.tomatoM;
					int y = xyz.tomatoN;
					int h = xyz.tomatoH;
					for (int i = 0; i < 6; i++) {
						int nx = x + dx[i];
						int ny = y + dy[i];
						int nh = h + dh[i];

						if (0 <= nx && nx < tomatoM && 0 <= ny && ny < tomatoN && 0 <= nh && nh < tomatoH && tomatoBox[nx][ny][nh] == 0) {
							tomatoBox[nx][ny][nh] = 1;
							q.add(new PointMNH(nx, ny, nh));
						}
					}
				}
			}
			boolean ifZeroIs = false;
			for (int k = 0; k < tomatoH; k++) {
				if (ifZeroIs)
					break;
				for (int i = 0; i < tomatoN; i++) {
					if (ifZeroIs)
						break;
					for (int j = 0; j < tomatoM; j++) {
						if (tomatoBox[j][i][k] == 0) {
							System.out.println(-1);
							ifZeroIs = true;
							break;
						}
					}
				}
			}
			if (!ifZeroIs) {
				System.out.println(count-1);
			}
		}

	}
}
