import java.awt.Point;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int[][] rainMap = new int[n][n];

		Queue<Point> q = new ArrayDeque<>();

		int maxH = 0;

		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int tmpInt = sc.nextInt();
				rainMap[i][j] = tmpInt;
				if (maxH < tmpInt) {
					maxH = tmpInt;
				}
			}
		}

		int answer = 0;
		for (int k = 1; k < maxH; k++) {
			int count = 0;
			boolean[][] visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j] && rainMap[i][j] > k) {
						count++;
						q.add(new Point(i,j));
                        visited[i][j] = true;
						while (!q.isEmpty()) {
							Point p = q.poll();
							int x = p.x;
							int y = p.y;
							visited[x][y] = true;

							for (int w = 0; w < 4; w++) {
								int nx = x + dx[w];
								int ny = y + dy[w];
								if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny] && rainMap[nx][ny] > k) {
									q.add(new Point(nx, ny));
								}
							}
						}

					}
				}
			}
			if (answer < count) {
				answer = count;
			}
		}
		System.out.println(answer);
	}
}
