package product;

import java.util.ArrayList;
import java.util.List;

public class ProductsManager implements Searchable {
	private List<Product> productList = new ArrayList<>();

	// 商品を追加
	public void addProduct(Product product) {
		productList.add(product);
	}

	// 商品を削除（id指定）
	public void removeProduct(int id) {
		productList.removeIf(p -> p.getId() == id);
	}

	// 名前で商品を取得
	public Product getProductByName(String name) {
		for (Product p : productList) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

	@Override
    public List<Product> search(String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getName().contains(keyword)) {
                result.add(p);
            }
        }
        return result;
    }

    // 全商品取得（確認用）
    public List<Product> showAllProducts() {
        return productList;
    }
}