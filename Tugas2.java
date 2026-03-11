import java.util.*;

public class Tugas2 {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Masukkan jumlah operasi: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new ArrayDeque<>(); 
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        
        List<String> operations = new ArrayList<>();
        
        boolean isStack = true;
        boolean isQueue = true;
        boolean isPriorityQueue = true;
        
        System.out.println("\nMasukkan " + n + " baris operasi:");
        System.out.println("(Format bisa '1 47' atau '147' untuk add nilai 47)");
        System.out.println("(Format bisa '2 47' atau '247' untuk take out nilai 47)");
        System.out.println("--------------------------------------");
        
        for (int i = 0; i < n; i++) {
            System.out.print("Operasi ke-" + (i+1) + ": ");
            String line = scanner.nextLine().trim();
            
            int operation = 0;
            int value = 0;
            
            if (line.contains(" ")) {
                String[] parts = line.split(" ");
                operation = Integer.parseInt(parts[0]);
                value = Integer.parseInt(parts[1]);
            } else if (line.length() == 3) {
                operation = Integer.parseInt(line.substring(0, 1));
                value = Integer.parseInt(line.substring(1, 3));
            } else {
                System.out.println("input salah");
                i--;
                continue;
            }
            
            operations.add(operation + " " + value);
            
            if (operation == 1) { 
                stack.push(value);
                queue.offer(value);
                priorityQueue.offer(value);
                System.out.println("  ➤ Menambahkan nilai: " + value);
            } 
            else if (operation == 2) { 
                System.out.println("  ➤ Mengambil nilai: " + value);
                
                if (stack.isEmpty()) {
                    isStack = false;
                } else {
                    int popped = stack.pop();
                    if (popped != value) {
                        isStack = false;
                    }
                }
                
                if (queue.isEmpty()) {
                    isQueue = false;
                } else {
                    int polled = queue.poll();
                    if (polled != value) {
                        isQueue = false;
                    }
                }
                
                if (priorityQueue.isEmpty()) {
                    isPriorityQueue = false;
                } else {
                    int polled = priorityQueue.poll();
                    if (polled != value) {
                        isPriorityQueue = false;
                    }
                }
            }
            else {
            }
        }
        
        for (String op : operations) {
            System.out.println("   " + op);
        }
        System.out.println("--------------------------------------");
        
        System.out.println("Stack\t\t: " + (isStack ? "✓ Sesuai" : "✗ Tidak sesuai"));
        System.out.println("Queue (ArrayDeque): " + (isQueue ? "✓ Sesuai" : "✗ Tidak sesuai"));
        System.out.println("Priority Queue\t: " + (isPriorityQueue ? "✓ Sesuai" : "✗ Tidak sesuai"));
        System.out.println("--------------------------------------");
        
        if (isStack) {
            System.out.println("stack");
        } else if (isQueue) {
            System.out.println("queue");
        } else if (isPriorityQueue) {
            System.out.println("priority queue");
        } else {
            System.out.println("tidak yakin");
        }
    }
}