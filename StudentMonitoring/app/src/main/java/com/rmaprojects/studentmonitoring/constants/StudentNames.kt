package com.rmaprojects.studentmonitoring.constants

val studentList = listOf(
    StudentModel("Abdul Naseer", "X"),
    StudentModel("Hanif Fawwaz Muyassar", "X"),
    StudentModel("M. Fadhil Ammar S.", "X"),
    StudentModel("Muhammad Ardan Unais Albani", "X"),
    StudentModel("Muhammad Faiz Kharul Azzam", "X"),
    StudentModel("Muhammad Hadiid Permana", "X"),
    StudentModel("Muhammad Irsyaad Fadhilah", "X"),
    StudentModel("Naufal Aliefky Arfiandany", "X"),
    StudentModel("Nindya Ekadarma Aji Prapanca", "X"),
    StudentModel("Rezvan Haidar Awwal", "X"),
    StudentModel("Ilyas", "X"),
    StudentModel("Muhammad Faiz Abdurrahman", "X"),
    StudentModel("Naufal Islami Pratama", "X"),
    StudentModel("Rezvan Haidar Awwal", "X"),

    StudentModel("Arya Dzikra Avicenna", "XI"),
    StudentModel("Bariq Nabil Rahman", "XI"),
    StudentModel("Fadhil Akbar Djunaedi", "XI"),
    StudentModel("Fariz Al Fathin Fayi", "XI"),
    StudentModel("Hariz Abdul Hakim", "XI"),
    StudentModel("Ilham Purnama Hadi", "XI"),
    StudentModel("Muhamad Hashfi Hazazi", "XI"),
    StudentModel("Muhammad Akbar Fajar Fadillah Tandean", "XI"),
    StudentModel("Muhammad Faiz Mukhtar", "XI"),
    StudentModel("Muhammad Hafidzh Musthofa", "XI"),
    StudentModel("Muhammad Myko Adityo", "XI"),
    StudentModel("Muhammad Naufal Trisliawan", "XI"),
    StudentModel("Muhammad Raheesh Aqeel Utama", "XI"),
    StudentModel("Raihan Mahdy Ananda", "XI"),
    StudentModel("Sayyid Muhammad Muslim As'ad Sunarko", "XI"),
    StudentModel("Zaid Zeid Alrayan", "XI"),
    StudentModel("Sayyid Muhammad Muslim As'ad Sunarko", "XI"),

    StudentModel("Achmad Dhiya'ulhaq", "XII"),
    StudentModel("Ahmad Rafie Ramadhani Azzaki", "XII"),
    StudentModel("Daffa Naswan Pramono", "XII"),
    StudentModel("Fayiz Yahya", "XII"),
    StudentModel("Hamas Azizan", "XII"),
    StudentModel("Jamilurrahman", "XII"),
    StudentModel("Muhammad Akmal Malik", "XII"),
    StudentModel("Muhammad Akna Mafaid Ilmi Anshori", "XII"),
    StudentModel("Muhammad Exel Prayoga", "XII"),
    StudentModel("Muhammad Ficrie El Ghaznavy", "XII"),
    StudentModel("Muhammad Fathul Falah", "XII"),
    StudentModel("Muhammad Yusuf Ekhar Putraramadana", "XII"),
)

data class StudentModel(
    val studentName: String,
    val studentClass: String
)