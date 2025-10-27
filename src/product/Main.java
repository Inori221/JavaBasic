package product;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductsManager manager = new ProductsManager();

        // 商品登録
        manager.addProduct(new Product(1, "冷蔵庫", 50000, 10));
        manager.addProduct(new Product(2, "ソファ", 30000, 5));
        manager.addProduct(new Product(3, "米", 2000, 3));
        manager.addProduct(new Product(4, "小説", 1500, 4));
        manager.addProduct(new Product(5, "Tシャツ", 1500, 5));

        // ① ソファ＋割引30%
        System.out.println("--商品名「ソファ」の情報と割引率30%の情報を表示する--");
        Product sofa = manager.getProductByName("ソファ");
        if (sofa != null) {
            DiscountedProduct discountedSofa = new DiscountedProduct(
                sofa.getId(), sofa.getName(), sofa.getPrice(), sofa.getStock(), 0.3);
            System.out.println(discountedSofa);
        } else {
            System.out.println("該当する商品はありません。");
        }

        // ② 「Tシャツ」を検索
        System.out.println("\n--商品名「Tシャツ」を検索して表示する--");
        List<Product> result = manager.search("Tシャツ");
        if (!result.isEmpty()) {
            for (Product p : result) {
                System.out.println(p);
            }
        } else {
            System.out.println("該当する商品はありません。");
        }
    }
}