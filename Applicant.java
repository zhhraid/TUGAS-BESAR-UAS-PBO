package models;
// Mendeklarasikan package 'models' untuk mengorganisir kelas yang berhubungan dengan data model.

import exceptions.ValidationException;
import java.util.Date;

public class Applicant extends Person {
// Mendefinisikan kelas 'Applicant' yang merupakan turunan dari kelas 'Person'.

    private String applicationId; // ID aplikasi untuk setiap pelamar.
    private int age; // Usia pelamar.
    private Date creationDate; // Tanggal pembuatan aplikasi.
    private int passportFee; // Biaya pembuatan paspor.

    // Konstruktor dengan parameter tambahan untuk inisialisasi data pelamar.
    public Applicant(String name, String address, String phoneNumber, String applicationId, int age, Date creationDate, int passportFee) throws ValidationException {
        super(name, address, phoneNumber); // Memanggil konstruktor superclass (Person) untuk menginisialisasi atribut dasar.
        
        if (age <= 0) { 
            // Validasi bahwa usia harus lebih besar dari 0.
            throw new ValidationException("Age must be greater than 0.");
        }
        
        if (name == null || name.trim().isEmpty()) {
            // Validasi bahwa nama tidak boleh kosong.
            throw new ValidationException("Name cannot be empty.");
        }
        
        this.applicationId = applicationId;
        this.age = age;
        this.creationDate = creationDate;
        this.passportFee = passportFee;
    }

    // Getter dan setter untuk mengakses dan memperbarui atribut kelas.
    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws ValidationException {
        if (age <= 0) {
            // Validasi bahwa usia harus lebih besar dari 0.
            throw new ValidationException("Age must be greater than 0.");
        }
        this.age = age;
        this.passportFee = calculateFee(); // Perbarui biaya paspor berdasarkan usia baru.
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getPassportFee() {
        return passportFee;
    }

    public void setPassportFee(int passportFee) {
        this.passportFee = passportFee;
    }

    // Method untuk menghitung biaya pembuatan paspor berdasarkan usia.
    public int calculateFee() {
        return age < 18 ? 350000 : 500000; 
        // Jika usia kurang dari 18 tahun, biaya 350.000; jika tidak, biaya 500.000.
    }

    @Override
    public String toString() {
        // Mengoverride metode toString untuk menampilkan data pelamar dalam format string.
        return "Applicant{" +
                "applicationId='" + applicationId + '\'' +
                ", name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", age=" + age +
                ", creationDate=" + creationDate +
                ", passportFee=" + passportFee +
                '}';
    }
}
