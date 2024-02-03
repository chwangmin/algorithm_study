import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	final static int LOTTO_NUM = 6;
	static int numsLength;
	static int[] nums;
	static int[] answer;
	static StringBuilder sb = new StringBuilder();

	static void inputData(StringTokenizer st) {
		int numsIndex = 0;
		while (st.hasMoreTokens()) {
			nums[numsIndex++] = Integer.parseInt(st.nextToken());
		}
	}

	static void lottoComb(int currentIdx, int start) {
		if (currentIdx == LOTTO_NUM) {
			for (int i = 0; i < LOTTO_NUM; i++) {
				sb.append(answer[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = start; i < numsLength; i++) {
			answer[currentIdx] = nums[i];
			lottoComb(currentIdx + 1, i + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			numsLength = Integer.parseInt(st.nextToken());

			if (numsLength == 0) {
				break;
			}

			nums = new int[numsLength];
			answer = new int[numsLength];

			inputData(st);

			lottoComb(0, 0);
			
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
