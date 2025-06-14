public class Drink extends MenuItem {
    public Drink(String code, String name, int price) {
        super(code, name, price);
    }

    @Override
    public double calculateTax(boolean isFreeTax) {
        if (isFreeTax) return 0;
        if (price >= 50 && price <= 55) return price * 0.08 * quantity;
        else if (price > 55) return price * 0.11 * quantity;
        return 0;
    }
}
