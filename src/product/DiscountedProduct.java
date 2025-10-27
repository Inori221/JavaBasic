package product;

public class DiscountedProduct extends Product {
	private double discountRate; // 割引率（例：0.3 = 30%）

	public DiscountedProduct(int id, String name, int price, int stock, double discountRate) {
		super(id, name, price, stock);
		this.discountRate = discountRate;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	// 割引後価格を計算
	public int calculateDiscountedPrice() {
		return (int) (getPrice() * (1 - discountRate));
	}

	@Override
	public String toString() {
		return String.format("Product: id=%d, name=%s, price=%d, stock=%d, 割引率=%.1f, 割引後価格=%d",
				getId(), getName(), getPrice(), getStock(), discountRate, calculateDiscountedPrice());
	}
}