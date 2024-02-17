package study_algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * dp 문제 dp 를 먼저 일 수 만큼 만든다. 입력이 들어올 때마다 dp 를 탐색하는데, 탐색을 시작하는 지점을 선택한다. 마지막 지점에서
 * 돈을 받기에 현재 시작 날짜 + end 부터 받을 돈을 입력한다.
 * 1,500,000이 주어진다.
 */

public class BOJ15486_퇴사2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int day = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			if (dp[i+1] < dp[i]) {
				dp[i+1] = dp[i];
			}
			
			if (day+i > N) {
				continue;
			}
			int tmp = dp[i] + value;
			
			if (tmp <= dp[day+i]) {
				continue;
			}
			dp[day+i] = tmp;
		}
		System.out.println(dp[N]);
	}
}
