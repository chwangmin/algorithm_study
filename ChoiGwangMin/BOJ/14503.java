import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	final static int DIRECTS[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	final static int DIRECT_NUM = 4;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int mapSizeX, mapSizeY, startX, startY, direct;
	static int[][] roomMap;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		mapSizeX = Integer.parseInt(st.nextToken());
		mapSizeY = Integer.parseInt(st.nextToken());
		roomMap = new int[mapSizeX][mapSizeY];

		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		direct = Integer.parseInt(st.nextToken());

		setMap();

		while (true) {
			cleenRobot();

			if (!isGoRobot()) { // 주변에 청소할 게 없음
				startX -= DIRECTS[direct][0];
				startY -= DIRECTS[direct][1];
				if (roomMap[startX][startY] == 1) {
					System.out.println(answer);
					return;
				}
				continue;
			}

			direct = (direct+3) % 4;

			int checkX = startX + DIRECTS[direct][0];
			int checkY = startY + DIRECTS[direct][1];

			if (roomMap[checkX][checkY] == 0) {
				startX = checkX;
				startY = checkY;
			}

		}
	}

	private static void setMap() throws IOException {
		for (int i = 0; i < mapSizeX; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mapSizeY; j++) {
				roomMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void cleenRobot() {
		if (roomMap[startX][startY] == 0) {
			roomMap[startX][startY] = -1;
			answer += 1;
		}
	}

	private static boolean isGoRobot() {
		for (int i = 0; i < DIRECT_NUM; i++) {
			int checkX = startX + DIRECTS[i][0];
			int checkY = startY + DIRECTS[i][1];

			if (roomMap[checkX][checkY] == 0) {
				return true;
			}
		}
		return false;
	}
}
