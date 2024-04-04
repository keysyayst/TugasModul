import java.util.ArrayList;
import java.util.Scanner;

class Admin{
    public void admin(){
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("Admin Menu:");
            System.out.println("1. Tambah data mahasiswa");
            System.out.println("2. Tampilkan Data Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Pilihan Anda: ");
            int choice = input.nextInt();

            switch (choice){
                case 1:
                    System.out.print("Masukkan nama mahasiswa\t\t: ");
                    input.nextLine();
                    String nmMhs = input.nextLine();
                    System.out.print("Masukkan nim mahasiswa\t\t: ");
                    String nimMhs = input.nextLine();
                    while (nimMhs.length() != 15){
                        System.out.println("Nim Harus 15 Digit!!");
                        System.out.print("Masukkan NIM mahasiswa\t\t: ");
                        nimMhs = input.nextLine();
                    }
                    System.out.print("Masukkan jurusan mahasiwa\t: ");
                    String jurusanMhs = input.nextLine();
                    Mahasiswa.addDaftarMahasiswa(nmMhs, nimMhs, jurusanMhs);
                    break;
                case 2:
                    Mahasiswa.tampilDataMahasiswa();
                    break;
                case 3:
                    System.out.println("Log out, back to Library System");
                    return;
                default:
                    System.out.println("Pilihan Tidak Valid.");
            }
        }
    }
}

class Mahasiswa{
    String nama, nim, jurusan;

    Mahasiswa(String nama, String nim, String jurusan){
        this.nama=nama;
        this.nim=nim;
        this.jurusan=jurusan;
    }

    static String tampilUniversitas(){
        return "Universitas Muhammadiyah Malang";
    }

    static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

    public static void addDaftarMahasiswa(String nama, String nim, String jurusan){
        Mahasiswa mhswBaru = new Mahasiswa(nama, nim, jurusan);
        daftarMahasiswa.add(mhswBaru);
        System.out.println("Data mahasiswa berhasil ditambahkan.");
    }

    public static void tampilDataMahasiswa() {
        System.out.println("Data Mahasiswa:");
        System.out.println("Universitas: " + tampilUniversitas());
        for (Mahasiswa mhs : daftarMahasiswa) {
            System.out.println("Nama: " + mhs.nama + ", NIM: " + mhs.nim + ", Jurusan: " + mhs.jurusan);
        }
    }
}
class studentMain {

    static String[][] bookList = {
            {"388c-e681-9152", "Foxit eSign","Accessibility","author1", "1"},
            {"2", "Title", "sejarah","author2", "3"},
            {"3", "Title", "sejarah", "author3", "2"}};
    public void displayBooks() {
        System.out.println("List of Books:");
        System.out.println("ID\tTitle\tAuthor\tStock");
        for (String[] book : studentMain.bookList) {
            System.out.println(book[0] + "\t" + book[1] + "\t" + book[2] + "\t" + book[3]);
        }
    }

    public void logout() {
        System.out.println("Logging out...");
    }

    public void menuStudent() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Student Dashboard");
            System.out.println("1. Display Available Books");
            System.out.println("2. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayBooks();
                    break;
                case 2:
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 2);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Admin objekAdmin = new Admin();
        studentMain objekStudent = new studentMain();
        boolean selesai = false;
        String nim, username, password;
        System.out.println("SELAMAT DATANG");

        while (!selesai) {
            System.out.println("===== Library System =====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Pilihan Opsi (1-3): ");
            int pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Enter your NIM : ");
                    nim = input.next();
                    if (nim.length() == 15) {
                        System.out.println("Successful Login as Student");
                        objekStudent.menuStudent();
                    } else {
                        System.out.println("User Not Found");
                    }
                    break;
                case 2:
                    System.out.print("Enter your username (admin): ");
                    username = input.next();
                    System.out.print("Enter your password (admin): ");
                    password = input.next();
                    if(username.equals("admin") && password.equals("admin")) {
                        System.out.println("Successful Login as Admin");
                        objekAdmin.admin();
                    } else {
                        System.out.println("Admin User Not Found!!");
                    }
                    break;
                case 3:
                    selesai = true;
                    System.out.println("Program selesai, Exit program");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia");
            }
        }

        input.close();
    }
}