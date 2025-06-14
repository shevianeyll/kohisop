public interface PaymentMethod {
    double applyDiscount(double total);
    double getAdminFee();
    String getName();
}
