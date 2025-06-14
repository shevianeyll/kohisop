import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Order> semuaPesanan = new ArrayList<>();

        System.out.println("Member Tetap: Haris, Cindi, Shevia\n");

        // Simulasi 3 pelanggan bertransaksi
        for (int i = 1; i <= 3; i++) {
            System.out.print("Masukkan nama pelanggan ke-" + i + ": ");
            String nama = sc.nextLine();
            Member member = new Member(nama);
            Order order = new Order(member);

            List<MenuItem> daftarMenu = new ArrayList<>();
            // Menu lengkap (minuman)
            daftarMenu.add(new Drink("A1", "Caffe Latte", 46));
            daftarMenu.add(new Drink("A2", "Cappuccino", 46));
            daftarMenu.add(new Drink("E1", "Caffe Americano", 37));
            daftarMenu.add(new Drink("E2", "Caffe Mocha", 55));
            daftarMenu.add(new Drink("E3", "Caramel Macchiato", 59));
            daftarMenu.add(new Drink("E4", "Asian Dolce Latte", 55));
            daftarMenu.add(new Drink("E5", "Double Shots Iced Shaken Espresso", 50));
            daftarMenu.add(new Drink("B1", "Freshly Brewed Coffee", 23));
            daftarMenu.add(new Drink("B2", "Vanilla Sweet Cream Cold Brew", 50));
            daftarMenu.add(new Drink("B3", "Cold Brew", 44));
            // Menu lengkap (makanan)
            daftarMenu.add(new Food("M1", "Petemania Pizza", 112));
            daftarMenu.add(new Food("M2", "Mie Rebus Super Mario", 35));
            daftarMenu.add(new Food("M3", "Ayam Bakar Goreng Rebus Spesial", 72));
            daftarMenu.add(new Food("M4", "Soto Kambing Iga Guling", 124));
            daftarMenu.add(new Food("S1", "Singkong Bakar A La Carte", 37));
            daftarMenu.add(new Food("S2", "Ubi Cilembu Bakar Arang", 58));
            daftarMenu.add(new Food("S3", "Tempe Mendoan", 18));
            daftarMenu.add(new Food("S4", "Tahu Bakso Extra Telur", 28));

            for (MenuItem menu : daftarMenu) {
                System.out.printf("%s - %s : Rp%d\n", menu.getCode(), menu.getName(), menu.getPrice());
            }

            while (true) {
                System.out.print("Masukkan kode menu (CC untuk batal): ");
                String kode = sc.nextLine();
                if (kode.equalsIgnoreCase("CC")) break;

                MenuItem dipilih = null;
                for (MenuItem m : daftarMenu)
                    if (m.getCode().equalsIgnoreCase(kode)) dipilih = m;

                if (dipilih == null) {
                    System.out.println("Kode tidak valid.");
                    continue;
                }

                System.out.print("Jumlah (default 1): ");
                String inputQty = sc.nextLine();
                int qty = inputQty.isEmpty() ? 1 : Integer.parseInt(inputQty);

                if (dipilih instanceof Drink && qty > 3 || dipilih instanceof Food && qty > 2) {
                    System.out.println("Jumlah melebihi batas.");
                    continue;
                }

                MenuItem itemCopy = dipilih instanceof Drink ?
                    new Drink(dipilih.getCode(), dipilih.getName(), dipilih.getPrice()) :
                    new Food(dipilih.getCode(), dipilih.getName(), dipilih.getPrice());
                itemCopy.setQuantity(qty);
                order.addItem(itemCopy);

                System.out.print("Tambah lagi? (y/n): ");
                if (!sc.nextLine().equalsIgnoreCase("y")) break;
            }

            System.out.println("Pilih metode pembayaran: 1.Tunai 2.QRIS 3.eMoney");
            int pm = Integer.parseInt(sc.nextLine());
            if (pm == 2) order.setPaymentMethod(new QRIS());
            else if (pm == 3) order.setPaymentMethod(new EMoney());
            else order.setPaymentMethod(new Cash());

            System.out.println("Pilih mata uang: 1.IDR 2.USD 3.EUR 4.JPY 5.MYR");
            int curr = Integer.parseInt(sc.nextLine());
            Currency currency = switch (curr) {
                case 2 -> new USD();
                case 3 -> new EUR();
                case 4 -> new JPY();
                case 5 -> new MYR();
                default -> new Currency() {
                    public double convertFromIDR(double amount) { return amount; }
                    public String getName() { return "IDR"; }
                };
            };
            order.setCurrency(currency);
            member.tambahPoin(order.getFinalTotalIDR());
            order.printReceipt();

            semuaPesanan.add(order);
            System.out.println("===========================\n");
        }

        // ========== PROSES DAPUR ==========
        System.out.println("\n>>> PROSES DAPUR MULAI <<<");
        Stack<MenuItem> prosesMinuman = new Stack<>();
        PriorityQueue<MenuItem> prosesMakanan = new PriorityQueue<>((a, b) -> b.getPrice() - a.getPrice());

        for (Order o : semuaPesanan) {
            for (MenuItem item : o.getItems()) {
                if (item instanceof Drink) prosesMinuman.push(item);
                else if (item instanceof Food) prosesMakanan.offer(item);
            }
        }

        System.out.println("\n[Tim Minuman - Last Ordered First Served]");
        while (!prosesMinuman.isEmpty()) {
            MenuItem item = prosesMinuman.pop();
            System.out.printf("%s (%d pcs) - %s\n", item.getName(), item.getQuantity(), item.getCode());
        }

        System.out.println("\n[Tim Makanan - Berdasarkan Harga (Prioritas Tinggi)]");
        while (!prosesMakanan.isEmpty()) {
            MenuItem item = prosesMakanan.poll();
            System.out.printf("%s (%d pcs) - Rp%d\n", item.getName(), item.getQuantity(), item.getPrice());
        }
    }
}
