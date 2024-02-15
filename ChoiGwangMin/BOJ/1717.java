import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 유니온 파인드라는 알고리즘을 사용 대표적 그래프 알고리즘으로 합집합 찾기 먼저 1번 인덱스는 1, 2번 인덱스는 2 값 설정 자신의 부모를
 * 연결할때 가장 작은 값으로 설정한다.
 * 
 */

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[] parentList;
	static int[] numList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int maxNum = Integer.parseInt(st.nextToken());
		int testNum = Integer.parseInt(st.nextToken());

		numList = new int[maxNum + 1];
		parentList = new int[maxNum + 1];

		for (int i = 1; i < maxNum + 1; i++) {
			parentList[i] = i;
		}

		for (int testCase = 0; testCase < testNum; testCase++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			int pLeft = parentList[left];
			int pRight = parentList[right];
			if (command == 0) {
				while (pLeft != pRight) {
					if (pLeft < pRight) {
						parentList[right] = pLeft;
					} else {
						parentList[left] = pRight;
					}
					pLeft = parentList[left];
					pRight = parentList[right];
				}
				continue;
			}
			
			while (pLeft != left) {
				 pLeft = parentList[pLeft];
				 left = pLeft;
			}
			
			while (pRight != right) {
				 pRight = parentList[pRight];
				 right = pRight;
			}

			if (parentList[right] == parentList[left]) {
				sb.append("YES\n");
				continue;
			}
			sb.append("NO\n");
		}
		System.out.println(sb);
	}
}
