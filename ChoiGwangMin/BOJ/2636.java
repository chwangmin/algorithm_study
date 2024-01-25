package algo_baekjoon;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b_2636 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int cheezeSizeY = sc.nextInt();
		int cheezeSizeX = sc.nextInt();
		
		
		int[][] cheezeMap = new int[cheezeSizeY][cheezeSizeX];
		for (int i = 0 ; i < cheezeSizeY; i++) {
			for (int j = 0; j < cheezeSizeX; j++) {
				cheezeMap[i][j] = sc.nextInt();
			}
		}
		
		// 첫번째로 공기와 인접한 치즈 탐색
		Queue<Point> q = new LinkedList<Point>();
		Queue<Point> q2 = new LinkedList<Point>();
		
		q.add(new Point(0,0));
		
		boolean[][] visited = new boolean[cheezeSizeY][cheezeSizeX];
		int count = 0;
		
		int[] dx = new int[] {1,-1,0,0};
		int[] dy = new int[] {0,0,1,-1};
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int x = p.x;
			int y = p.y;
			visited[y][x] = true;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= cheezeSizeX || ny >= cheezeSizeY || visited[ny][nx]) continue;
				
				q.add(new Point(nx,ny));
				
				if (cheezeMap[ny][nx] == 1) {
					q2.add(new Point(nx,ny));
					visited[y][x] = true;
					continue;
				}
			}
		}
		
		boolean[][] visited2 = new boolean[cheezeSizeY][cheezeSizeX];
		
		while(!q2.isEmpty()) {
			count++;
			for (int i = 0; i < q2.size(); i++) {
				Point p = q2.poll();
				int x = p.x;
				int y = p.y;
				visited2[y][x] = true;
				for (int j = 0 ; j < 4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					
					if (nx < 0 || ny < 0 || nx >= cheezeSizeX || ny >= cheezeSizeY || visited2[ny][nx]) continue;
					
					if (cheezeMap[ny][nx] == 1) {
						q2.add(new Point(nx,ny));
					}
				}
			}
		}
		System.out.println(count);
	}

}
