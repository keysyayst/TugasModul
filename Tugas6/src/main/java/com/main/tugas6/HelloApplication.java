package com.main.tugas6;

import com.data.Admin;
import com.data.Student;
import com.data.User;
import com.util.iMenu;
import exception.custom.IllegalAdminAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.data.Student.scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    private final Scanner scanner = new Scanner(System.in);
    Admin admin = new Admin();
    String inputUsername, inputPassword;

    public  HelloApplication() {
        this.addTempBook();
        this.addTempStudent();
    }
    public static void main(String[] args) {
        launch();
        System.out.println("SELAMAT DATANG");
        Main lib = new Main();
        Scanner input = new Scanner(System.in);
        lib.menu(input);
        input.close();
    }
        public void menu(Scanner input) {
            boolean selesai = false;
            while (!selesai) {
                System.out.println("===== Library System =====");
                System.out.println("1. Login as Student");
                System.out.println("2. Login as Admin");
                System.out.println("3. Exit");
                System.out.print("Pilihan Opsi (1-3): ");
                try {
                    int pilihan = input.nextInt();
                    switch (pilihan) {
                        case 1:
                            inputNim();
                            break;
                        case 2:
                            loginAsAdmin();
                            break;
                        case 3:
                            selesai = true;
                            System.out.println("Program selesai, Exit program");
                            break;
                        default:
                            System.out.println("Pilihan tidak tersedia");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Pilihan harus berupa angka (1-3). Silakan coba lagi.");
                    input.nextLine();
                }
            }

}

    private void loginAsAdmin() {
        iMenu menuAdmin = new Admin();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        inputUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        inputPassword = scanner.nextLine();
        try {
            if (admin.isAdmin(inputUsername, inputPassword)) {
                System.out.println("Login berhasil. Selamat datang " + inputUsername);
                menuAdmin.Menu();
            } else {
                System.out.println("Username atau password salah. Silakan coba lagi.");
            }
        } catch (IllegalAdminAccess e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputNim() {
            System.out.print("Enter your NIM : ");
            String nim = scanner.nextLine();
            checkNim(nim);
        }

    public void checkNim(String nim) {
    iMenu menuStudent = new Student();
    Student student = admin.getStudentByNim(nim);
    if (student != null) {
        student.displayInfo(student);
        menuStudent.Menu();
        return;
    }
    System.out.println("NIM tidak ditemukan, silakan coba lagi");
}

    public void addTempBook(){
    User.addBook("388c-e681-9152", "Foxit eSign", "Author1", "Accessibility", 1);
    User.addBook("d95e-28c4-9523", "Nana Buku", "Author2", "Category", 2);
    User.addBook("sgsg-ytgf-we54", "Sejarah", "Author3", "Sejarah", 8);
    User.addBook("rdgf-rtgf-evgt", "Sejarah", "Author3","Sejarah" , 8);
}

    public void addTempStudent(){
    admin.addedStudent("Keysya", "202310370311363", "Teknik", "Informatika");
    admin.addedStudent("mas", "201110370311777", "Teknik", "Informatika");

}
}
    }
