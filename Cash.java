public class Cash implements PaymentMethod {
    public double applyDiscount(double total) { return total; }
    public double getAdminFee() { return 0; }
    public String getName() { return "Tunai"; }
}
