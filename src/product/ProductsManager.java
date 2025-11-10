package product;

import java.util.ArrayList;
import java.util.List;

public class ProductsManager implements Searchable {
	private List<Product> productList = new ArrayList<>();

	//商品追加
	public void addProduct(Product product) {
		productList.add(product);
	}

	//商品削除
	public void removeProduct(int id) {
		productList.removeIf(p -> p.getId() == id);
	}

	//商品名から情報を取得
	public Product getProductByName(String name) {
		for (Product p : productList) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

	@Override
	//商品検索
	public List<Product> search(String keyword) {
		List<Product> result = new ArrayList<>();
		for (Product p : productList) {
			if (p.getName().contains(keyword)) {
				result.add(p);
			}
		}
		return result;
	}

	public List<Product> getProducts() {
		return productList;
	}

	//商品すべて表示
	public void showAllProducts() {
		if (productList.isEmpty()) {
			System.out.println("登録されている商品はありません。");
		} else {
			for (Product p : productList) {
				System.out.println(p);
			}
		}
	}
}
