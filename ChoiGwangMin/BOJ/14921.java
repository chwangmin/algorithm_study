import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int solutionNum =  sc.nextInt();
		
		int[] solutionList = new int[solutionNum];
		
		for (int i = 0; i < solutionNum; i ++) {
			solutionList[i] = sc.nextInt();
		}
		
		int answer = Integer.MAX_VALUE;
		
		int left = 0;
		int right = solutionNum - 1;
		
		while (left < right) {
			int tmpSolutionSum = solutionList[left] + solutionList[right];
			if (Math.abs(tmpSolutionSum) < Math.abs(answer)) {
				answer = tmpSolutionSum;
			}
			if (tmpSolutionSum > 0) {
				right--;
			}
			else if (tmpSolutionSum < 0) {
				left++;
			}
			else {
				break;
			}
		}
		System.out.println(answer);
	}
}
