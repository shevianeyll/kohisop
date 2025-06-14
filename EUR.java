public class EUR implements Currency {
    public double convertFromIDR(double amount) { return amount / 14.0; }
    public String getName() { return "EUR"; }
}
