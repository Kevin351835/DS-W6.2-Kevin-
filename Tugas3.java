import java.util.*;

public class Tugas3 {
    private static Scanner scanner = new Scanner(System.in);
    
    static class Student {
        String name;
        int chances;
        
        Student(String name, int chances) {
            this.name = name;
            this.chances = chances;
        }
        
        @Override
        public String toString() {
            return name + " (" + chances + " kesempatan)";
        }
    }
    
    public static void main(String[] args) {
       
        System.out.print("Masukkan panjang antrian ");
        int n = scanner.nextInt();
        scanner.nextLine();
        
        if (n <= 5 || n >= 20) {
            System.out.println("Error");
            return;
        }
        
        System.out.print("Masukkan " + n + " nama (pisahkan dengan spasi): ");
        String namesLine = scanner.nextLine();
        String[] names = namesLine.split(" ");
        
        if (names.length != n) {
            System.out.println("Error");
            return;
        }
        
        System.out.print("Masukkan " + n + " jumlah kesempatan: ");
        String chancesLine = scanner.nextLine();
        String[] chancesStr = chancesLine.split(" ");
        
        if (chancesStr.length != n) {
            System.out.println("Error");
            return;
        }
        
        int[] chances = new int[n];
        for (int i = 0; i < n; i++) {
            try {
                chances[i] = Integer.parseInt(chancesStr[i]);
                if (chances[i] < 1 || chances[i] > 10) {
                    System.out.println("Error (input: " + chances[i] + ")");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error");
                return;
            }
        }
        
        Queue<Student> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            queue.offer(new Student(names[i], chances[i]));
        }
        
        System.out.println("\n📋 Data sebelum konsul:");
        int index = 1;
        for (Student s : queue) {
            System.out.println((index++) + ". " + s.name + " - " + s.chances + " kesempatan");
        }
        
        System.out.println("[Nama]|[Status]|[Sisa Kesempatan]");
        
        int turn = 1;
        while (!queue.isEmpty()) {
            Student current = queue.poll();
            
            if (current.chances > 1) {
                current.chances--;
                System.out.println("Turn " + turn + ": " + current.name + "|Try Again|" + current.chances);
                queue.offer(current);
            } else {
                System.out.println("Turn " + turn + ": " + current.name + "|Get Out|0");
            }
            turn++;
        }
        
    }
}