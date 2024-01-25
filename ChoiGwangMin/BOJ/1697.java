import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();

	static int subinX, broX;
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;

	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		subinX = Integer.parseInt(st.nextToken());
		broX = Integer.parseInt(st.nextToken());

		visited = new boolean[100_001];

		System.out.println(bfs(subinX));
	}

	public static int bfs(int subinX) {
		int count = 0;

		if (subinX == broX) {
			return count;
		}

		q.add(subinX);
		visited[subinX] = true;

		while (true) {
			count++;
			int size = q.size();

			for (int i = 0; i < size; i++) {
				int tmpSubinX = q.remove();
				if (tmpSubinX + 1 == broX || tmpSubinX - 1 == broX || tmpSubinX * 2 == broX) {
					return count;
				}
				if (tmpSubinX - 1 > 0 && !visited[tmpSubinX - 1]) {
					q.add(tmpSubinX - 1);
					visited[tmpSubinX - 1] = true;
				}
				if (tmpSubinX + 1 < 100_000 && !visited[tmpSubinX + 1]) {
					q.add(tmpSubinX + 1);
					visited[tmpSubinX + 1] = true;
				}
				if (tmpSubinX * 2 < 100_000 && !visited[tmpSubinX * 2]) {
					q.add(tmpSubinX * 2);
					visited[tmpSubinX * 2] = true;
				}
			}
		}
	}
}
