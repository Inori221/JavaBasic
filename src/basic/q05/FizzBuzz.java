package basic.q05;

public class FizzBuzz {

	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {

			if (i % 3 == 0 && i % 5 == 0) {
				System.out.println("FizzBuzz"); // 3と5の両方の倍数
			} else if (i % 3 == 0) {
				System.out.println("Fizz"); // 3の倍数
			} else if (i % 5 == 0) {
				System.out.println("Buzz"); // 5の倍数
			} else {
				System.out.println(i); // それ以外は数字を表示
			}

		}
	}
}
