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
			System.out.println("0. 終了");
			System.out.print("番号を選んでください → ");

			int select = sc.nextInt();
			sc.nextLine();

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

			case 0:
				System.out.println("終了します。");
				return;

			default:
				System.out.println("正しい番号を入力してください。");
			}
		}
	}

	// ---------------------------
	// 商品追加
	// ---------------------------
	private static void insertProduct(Scanner sc) {
		System.out.println("--商品の登録--");

		try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

			System.out.print("商品名を入力してください：\n");
			String name = sc.nextLine();

			System.out.print("価格を入力してください：\n");
			int price = sc.nextInt();

			System.out.print("在庫数を入力してください：\n");
			int stock = sc.nextInt();

			System.out.print("カテゴリーIDを入力してください：\n");
			int categoryId = sc.nextInt();
			sc.nextLine();

			String sql = "INSERT INTO products (name, price, stock, category_id) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setInt(3, stock);
			ps.setInt(4, categoryId);

			int result = ps.executeUpdate();

			System.out.println("\n登録成功件数： " + result + "件");
			System.out.println("登録内容：");
			System.out.println("商品名：" + name + "、 価格：" + price + "、 在庫数：" + stock + "、 カテゴリーID：" + categoryId);

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
			int id = sc.nextInt();

			System.out.print("価格を入力してください：\n");
			int price = sc.nextInt();

			System.out.print("在庫数を入力してください：\n");
			int stock = sc.nextInt();
			sc.nextLine();

			String sql = "UPDATE products SET price = ?, stock = ? WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, price);
			ps.setInt(2, stock);
			ps.setInt(3, id);

			int result = ps.executeUpdate();

			System.out.println("\n更新成功件数： " + result + "件");

			if (result > 0) {
				System.out.println("更新内容：");
				System.out.println("商品ID: " + id + "、 価格：" + price + "、 在庫数：" + stock);
			} else {
				System.out.println("更新失敗");
			}

		} catch (Exception e) {
			System.out.println("更新に失敗しました。");
			e.printStackTrace();
		}
	}

	// ---------------------------
	// 商品削除（カテゴリーID指定）
	// ---------------------------
	private static void deleteProduct(Scanner sc) {
		System.out.println("--商品の削除（カテゴリーID指定）--");

		try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

			System.out.print("削除するカテゴリーIDを入力してください：\n");
			int categoryId = sc.nextInt();
			sc.nextLine();

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
}
