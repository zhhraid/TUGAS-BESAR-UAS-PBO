import database.PassportDatabase;
import models.Applicant;
import exceptions.ValidationException;
import exceptions.DatabaseException;
import utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Inisialisasi koneksi ke database PassportDatabase
            PassportDatabase db = new PassportDatabase("jdbc:postgresql://localhost:5432/DatabasePasport", "postgres", "Polinomial22");

            while (true) { // Program akan terus berjalan hingga pengguna memilih untuk keluar
                // Menu utama program
                System.out.println("\n=== Sistem Manajemen Pemohon Paspor ===");
                System.out.println("1. Tambah Pemohon");
                System.out.println("2. Lihat Pemohon");
                System.out.println("3. Ubah Pemohon");
                System.out.println("4. Hapus Pemohon");
                System.out.println("5. Tampilkan Semua Pemohon");
                System.out.println("6. Keluar");
                System.out.print("Pilih opsi: ");
                int choice = scanner.nextInt();

                // Menjalankan fitur sesuai pilihan pengguna
                switch (choice) {
                    case 1 -> addApplicant(scanner, db); // Tambah pemohon
                    case 2 -> viewApplicant(scanner, db); // Lihat detail pemohon
                    case 3 -> updateApplicant(scanner, db); // Ubah data pemohon
                    case 4 -> deleteApplicant(scanner, db); // Hapus data pemohon
                    case 5 -> showAllApplicants(db); // Tampilkan semua data pemohon
                    case 6 -> {
                        // Keluar dari program
                        System.out.println("Terima kasih! Program selesai.");
                        return;
                    }
                    default -> System.out.println("Pilihan tidak valid. Coba lagi."); // Pilihan tidak dikenal
                }
            }
        } catch (Exception e) {
            // Menangani kesalahan umum dalam program
            System.err.println("Kesalahan: " + e.getMessage());
        }
    }

    // Fungsi untuk menambahkan pemohon baru
    private static void addApplicant(Scanner scanner, PassportDatabase db) {
        try {
            // Meminta input data pemohon
            System.out.print("Nama: ");
            String name = scanner.next();
            System.out.print("Alamat: ");
            String address = scanner.next();
            System.out.print("Nomor Telepon: ");
            String phoneNumber = scanner.next();
            System.out.print("ID Aplikasi: ");
            String applicationId = scanner.next();
            System.out.print("Usia: ");
            int age = scanner.nextInt();

            // Membuat data pemohon baru
            Date creationDate = new Date(); // Tanggal pembuatan otomatis adalah hari ini
            int passportFee = age < 18 ? 350000 : 500000; // Biaya paspor berdasarkan usia

            // Membuat objek pemohon dan menyimpannya ke database
            Applicant applicant = new Applicant(name.toUpperCase(), address, phoneNumber, applicationId, age, creationDate, passportFee);
            db.createApplicant(applicant);

            // Konfirmasi keberhasilan
            System.out.println("Pemohon berhasil ditambahkan.");
            System.out.println("Tanggal Pembuatan: " + DateUtils.formatDate(creationDate));
            System.out.println("Biaya Pembuatan Paspor: Rp" + passportFee);
        } catch (ValidationException ve) {
            // Menangani kesalahan validasi
            System.err.println("Kesalahan validasi: " + ve.getMessage());
        } catch (DatabaseException de) {
            // Menangani kesalahan pada operasi database
            System.err.println("Kesalahan database: " + de.getMessage());
        }
    }

    // Fungsi untuk melihat detail pemohon berdasarkan ID aplikasi
    private static void viewApplicant(Scanner scanner, PassportDatabase db) {
        System.out.print("Masukkan ID Aplikasi untuk melihat pemohon: ");
        String applicationId = scanner.next();

        try {
            // Mengambil data pemohon dari database
            Applicant applicant = db.readApplicant(applicationId);

            // Menampilkan data pemohon
            System.out.println("=== Informasi Pemohon ===");
            System.out.println("Nama: " + applicant.getName());
            System.out.println("Alamat: " + applicant.getAddress());
            System.out.println("Nomor Telepon: " + applicant.getPhoneNumber());
            System.out.println("Usia: " + applicant.getAge());
            System.out.println("Tanggal Pembuatan: " + DateUtils.formatDate(applicant.getCreationDate()));
            System.out.println("Biaya Pembuatan Paspor: Rp" + applicant.getPassportFee());
        } catch (ValidationException ve) {
            // Menangani kesalahan validasi
            System.err.println("Kesalahan validasi: " + ve.getMessage());
        } catch (DatabaseException de) {
            // Menangani kesalahan pada operasi database
            System.err.println("Kesalahan database: " + de.getMessage());
        }
    }

    // Fungsi untuk mengubah data pemohon
    private static void updateApplicant(Scanner scanner, PassportDatabase db) {
        System.out.print("Masukkan ID Aplikasi untuk mengubah pemohon: ");
        String applicationId = scanner.next();

        try {
            // Mengambil data pemohon yang ada di database
            Applicant existingApplicant = db.readApplicant(applicationId);

            // Meminta input data baru dari pengguna
            System.out.println("Pemohon ditemukan, masukkan data baru.");
            System.out.print("Nama (sekarang: " + existingApplicant.getName() + "): ");
            String name = scanner.next();
            System.out.print("Alamat (sekarang: " + existingApplicant.getAddress() + "): ");
            String address = scanner.next();
            System.out.print("Nomor Telepon (sekarang: " + existingApplicant.getPhoneNumber() + "): ");
            String phoneNumber = scanner.next();
            System.out.print("Usia (sekarang: " + existingApplicant.getAge() + "): ");
            int age = scanner.nextInt();

            // Memperbarui data pemohon
            existingApplicant.setName(name);
            existingApplicant.setAddress(address);
            existingApplicant.setPhoneNumber(phoneNumber);
            existingApplicant.setAge(age);

            // Menyimpan perubahan ke database
            db.updateApplicant(existingApplicant);
            System.out.println("Pemohon berhasil diubah.");
        } catch (ValidationException ve) {
            // Menangani kesalahan validasi
            System.err.println("Kesalahan validasi: " + ve.getMessage());
        } catch (DatabaseException de) {
            // Menangani kesalahan pada operasi database
            System.err.println("Kesalahan database: " + de.getMessage());
        }
    }

    // Fungsi untuk menghapus data pemohon
    private static void deleteApplicant(Scanner scanner, PassportDatabase db) {
        System.out.print("Masukkan ID Aplikasi untuk menghapus pemohon: ");
        String applicationId = scanner.next();

        try {
            // Menghapus data pemohon dari database
            db.deleteApplicant(applicationId);
            System.out.println("Pemohon berhasil dihapus.");
        } catch (DatabaseException de) {
            // Menangani kesalahan pada operasi database
            System.err.println("Kesalahan database: " + de.getMessage());
        }
    }

    // Fungsi untuk menampilkan semua data pemohon
    private static void showAllApplicants(PassportDatabase db) {
        try {
            // Mengambil semua data pemohon dari database
            List<Applicant> applicants = db.getAllApplicants();

            // Menampilkan data jika ada, atau pesan jika kosong
            if (applicants.isEmpty()) {
                System.out.println("Tidak ada data pemohon.");
            } else {
                System.out.println("=== Daftar Semua Pemohon ===");
                for (Applicant applicant : applicants) {
                    System.out.println(applicant);
                }
            }
        } catch (DatabaseException de) {
            // Menangani kesalahan pada operasi database
            System.err.println("Kesalahan database: " + de.getMessage());
        }
    }
}
