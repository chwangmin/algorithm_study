import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 괄호_추가하기_16637 {

    static int length;
    static int[] nums;
    static char[] ops;

    static int answer = -Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        length = Integer.parseInt(br.readLine());

        nums = new int[length / 2 + 1];
        ops = new char[length / 2];

        String in = br.readLine();

        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                nums[i / 2] = in.charAt(i) - '0';
                continue;
            }
            ops[i / 2] = in.charAt(i);
        }
        dfs(1,nums[0]);

        System.out.println(answer);
    }

    private static int calcul(int num1, int num2, char op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
        }
        return 0;
    }

    private static void dfs(int cnt, int result) {
        if (cnt == length / 2 + 1) {
            answer = Math.max(answer, result);
            return;
        }

        dfs(cnt+1,calcul(result, nums[cnt], ops[cnt-1]));

        if (cnt < length / 2) {
            dfs(cnt + 2, calcul(result, calcul(nums[cnt], nums[cnt + 1], ops[cnt]), ops[cnt-1]));
        }
    }
}
