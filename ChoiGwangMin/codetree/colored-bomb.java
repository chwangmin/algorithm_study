package study_algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 주변을 찾아야 하기 때문에 bfs로 한번 풀어볼까 함.
 * 
 * 필요한 메소드 정리 bfs로 터질 것들 찾는 메소드 터질 것들 중 터질 것 고르는 메소드 회전하는 메소드 중력 작용 메소드
 *
 * 가장 길게 붙은 폭탄 묶음을 찾기 위해서는 저장을 해야 한다. n의 최대 크기는 20이다. 즉, 20*20 = 400개까지 가능하다.
 * 여기서 같은 크기일때 우선순위는 빨간 폭탄이 가장적고, 행이 가장큰 칸, 같다면 열이 작은 폭탄 묶음이다.
 * 
 */

public class Bomb {

	private static class coordinate {
		int c;
		int r;

		coordinate(int c, int r) {
			this.c = c;
			this.r = r;
		}
	}

	// 다른 메소드에서도 사용해야 하기에
	static int size, bombs;
	static boolean[][] visited;
	static boolean flag; // 만약 다 돌았는데 부서질게 없으면 끝내야 대서.
	static int[][] map;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		size = Integer.parseInt(st.nextToken());
		bombs = Integer.parseInt(st.nextToken());

		map = new int[size][size];
		visited = new boolean[size][size];

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				int color = Integer.parseInt(st.nextToken());
				map[i][j] = color;
			}
		}

		while (true) {
			flag = false;
			// 반복 할때마다 가능하지 않는 가장 큰 값으로 초기화함.
			old = Arrays.copyOf(maxList, maxList.length);
			// 여기서 한바퀴를 돌음.
			// 여기서 우선순위가 가장 행이 가장 크고,
			for (int i = size - 1; i >= 0; i--) {
				// 행이 같다면, 열이 가장 작은게 우선
				for (int j = 0; j < size; j++) {
					if (visited[i][j]) {
						continue;
					}
					if (map[i][j] == 0 || map[i][j] == -1 || map[i][j] == -2) {
						continue;
					}
					for (int k = 0; k < size; k++) {
						for (int r = 0; r < size; r++) {
							if (map[k][r] == 0) {
								visited[k][r] = false;
							}
						}
					}
					if (bfs(i, j) > 1) {
						flag = true;

						// 길이 비교 더 크면 걍 넣기
						if (old[0] < current[0]) {
							old = Arrays.copyOf(current, 4);
							continue;
						}
						// 길이 비교 같으면 빨간색 갯수 비교
						if (old[0] == current[0]) { // if 시작
							if (old[1] > current[1]) {
								old = Arrays.copyOf(current, 4);
								continue;
							}
							if (old[1] == current[1]) {
								if (old[2] < current[2]) {
									old = Arrays.copyOf(current, 4);
									continue;
								}
								if (old[2] == current[2]) {
									if (old[3] > current[3]) {
										old = Arrays.copyOf(current, 4);
										continue;
									}
								}
							}
						} // end 끝
					}
				}
			}
			// 만약 터질게 없으면 끝내기.
			if (!flag) {
				break;
			}

			// 터질게 있다면 터질 리스트 즉 old 리스트에 있는거 터뜨리기.
			selectBomb();

			gravity();

			rotate();

			gravity();

			for (int i = 0; i < size; i++) {
				Arrays.fill(visited[i], false);
			}
		}
		System.out.println(answer);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<coordinate> q = new LinkedList<>();
	// 우선순위 대로 - 갯수, 빨간색, 행, 열
	static final int[] maxList = { 0, 401, 0, 21 };
	static int[] old = new int[4];
	static int[] current = new int[4];

	// 가장 큰 값을 찾아야한다.
	// 일단 여기서 가장 큰 값을 return 하고, 그 값들을 리스트에 넣어둔다?
	/**
	 * 
	 * 여기서 리스트를 [1,2,3,4] 4차원으로 만들어야 한다? 아니 배열 4개로 해결한다. 옛날 배열과, 현재 배열을 비교하면됨. 행, 열
	 * 저장, 길이 저장, 빨간색 갯수 저장
	 * 
	 */
	private static int bfs(int c, int r) {

		int startC = c;
		int startR = r;
		int redNum = 0;

		int color = map[c][r];
		int count = 1;

		q.add(new coordinate(c, r));

		visited[c][r] = true;

		while (!q.isEmpty()) {
			coordinate cd = q.poll();
			c = cd.c;
			r = cd.r;

			for (int i = 0; i < 4; i++) {
				int nx = c + dx[i];
				int ny = r + dy[i];

				if (nx < 0 || nx >= size || ny < 0 || ny >= size) {
					continue;
				}
				if (visited[nx][ny]) {
					continue;
				}
				if (map[nx][ny] == color) {
					q.add(new coordinate(nx, ny));
					visited[nx][ny] = true;
					count++;
				}
				if (map[nx][ny] == 0) {
					q.add(new coordinate(nx, ny));
					visited[nx][ny] = true;
					redNum++;
					count++;
				}
			}
		}

		current[0] = count;
		current[1] = redNum;
		current[2] = startC;
		current[3] = startR;
		return count;
	}

	private static void selectBomb() {
		int c = old[2];
		int r = old[3];

		answer += old[0] * old[0];

		int color = map[c][r];

		map[c][r] = -2;

		q.add(new coordinate(c, r));

		while (!q.isEmpty()) {
			coordinate cd = q.poll();
			c = cd.c;
			r = cd.r;

			for (int i = 0; i < 4; i++) {
				int nx = c + dx[i];
				int ny = r + dy[i];

				if (nx < 0 || nx >= size || ny < 0 || ny >= size) {
					continue;
				}

				if (map[nx][ny] == color) {
					q.add(new coordinate(nx, ny));
					map[nx][ny] = -2; // 비움
				}
				if (map[nx][ny] == 0) {
					q.add(new coordinate(nx, ny));
					map[nx][ny] = -2; // 비움
				}
			}
		}
	}

	private static void gravity() {
		for (int i = 0; i < size; i++) {
			for (int j = size - 1; j >= 1; j--) {
				if (map[j][i] == -2) {
					for (int k = j; k >= 0; k--) {
						if (map[k][i] == -2) {
							continue;
						}
						if (map[k][i] == -1) {
							break;
						}
						map[j][i] = map[k][i];
						map[k][i] = -2;
						break;
					}
				}
			}
		}
	}

	private static void rotate() {
		int[][] tmp = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				tmp[size - 1 - j][i] = map[i][j];
			}
		}
		map = tmp;
	}
}
