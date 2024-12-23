package utils;
// Mendeklarasikan package 'utils' untuk mengorganisir kelas-kelas utilitas.

import java.text.SimpleDateFormat;
// Mengimpor kelas 'SimpleDateFormat' untuk format tanggal.

import java.util.Date;
// Mengimpor kelas 'Date' untuk menangani objek tanggal.

public class DateUtils {
    // Mendefinisikan kelas 'DateUtils' sebagai kelas utilitas untuk manipulasi tanggal.

    // Method statis untuk memformat objek Date ke dalam bentuk string dengan format "dd-MM-yyyy".
    public static String formatDate(Date date) {
        // Membuat objek 'SimpleDateFormat' dengan pola format tanggal "dd-MM-yyyy".
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        // Mengembalikan string hasil format dari objek 'date'.
        return sdf.format(date);
    }
}
