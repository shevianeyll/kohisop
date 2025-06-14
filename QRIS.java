public class QRIS implements PaymentMethod {
    public double applyDiscount(double total) { return total * 0.95; }
    public double getAdminFee() { return 0; }
    public String getName() { return "QRIS"; }
}
