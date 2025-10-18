package method.q05;

public class Triangle {
	// メソッド定義
	public static int getTriangleArea(int base, int height) {
		// 面積 = (底辺 × 高さ) ÷ 2
		return (base * height) / 2;
	}

	// 実行用メインメソッド
	public static void main(String[] args) {
		int base = 8;
		int height = 5;
		int area = getTriangleArea(base, height);

		System.out.println("底辺：" + base);
		System.out.println("高さ：" + height);
		System.out.println("三角形の面積：" + area);
	}
}