import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 완탐 사용 x
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;

    static int N, M, craneIdx, boxIdx, answer = 1;
    static Integer[] craneWeights, boxWeights;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        craneWeights = new Integer[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            craneWeights[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(craneWeights, Collections.reverseOrder());

        M = Integer.parseInt(br.readLine());
        boxWeights = new Integer[M];
        st = new StringTokenizer(br.readLine());

        ArrayList<Integer> boxWeights = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            boxWeights.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(boxWeights, Collections.reverseOrder());

        if (craneWeights[0] < boxWeights.get(0)) {
            System.out.println(-1);
            return;
        }

        boxIdx = 0;
        craneIdx = 0;
        while (!boxWeights.isEmpty()) {
            if (craneIdx == N || boxIdx == M){
                craneIdx = 0;
                boxIdx = 0;
                answer++;
            }
            if (craneWeights[craneIdx] >= boxWeights.get(boxIdx)){
                craneIdx++;
                M--;
                boxWeights.remove(boxIdx);
            }
            else {
                boxIdx++;
            }
        }

        System.out.println(answer);
    }
}
