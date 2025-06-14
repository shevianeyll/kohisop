public class MYR implements Currency {
    public double convertFromIDR(double amount) { return amount / 4.0; }
    public String getName() { return "MYR"; }
}
