package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ProductDB {

	static final String URL = "jdbc:mysql://localhost:3306/product_management"
			+ "?useSSL=false"
			+ "&allowPublicKeyRetrieval=true"
			+ "&serverTimezone=UTC";

	static final String USER = "root";
	static final String PASS = "Araki221";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("===== ProductDB メニュー =====");
			System.out.println("1. 商品追加");
			System.out.println("2. 商品更新");
			System.out.println("3. 商品削除（カテゴリーID）");
			System.out.println("4. 商品一覧表示");
			System.out.println("5. 複数商品の在庫更新（トランザクション）");//トランザクション
			System.out.println("0. 終了");
			System.out.print("番号を選んでください → ");

			int select = inputInt(sc);

			switch (select) {
			case 1:
				insertProduct(sc);
				break;
			case 2:
				updateProduct(sc);
				break;
			case 3:
				deleteProduct(sc);
				break;
			case 4:
				showProducts();
				break;
			case 5:
			    updatesProducts(sc);
			    break;
			case 0:
				System.out.println("終了します。");
				return;
			default:
				System.out.println("正しい番号を入力してください。");
			}
		}
	}

	// ---------------------------
	// 複数商品の更新（ID・価格・在庫）
	// ---------------------------
	private static void updatesProducts(Scanner sc) {

	    System.out.println("--複数商品の価格と在庫を更新（トランザクション）--");

	    Connection con = null;

	    try {
	        con = DriverManager.getConnection(URL, USER, PASS);
	        con.setAutoCommit(false);  // トランザクション開始

	        String sql = "UPDATE products SET price = ?, stock = ? WHERE id = ?";
	        PreparedStatement ps = con.prepareStatement(sql);

	        System.out.print("更新する商品数を入力してください：");
	        int count = inputInt(sc);

	        StringBuilder log = new StringBuilder();
	        int successCount = 0;

	        for (int i = 1; i <= count; i++) {
	            System.out.println("\n-- 商品の更新 " + i + " --");

	            System.out.print("商品IDを入力してください：");
	            int id = inputInt(sc);

	            System.out.print("新しい価格を入力してください：");
	            int price = inputInt(sc);

	            System.out.print("新しい在庫数を入力してください：");
	            int stock = inputInt(sc);

	            ps.setInt(1, price);
	            ps.setInt(2, stock);
	            ps.setInt(3, id);

	            int result = ps.executeUpdate();

	            if (result == 0) {
	                throw new Exception("商品ID " + id + " は存在しません → 更新失敗");
	            }

	            successCount++;

	            log.append("商品ID: ").append(id)
	               .append("、 価格: ").append(price)
	               .append("、 在庫: ").append(stock)
	               .append("\n");
	        }

	        con.commit();  // 成功 → コミット
	        System.out.println("\n=== コミット成功 ===");
	        System.out.println("更新成功件数： " + successCount + "件\n");

	        System.out.println("--- 更新内容一覧 ---");
	        System.out.println(log.toString());

	    } catch (Exception e) {
	        System.out.println("\nエラー発生 → 全てロールバックします。");
	        e.printStackTrace();
	        try {
	            if (con != null) {
	                con.rollback();  // ← 正しい rollback
	            }
	        } catch (Exception e2) {
	            System.out.println("ロールバックに失敗しました。");
	        }
	    } finally {
	        try {
	            if (con != null) con.close();
	        } catch (Exception e) {}
	    }
	}




	// 商品追加
	private static void insertProduct(Scanner sc) {
		System.out.println("--商品の登録--");

		try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

			System.out.print("商品名を入力してください：\n");
			String name = sc.nextLine();

			System.out.print("価格を入力してください：\n");
			int price = inputInt(sc);

			System.out.print("在庫数を入力してください：\n");
			int stock = inputInt(sc);

			System.out.print("カテゴリーIDを入力してください：\n");
			int categoryId = inputInt(sc);

			String sql = "INSERT INTO products (name, price, stock, category_id) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setInt(3, stock);
			ps.setInt(4, categoryId);

			int result = ps.executeUpdate();

			System.out.println("\n登録成功件数： " + result + "件");
			System.out.println("登録内容：");
			System.out.println(
					"商品名：" + name +
							"、 価格：" + price +
							"、 在庫数：" + stock +
							"、 カテゴリーID：" + categoryId);

		} catch (Exception e) {
			System.out.println("登録に失敗しました。");
			e.printStackTrace();
		}
	}

	// ---------------------------
	// 商品更新
	// ---------------------------
	private static void updateProduct(Scanner sc) {
		System.out.println("--商品の価格と在庫を更新--");

		try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

			System.out.print("商品IDを入力してください：\n");
			int id = inputInt(sc);

			System.out.print("新しい価格を入力してください：\n");
			int price = inputInt(sc);

			System.out.print("新しい在庫数を入力してください：\n");
			int stock = inputInt(sc);

			String sql = "UPDATE products SET price = ?, stock = ? WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, price);
			ps.setInt(2, stock);
			ps.setInt(3, id);

			int result = ps.executeUpdate();

			System.out.println("\n更新成功件数： " + result + "件");

			if (result > 0) {
				System.out.println("更新内容：");
				System.out.println(
						"商品ID: " + id +
								"、 新価格：" + price +
								"、 新在庫数：" + stock);
			} else {
				System.out.println("指定した商品IDが存在しません。");
			}

		} catch (Exception e) {
			System.out.println("更新に失敗しました。");
			e.printStackTrace();
		}
	}

	// 商品削除（カテゴリーID指定）
	private static void deleteProduct(Scanner sc) {
		System.out.println("--商品の削除（カテゴリーID指定）--");

		try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

			System.out.print("削除するカテゴリーIDを入力してください：\n");
			int categoryId = inputInt(sc);

			String sql = "DELETE FROM products WHERE category_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoryId);

			int result = ps.executeUpdate();

			System.out.println("\n削除成功件数： " + result + "件");
			System.out.println("カテゴリーID " + categoryId + " の商品を削除しました。");

		} catch (Exception e) {
			System.out.println("削除に失敗しました。");
			e.printStackTrace();
		}
	}

	// ---------------------------
	// 商品一覧表示
	// ---------------------------
	private static void showProducts() {
		System.out.println("--productsテーブルの商品一覧--");

		try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

			String sql = "SELECT * FROM products";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(
						"\nID: " + rs.getInt("id") +
								"\nname: " + rs.getString("name") +
								"\nprice: " + rs.getInt("price") +
								"\nstock: " + rs.getInt("stock") +
								"\ncategory_id: " + rs.getInt("category_id"));
			}

		} catch (Exception e) {
			System.out.println("一覧取得に失敗しました。");
			e.printStackTrace();
		}
	}

	// 数値入力を安全に受け取る共通メソッド
	private static int inputInt(Scanner sc) {
		while (true) {
			String input = sc.nextLine();

			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.print("数字を入力してください → ");
			}
		}
	}
}
