package models;
// Mendeklarasikan package 'models' untuk mengorganisir kelas-kelas model.

public class Person {
    // Mendefinisikan kelas 'Person' yang merepresentasikan data umum seseorang.

    private String name; // Nama seseorang.
    private String address; // Alamat seseorang.
    private String phoneNumber; // Nomor telepon seseorang.

    // Konstruktor untuk menginisialisasi atribut 'name', 'address', dan 'phoneNumber'.
    public Person(String name, String address, String phoneNumber) {
        this.name = name; // Mengatur nilai atribut 'name' berdasarkan parameter yang diberikan.
        this.address = address; // Mengatur nilai atribut 'address' berdasarkan parameter yang diberikan.
        this.phoneNumber = phoneNumber; // Mengatur nilai atribut 'phoneNumber' berdasarkan parameter yang diberikan.
    }

    // Getter untuk mendapatkan nilai atribut 'name'.
    public String getName() { 
        return name; 
    }

    // Setter untuk memperbarui nilai atribut 'name'.
    public void setName(String name) { 
        this.name = name; 
    }

    // Getter untuk mendapatkan nilai atribut 'address'.
    public String getAddress() { 
        return address; 
    }

    // Setter untuk memperbarui nilai atribut 'address'.
    public void setAddress(String address) { 
        this.address = address; 
    }

    // Getter untuk mendapatkan nilai atribut 'phoneNumber'.
    public String getPhoneNumber() { 
        return phoneNumber; 
    }

    // Setter untuk memperbarui nilai atribut 'phoneNumber'.
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }
}
