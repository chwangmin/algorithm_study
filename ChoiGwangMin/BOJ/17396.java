import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 양방향 연결이므로 각 노드 별로 연결
 *
 */

public class Main {
	// 무향 그래프 이므로, node와 distance 만듬
	static class Node implements Comparable<Node> {
		int eNode;
		long distance;

		Node(int eNode, long currentDist) {
			this.eNode = eNode;
			this.distance = currentDist;
		}

		@Override
		public int compareTo(Node o) {
			return (this.distance > o.distance) ? 1 : -1; // 오른쪽이 더 크면 1
		}
	}

	static ArrayList<ArrayList<Node>> list = new ArrayList<ArrayList<Node>>();
	static long[] dist;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // br 로 입력을 받는다.
		StringTokenizer st = new StringTokenizer(br.readLine()); // 토크나이저로 입력 값 나누기

		int nodeNum = Integer.parseInt(st.nextToken());
		int edge = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int[] nodes = new int[nodeNum];

		// node를 사용할 것인가 안할 것인가 체크, 1이면 사용 안함.
		for (int i = 0; i < nodeNum; i++) {
			nodes[i] = Integer.parseInt(st.nextToken());
		}

		// 노드 연결 저장하기 위해 반복해서 저장
		for (int i = 0; i < nodeNum; i++) {
			list.add(new ArrayList<Node>());
		}

		// 노드들 연결 정보 이중 ArrayList에 넣기. 10만 * 30만 = 300억 = 30,000,000,000 즉 30000MB이기에
		// 2차원 불가능.
		for (int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());

			if ((start != nodeNum-1 && end != nodeNum-1) && (nodes[start] == 1 || nodes[end] == 1)) {
				continue;
			}

			list.get(start).add(new Node(end, distance)); // 시작 노드 -> 끝 노드 연결
			list.get(end).add(new Node(start, distance)); // 끝 노드 -> 시작 노드 연결
		}

		// 이제 다익스트라 알고리즘 시작
		dijkstra(nodeNum);
		
		if (dist[nodeNum-1] == Long.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(dist[nodeNum-1]);
	}

	// 다익스트라 알고리즘 메소드
	private static void dijkstra(int nodeNum) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(0, 0));
		
		boolean[] visited = new boolean[nodeNum];
		dist = new long[nodeNum];

		for (int i = 0; i < nodeNum; i++) {
			dist[i] = Long.MAX_VALUE;
		}
		dist[0] = 0;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int eNode = node.eNode;

			if (visited[eNode]) {
				continue;
			}
			visited[eNode] = true;

			for (int i = 0; i < list.get(eNode).size(); i++) {
				node = list.get(eNode).get(i);
				int currentNode = node.eNode;
				long currentDist = dist[eNode] + node.distance;
				if (dist[currentNode] > currentDist) {
					dist[currentNode] = currentDist;
				}
				q.add(new Node(currentNode,currentDist));
			}
		}
	}
}
