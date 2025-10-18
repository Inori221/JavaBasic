package product;

public class Main {
    public static void main(String[] args) {
        ProductsManager manager = new ProductsManager();

        System.out.println("----商品を5つ追加して全てを表示する----");
        manager.addProduct(new Product(1, "冷蔵庫", 50000, 10));
        manager.addProduct(new Product(2, "ソファ", 30000, 5));
        manager.addProduct(new Product(3, "米", 2000, 3));
        manager.addProduct(new Product(4, "小説", 1500, 4));
        manager.addProduct(new Product(5, "Tシャツ", 1500, 5));
        manager.showAllProducts();

        System.out.println("\n----商品を1つ削除して全てを表示する----");
        manager.removeProduct(1);
        manager.showAllProducts();

        System.out.println("\n----商品名「米」の情報を表示する----");
        Product found = manager.getProductByName("米");
        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("該当する商品はありません。");
        }
    }
}