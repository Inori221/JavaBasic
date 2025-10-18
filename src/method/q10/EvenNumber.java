package method.q10;

import java.util.Arrays;

public class EvenNumber {
	// メソッド定義
	public static int getEvenNumbers(int[] numbers) {
		int count = 0;
		for (int num : numbers) {
			if (num % 2 == 0) {
				count++;
			}
		}
		return count;
	}

	// 実行用メインメソッド
	public static void main(String[] args) {
		int[] numbers = { 3, 2, 5, 6, 7, 25, 10, 51, 88, 98 };
		int evenCount = getEvenNumbers(numbers);

		System.out.println(Arrays.toString(numbers) + "には、偶数が" + evenCount + "個あります。");
	}
}