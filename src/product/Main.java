package product;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ProductsManager manager = new ProductsManager();

		//メニューの表示
		while (true) {
			System.out.println("\n---メニュー---");
			System.out.println("1:商品追加");
			System.out.println("2:商品削除");
			System.out.println("3:商品情報取得");
			System.out.println("4:商品検索");
			System.out.println("5:商品全て表示");
			System.out.println("0:終了");
			System.out.print("メニューから操作を選択してください: ");
			int choice = sc.nextInt();
			sc.nextLine(); // 改行を消す

			try { //例外処理
				switch (choice) {
				case 1:
					System.out.print("商品IDを入力してください: "); //ID入力
					int id = sc.nextInt();
					sc.nextLine();

					System.out.print("商品名を入力してください: "); //商品名入力
					String name = sc.nextLine();
					if (name.isEmpty()) {
						throw new Exception("無効な入力です。商品名を正しく入力してください。");
					}

					System.out.print("価格を入力してください: "); //価格入力
					int price = sc.nextInt();
					if (price < 0) {
						throw new Exception("無効な入力です。価格は0以上を入力してください。");
					}

					System.out.print("在庫数を入力してください: "); //在庫数入力
					int stock = sc.nextInt();
					if (stock < 0) {
						throw new Exception("無効な入力です。在庫数は0以上を入力してください。");
					}

					Product p = new Product(id, name, price, stock);
					manager.addProduct(p);
					System.out.println("商品を追加しました: " + name);
					break;

				case 2:
					System.out.print("削除する商品IDを入力してください: ");
					int deleteId = sc.nextInt();
					manager.removeProduct(deleteId);
					System.out.println("削除しました。");
					break;

				case 3:
					System.out.print("商品名を入力してください: ");
					String searchName = sc.nextLine();
					Product found = manager.getProductByName(searchName);
					if (found != null) {
						System.out.println("商品情報: " + found);
					} else {
						System.out.println("商品が見つかりません。");
					}
					break;

				case 4:
					System.out.print("検索キーワードを入力してください: ");
					String keyword = sc.nextLine();
					var results = manager.search(keyword);
					if (results.isEmpty()) {
						System.out.println("該当する商品はありません。");
					} else {
						for (Product pr : results) {
							System.out.println(pr);
						}
					}
					break;

				case 5:
					for (Product pr : manager.getProducts()) {
						System.out.println(pr);
					}
					break;

				case 0:
					System.out.println("終了します。");
					sc.close();
					return;

				default:
					System.out.println("無効な選択です。");
					break;
				}
			} catch (Exception e) {
				System.out.println("無効な入力です。入力された内容:\n" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
