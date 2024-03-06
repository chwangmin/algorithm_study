import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N 은 10만 -> o(n)으로 풀어야 함.
 * S 은 1억까지
 * 들어가는 수는 1만
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] list = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        int answer = (int) 1e9;

        int sum = 0;
        // 지렁이 처럼 M 보다 작으면 오른쪽으로 가면서 누적 합구하기
        // M 보다 작으면 왼쪽 마지막 부분을 빼면 됩니다.
        int left = 0;
        for (int i = 0; i < N; i++) {
            sum += list[i];
            while (sum >= M) {
                answer = Math.min(answer, i - left + 1);
                if (left >= i){
                    break;
                }
                sum -= list[left++];
            }
        }
        if (answer == (int)1e9){
            answer = 0;
        }
        System.out.println(answer);
    }
}
