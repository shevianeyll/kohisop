public class EMoney implements PaymentMethod {
    public double applyDiscount(double total) { return total * 0.93; }
    public double getAdminFee() { return 20; }
    public String getName() { return "eMoney"; }
}
