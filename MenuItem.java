public abstract class MenuItem {
    protected String code, name;
    protected int price;
    protected int quantity = 1;

    public MenuItem(String code, String name, int price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public void setQuantity(int qty) { this.quantity = qty; }
    public int getQuantity() { return quantity; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public double totalPrice() { return price * quantity; }
    public abstract double calculateTax(boolean isFreeTax);
}
