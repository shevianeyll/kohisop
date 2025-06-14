public class Food extends MenuItem {
    public Food(String code, String name, int price) {
        super(code, name, price);
    }

    @Override
    public double calculateTax(boolean isFreeTax) {
        if (isFreeTax) return 0;
        if (price > 50) return price * 0.08 * quantity;
        return price * 0.11 * quantity;
    }
}
