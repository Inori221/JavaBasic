package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDB {

	public static void main(String[] args) {

		// データベース接続情報
		String url = "jdbc:mysql://localhost:3306/product_management";
		String user = "root";
		String password = "Araki221";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// JDBCドライバの読み込み
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベース接続
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB接続成功");

			// SQL文作成
			String sql = "SELECT * FROM products";

			// SQL実行オブジェクト生成
			stmt = conn.createStatement();

			// SQL実行
			rs = stmt.executeQuery(sql);

			System.out.println("-- productsテーブルの全ての商品情報を表示 --");

			// 結果セットの処理
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				int categoryId = rs.getInt("category_id");

				System.out.println("id: " + id);
				System.out.println("name: " + name);
				System.out.println("price: " + price);
				System.out.println("stock: " + stock);
				System.out.println("category_id: " + categoryId);
				System.out.println();
			}

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("DB接続失敗");
			e.printStackTrace();
		} finally {
			// リソースをクローズ
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("リソース解放エラー: " + e.getMessage());
			}
		}
	}
}
