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
				while(tmpLeft <= tmpRight) {
					if (alphaString[tmpLeft] < alphaString[tmpRight]) {
						answerString[idx++] = alphaString[left++];
						break;
					} else if (alphaString[tmpLeft] > alphaString[tmpRight]) {
						answerString[idx++] = alphaString[right--];
						break;
					} else {
						tmpLeft++;
						tmpRight--;
					}
				}
			}
		}
		int checkEighty = 0;
		for (int i = 0; i < alphaSize; i++) {
			System.out.print(answerString[i]);
			checkEighty++;
			if (checkEighty % 80 == 0) {
				System.out.println();
			}
		}
	}
}
