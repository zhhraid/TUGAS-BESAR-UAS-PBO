package exceptions;
// Mendeklarasikan package 'exceptions' untuk mengorganisir kelas exception khusus.

public class ValidationException extends Exception {
// Mendefinisikan kelas 'ValidationException' yang merupakan turunan dari kelas 'Exception'.

    public ValidationException(String message) {
        // Konstruktor untuk menginisialisasi ValidationException dengan pesan kesalahan.
        super(message);
        // Memanggil konstruktor superclass (Exception) dengan parameter 'message' untuk menyimpan pesan kesalahan.
    }
}
