import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int alphaSize = sc.nextInt();

		char[] alphaString = new char[alphaSize];
		char[] answerString = new char[alphaSize];

		for (int i = 0; i < alphaSize; i++) {
			char alpha = sc.next().charAt(0);
			alphaString[i] = alpha;
		}

		int left = 0;
		int right = alphaSize - 1;

		int idx = 0;
		while (left <= right) {
			if (left == right) {
				answerString[idx++] = alphaString[left++];
				break;
			}
			if (alphaString[left] < alphaString[right]) {
				answerString[idx++] = alphaString[left++];
			} else if (alphaString[left] > alphaString[right]) {
				answerString[idx++] = alphaString[right--];
			}
			else {
				int tmpLeft = left; int tmpRight = right;
				boolean check = true;
				while(alphaString[tmpLeft] == alphaString[tmpRight]) {
					if (tmpRight > 0) tmpRight--;
					if (tmpLeft < alphaSize-1) tmpLeft++;
					
					if (alphaString[tmpLeft] < alphaString[tmpRight]) check = true;
					else if (alphaString[tmpLeft] > alphaString[tmpRight]) check = false;
				}
				if (check) answerString[idx++] = alphaString[left++];
				else answerString[idx++] = alphaString[right--];
			}
		}
		for (int i = 1; i < alphaSize + 1; i++) {
			System.out.print(answerString[i - 1]);
			if (i % 80 == 0) {
				System.out.println();
			}
		}
	}
}
