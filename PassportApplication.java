package models;
// Mendeklarasikan package 'models' untuk mengorganisir kelas terkait data model.

import utils.DateUtils;
// Mengimpor kelas 'DateUtils' dari package 'utils' untuk format tanggal.

import java.util.Date;
// Mengimpor kelas 'Date' untuk menangani tanggal.

public class PassportApplication {
    // Mendefinisikan kelas 'PassportApplication' yang merepresentasikan aplikasi paspor.

    private String applicationId; // ID aplikasi untuk mengidentifikasi aplikasi paspor.
    private Date submissionDate; // Tanggal pengajuan aplikasi.

    // Konstruktor untuk menginisialisasi ID aplikasi dan secara otomatis mengatur tanggal pengajuan ke waktu saat ini.
    public PassportApplication(String applicationId) {
        this.applicationId = applicationId; // Mengatur ID aplikasi.
        this.submissionDate = new Date(); // Mengatur tanggal pengajuan ke waktu saat ini.
    }

    // Getter untuk mendapatkan nilai applicationId.
    public String getApplicationId() { 
        return applicationId; 
    }

    // Setter untuk memperbarui nilai applicationId.
    public void setApplicationId(String applicationId) { 
        this.applicationId = applicationId; 
    }

    // Getter untuk mendapatkan nilai submissionDate.
    public Date getSubmissionDate() { 
        return submissionDate; 
    }

    // Method untuk mendapatkan tanggal pengajuan dalam format yang telah diformat.
    public String getFormattedSubmissionDate() {
        return DateUtils.formatDate(submissionDate); 
        // Menggunakan utilitas DateUtils untuk memformat tanggal pengajuan.
    }
}
