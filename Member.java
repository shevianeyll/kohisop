import java.util.*;

public class Member {
    private static final Set<String> daftarMemberTetap = new HashSet<>(Arrays.asList(
        "Haris", "Cindi", "Shevia"
    ));

    private String nama;
    private String kodeMember;
    private int poin;
    private boolean valid;

    public Member(String nama) {
        this.nama = nama;
        this.valid = daftarMemberTetap.contains(nama);
        this.kodeMember = valid ? "MEM" + nama.toUpperCase().charAt(0) + "01" : "-";
        this.poin = 0;
    }

    public boolean isValid() {
        return valid;
    }

    public void tambahPoin(double totalIDR) {
        if (!valid) return;
        int p = (int)(totalIDR / 10);
        if (kodeMember.contains("A")) p *= 2;
        poin += p;
    }

    public void pakaiPoin(double[] tagihanIDR) {
        if (!valid) return;
        int nilai = poin * 2;
        if (nilai >= tagihanIDR[0]) {
            nilai -= tagihanIDR[0];
            tagihanIDR[0] = 0;
        } else {
            tagihanIDR[0] -= nilai;
            nilai = 0;
        }
        poin = nilai / 2;
    }

    public String getNama() { return nama; }
    public String getKodeMember() { return kodeMember; }
    public int getPoin() { return poin; }
}
