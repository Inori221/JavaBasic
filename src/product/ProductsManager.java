package product;

import java.util.ArrayList;
import java.util.List;

public class ProductsManager {
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

    // 全商品を表示
    public void showAllProducts() {
        for (Product p : productList) {
            System.out.println(p);
        }
    }
}