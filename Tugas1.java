import java.util.*;

public class Tugas1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Masukkan jumlah data: ");
        int n = sc.nextInt();
        sc.nextLine(); 
        
        String[][] letters = new String[n][3];
        System.out.println("Masukkan data (Format: Nama Kategori Waktu):");
        for (int i = 0; i < n; i++) {
            System.out.print("Data ke-" + (i + 1) + ": ");
            String[] data = sc.nextLine().split(" ");
            letters[i][0] = data[0];          
            letters[i][1] = data[1];           
            letters[i][2] = data[2];           
        }
        
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                int p1 = Integer.parseInt(letters[j][2]);
                int p2 = Integer.parseInt(letters[j + 1][2]);
                if (p1 > p2) {
                    String[] temp = letters[j];
                    letters[j] = letters[j + 1];
                    letters[j + 1] = temp;
                }
            }
        }
        
        List<String[]> pending = new ArrayList<>(Arrays.asList(letters));
        
        int time = 0;
        Queue<String[]> queue = new LinkedList<>();
        List<String> sent = new ArrayList<>();
        
        System.out.println("\n--- Hasil Simulasi ---");
        while (!pending.isEmpty() || !queue.isEmpty()) {
            int i = 0;
            while (i < pending.size()) {
                int priority = Integer.parseInt(pending.get(i)[2]);
                if (priority <= time) {
                    queue.add(pending.remove(i));
                } else {
                    i++;
                }
            }
            
            while (!queue.isEmpty()) {
                sent.add(queue.poll()[0]);
            }
            
            List<String> pendingNames = new ArrayList<>();
            for (String[] l : pending) pendingNames.add(l[0]);
            
            List<String> queueNames = new ArrayList<>();
            for (String[] l : queue) queueNames.add(l[0]);
            
            System.out.print(time + " ");
            
            if (!pendingNames.isEmpty()) {
                System.out.print(String.join(" ", pendingNames));
            }
            
            if (!queueNames.isEmpty()) {
                if (!pendingNames.isEmpty()) System.out.print(" | ");
                else System.out.print(" ");
                System.out.print(String.join(" ", queueNames));
            }
            
            if (!sent.isEmpty()) {
                if (!pendingNames.isEmpty() || !queueNames.isEmpty()) System.out.print(" | ");
                else System.out.print(" ");
                System.out.print(String.join(" ", sent));
            } else if (pendingNames.isEmpty() && queueNames.isEmpty()) {
                System.out.print("| |");
            }
            
            System.out.println();
            sent.clear();
            time++;
        }
        
        sc.close();
    }
}