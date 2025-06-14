import java.util.*;

public class MenuRepository {
    private static final Map<String, MenuItem> menuItems = new HashMap<>();

    static {
        // Minuman
        menuItems.put("A1", new Drink("A1", "Caffe Latte", 46));
        menuItems.put("A2", new Drink("A2", "Cappuccino", 46));
        menuItems.put("E1", new Drink("E1", "Caffe Americano", 37));
        menuItems.put("E2", new Drink("E2", "Caffe Mocha", 55));
        menuItems.put("E3", new Drink("E3", "Caramel Macchiato", 59));
        menuItems.put("E4", new Drink("E4", "Asian Dolce Latte", 55));
        menuItems.put("E5", new Drink("E5", "Double Shots Iced Shaken Espresso", 50));
        menuItems.put("B1", new Drink("B1", "Freshly Brewed Coffee", 23));
        menuItems.put("B2", new Drink("B2", "Vanilla Sweet Cream Cold Brew", 50));
        menuItems.put("B3", new Drink("B3", "Cold Brew", 44));

        // Makanan
        menuItems.put("M1", new Food("M1", "Petemania Pizza", 112));
        menuItems.put("M2", new Food("M2", "Mie Rebus Super Mario", 35));
        menuItems.put("M3", new Food("M3", "Ayam Bakar Goreng Rebus Spesial", 72));
        menuItems.put("M4", new Food("M4", "Soto Kambing Iga Guling", 124));
        menuItems.put("S1", new Food("S1", "Singkong Bakar A La Carte", 37));
        menuItems.put("S2", new Food("S2", "Ubi Cilembu Bakar Arang", 58));
        menuItems.put("S3", new Food("S3", "Tempe Mendoan", 18));
        menuItems.put("S4", new Food("S4", "Tahu Bakso Extra Telur", 28));
    }

    public static MenuItem getMenuItem(String code) {
        return menuItems.get(code.toUpperCase());
    }

    public static void showAllMenu() {
        System.out.println("===== Daftar Menu =====");
        for (MenuItem item : menuItems.values()) {
            System.out.printf("%s - %s: Rp%d\n", item.getCode(), item.getName(), item.getPrice());
        }
        System.out.println("========================");
    }
}
