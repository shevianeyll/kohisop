import java.util.*;

public class Order {
    private List<MenuItem> items = new ArrayList<>();
    private PaymentMethod paymentMethod;
    private Currency currency;
    private Member member;

    public Order(Member member) {
        this.member = member;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public List<MenuItem> getItems() { return items; }
    public void setPaymentMethod(PaymentMethod pm) { this.paymentMethod = pm; }
    public void setCurrency(Currency c) { this.currency = c; }

    public double getTotalPrice() {
        double total = 0;
        for (MenuItem item : items) total += item.totalPrice();
        return total;
    }

    public double getTotalTax() {
        double total = 0;
        boolean freeTax = member.getKodeMember().contains("A");
        for (MenuItem item : items) total += item.calculateTax(freeTax);
        return total;
    }

    public double getFinalTotalIDR() {
        double subtotal = getTotalPrice() + getTotalTax();
        return paymentMethod.applyDiscount(subtotal) + paymentMethod.getAdminFee();
    }

    public void printReceipt() {
        System.out.println("===== STRUK PEMBELIAN KOHISOP =====");
        for (MenuItem item : items) {
            System.out.printf("%s - %s (%d x Rp%d) = Rp%.2f | Pajak: Rp%.2f\n",
                item.getCode(), item.getName(), item.getQuantity(), item.getPrice(),
                item.totalPrice(), item.calculateTax(member.getKodeMember().contains("A")));
        }

        double total = getTotalPrice();
        double tax = getTotalTax();
        double afterDiscount = paymentMethod.applyDiscount(total + tax);
        double admin = paymentMethod.getAdminFee();
        double[] totalAkhir = { afterDiscount + admin };

        if (currency.getName().equals("IDR")) member.pakaiPoin(totalAkhir);

        double converted = currency.convertFromIDR(totalAkhir[0]);
        System.out.println("------------------------------------");
        System.out.printf("Subtotal: Rp%.2f\n", total);
        System.out.printf("Pajak: Rp%.2f\n", tax);
        System.out.printf("Diskon + Admin (%s): Rp%.2f\n", paymentMethod.getName(), admin);
        System.out.printf("Total Bayar (IDR): Rp%.2f\n", totalAkhir[0]);
        System.out.printf("Total Bayar (%s): %.2f\n", currency.getName(), converted);
        System.out.printf("Poin sekarang: %d\n", member.getPoin());
        System.out.println("Terima kasih dan silakan datang kembali!");
    }
}
