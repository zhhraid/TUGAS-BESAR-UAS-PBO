package exceptions;
// Mendeklarasikan package 'exceptions' untuk mengorganisir kelas exception khusus.

public class DatabaseException extends Exception {
// Mendefinisikan kelas 'DatabaseException' yang merupakan turunan dari kelas 'Exception'.

    public DatabaseException(String message) {
        // Konstruktor untuk menginisialisasi DatabaseException dengan pesan kesalahan.
        super(message);
        // Memanggil konstruktor superclass (Exception) dengan parameter 'message' untuk menyimpan pesan kesalahan.
    }
}
