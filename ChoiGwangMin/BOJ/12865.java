package study_algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 전형적인 dp 문제 dp 배열을 만들고 각각의 배낭이 들어오면 dp 에저장 dp 에 저장할때 자신의 무게 인덱스 부터 시작하며, 자신의
 * 무게보다 큰 무게가 나오면 넘기기
 * 
 */

public class BOJ12865_평범한배낭 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] oldDp = new int[K + 1];
		int[] dp = new int[K + 1];

		// 입력을 받으며 dp 에 넣기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			oldDp  = Arrays.copyOf(dp, K+1);
			
			for (int j = weight; j <= K; j++) {
				int tmp = oldDp[j - weight] + value;
				if (dp[j] < tmp) {
					dp[j] = tmp;
				}
			}
		}
		System.out.println(dp[K]);
	}
}
