package method.q08;

public class SumLoop {
	// メソッド定義
	public static int sumLoop(int min, int max) {
		int sum = 0;
		for (int i = min; i <= max; i++) {
			sum += i;
		}
		return sum;
	}

	// 実行用メインメソッド
	public static void main(String[] args) {
		int min = 1;
		int max = 100;
		int result = sumLoop(min, max);

		System.out.println("最小値：" + min);
		System.out.println("最大値：" + max);
		System.out.println("加算結果：" + result);
	}
}