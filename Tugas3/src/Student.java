import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User{
    String name, nim, faculty, studyProgram;
    public static ArrayList<Book> bukuTerpinjam;
    public static ArrayList<Student> maLog;
    static Scanner scanner;
    //construktor
    public Student(String name, String nim, String faculty, String studyProgram) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
        bukuTerpinjam = new ArrayList<>();
        maLog = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public Student() {
    }

    //getter setter
    public String getNim() {
        return nim;
    }
    public String getName() {
        return name;
    }
    public String getFaculty(){
        return faculty;
    }
    public String getStudyProgram(){
        return studyProgram;
    }


    public void displayInfo(Student student){
        System.out.println("Login sebagai: " + student.getName() + "(" + student.getNim()+")");
    }

    //method displayBook
    @Override
    public void displayBook() {
        super.displayBook();
    }

    //method tampilBukuterpinjam
    public void tampilBukuTerpinjam() {
        if (bukuTerpinjam.isEmpty()) {
            System.out.println("Kamu belum meminjam buku");
        } else {
            System.out.println("Books yang dipinjam oleh :");
            System.out.println("=========================================================================================================================");
            System.out.printf("|| %-25s || %-25s || %-25s || %-20s ||", "ID Buku", "Nama Buku", "Penulis", "Kategori");
            System.out.println("\n=========================================================================================================================");
            for (Book buku : bukuTerpinjam) {
                System.out.printf("|| %-25s || %-25s || %-25s || %-20s ||\n", buku.getId(), buku.getTitle(), buku.getAuthor(),buku.getCategory());
            }
            System.out.print("=========================================================================================================================\n");
        }
    }
    //method Pinjam buku
    public void pinjamBuku() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Id buku yang ingin dipinjam:");
        String idBuku = scanner.nextLine();
        Book bukuygPinjam = null;
        for (Book buku : Main.bookList) {
            if (buku.getId().equals(idBuku)) {
                bukuygPinjam = buku;
                break;
            }
        }

        // Check if the book ID was found
        if (bukuygPinjam == null) {
            System.out.println("Id buku tidak tersedia");
            return;
        }

        if (bukuygPinjam.getStock() > 0) {
            bukuygPinjam.setStock(bukuygPinjam.getStock() - 1);
            bukuTerpinjam.add(bukuygPinjam);
            System.out.println("berapa hari buku akan dipinjam?(maksimal 15 hari)");
            int jumlahHariPeminjaman = scanner.nextInt();
            for (Book bukuu : bukuTerpinjam) {
                bukuu.setDaysToReturn(jumlahHariPeminjaman);
            }
            System.out.println("Buku berhasil dipinjam, kamu harus mengembalikannya sebelum " + jumlahHariPeminjaman + " hari.");
        } else {
            System.out.println("Buku tidak tersedia");
        }
    }


    //method log out
    public void logout() {
        if (bukuTerpinjam.isEmpty()) {
            System.out.println("log out");
        } else {
            System.out.println("batal meminjam buku? (Y/N)");
            String jawab = scanner.next();
            if (jawab.equalsIgnoreCase("Y")) {
                returnBooks();
            } else {
                System.out.println("Peminjaman buku berhasil dilakukan");
                System.out.println("Kamu telah log out");
            }
        }
    }

    public void returnBooks() {
        if(bukuTerpinjam.isEmpty()) {
            System.out.println("tidak ada buku terpinjam untuk dikembalikan.");
            return;
        }
        System.out.println("Masukkan id buku yang ingin di kembalikan:");
        String idBuku = scanner.nextLine();
        Book bukuPinjam = null;
        for(Book buku : Main.bookList) {
            if(buku.getId().equals(idBuku)) {
                bukuPinjam = buku;
                break;
            }
        }
        if(bukuPinjam == null) {
            System.out.println("Buku tidak ditemukan.");
            return;
        }
        for(Book buku : bukuTerpinjam) {
            if(buku.getId().equals(idBuku)) {
                bukuPinjam.setStock(bukuPinjam.getStock() + 1);
                bukuTerpinjam.remove(buku);
                System.out.println("Buku berhasil dikembalikan.");
                return;
            }
        }
        System.out.println("Buku tidak ditemukan di daftar buku terpinjam.");
    }
}