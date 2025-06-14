public class JPY implements Currency {
    public double convertFromIDR(double amount) { return amount * 10.0; }
    public String getName() { return "JPY"; }
}
