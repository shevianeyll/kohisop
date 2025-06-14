public class USD implements Currency {
    public double convertFromIDR(double amount) { return amount / 15.0; }
    public String getName() { return "USD"; }
}
