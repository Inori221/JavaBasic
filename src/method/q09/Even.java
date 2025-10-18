package method.q09;

public class Even {
	// メソッド定義
	public static boolean checkEven(int num) {
		// 偶数ならtrue、奇数ならfalseを返す
		return num % 2 == 0;
	}

	// 実行用メインメソッド
	public static void main(String[] args) {
		int num1 = 10;
		int num2 = 5;

		// 1つ目の例
		if (checkEven(num1)) {
			System.out.println(num1 + "は偶数です。");
		} else {
			System.out.println(num1 + "は奇数です。");
		}

		System.out.println();

		// 2つ目の例
		if (checkEven(num2)) {
			System.out.println(num2 + "は偶数です。");
		} else {
			System.out.println(num2 + "は奇数です。");
		}
	}
}