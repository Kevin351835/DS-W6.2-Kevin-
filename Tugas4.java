import java.util.*;

public class Tugas4 {
    private static Scanner scanner = new Scanner(System.in);
    
    static class Visitor {
        String name;
        int money;
        
        Visitor(String name, int money) {
            this.name = name;
            this.money = money;
        }
    }
    
    public static void main(String[] args) { 
        System.out.print("Masukkan jumlah pengunjung");
        int n = scanner.nextInt();
        scanner.nextLine();
        
        if (n <= 1 || n >= 1000) {
            System.out.println("Error");
            return;
        }
        
        System.out.print("Masukkan " + n + " nama");
        String namesLine = scanner.nextLine();
        String[] names = namesLine.split(",\\s*");
        
        if (names.length != n) {
            System.out.println("Error");
            return;
        }
        
        System.out.print("Masukkan " + n + " jumlah uang (pisahkan dengan koma, 1-100): ");
        String moneyLine = scanner.nextLine();
        String[] moneyStr = moneyLine.split(",\\s*");
        
        if (moneyStr.length != n) {
            System.out.println("Error");
            return;
        }
        
        int[] money = new int[n];
        for (int i = 0; i < n; i++) {
            try {
                money[i] = Integer.parseInt(moneyStr[i]);
                if (money[i] <= 1 || money[i] >= 100) {
                    System.out.println("Error (input: " + money[i] + ")");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error");
                return;
            }
        }
        
        List<Visitor> visitors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            visitors.add(new Visitor(names[i], money[i]));
        }
        
        System.out.println("\n📋 Data awal:");
        for (int i = 0; i < n; i++) {
            System.out.println((i+1) + ". " + visitors.get(i).name + " - Rp" + visitors.get(i).money);
        }
        
        boolean hasJeff = false;
        for (Visitor v : visitors) {
            if (v.name.equalsIgnoreCase("Jeff")) {
                hasJeff = true;
                break;
            }
        }
        
        visitors.sort((a, b) -> Integer.compare(b.money, a.money));
        
        List<Visitor> filteredVisitors = new ArrayList<>();
        for (Visitor v : visitors) {
            if (!v.name.equalsIgnoreCase("Jeff")) {
                filteredVisitors.add(v);
            }
        }
        
        if (hasJeff) {
            System.out.println("Dikeluarkan");
        }
        
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < filteredVisitors.size(); i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(filteredVisitors.get(i).name);
        }
        result.append("]");
        
        System.out.println("\nurutan antrian final:");
        System.out.println("   " + result.toString());
        
        System.out.println("\nUrutan");
        for (int i = 0; i < filteredVisitors.size(); i++) {
            Visitor v = filteredVisitors.get(i);
            String arrow = (i == 0) ? "🏆 " : "   ";
            System.out.printf("%s%d. %s (Rp%d)%n", arrow, i+1, v.name, v.money);
        }
        
        System.out.println("   Pengunjung dengan uang yang sama ditempatkan berurutan:");
        
        Map<Integer, List<String>> moneyGroups = new HashMap<>();
        for (Visitor v : filteredVisitors) {
            moneyGroups.computeIfAbsent(v.money, k -> new ArrayList<>()).add(v.name);
        }
        
        for (Map.Entry<Integer, List<String>> entry : moneyGroups.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("   • Rp" + entry.getKey() + " : " + String.join(", ", entry.getValue()));
            }
        }
        
    }
}