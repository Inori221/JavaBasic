package method.q07;

public class SumNumbers2 {
	// メソッド定義
	public static int calculateSum(int num1, double num2) {
		// 2つの引数を加算し、整数にして返す
		return (int) (num1 + num2);
	}

	// 実行用メインメソッド
	public static void main(String[] args) {
		int intValue = 5;
		double doubleValue = 3.3;

		int result = calculateSum(intValue, doubleValue);

		System.out.println("第一引数（整数）：" + intValue);
		System.out.println("第二引数（実数）：" + doubleValue);
		System.out.println("加算結果：" + result);
	}
}