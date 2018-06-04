package com.example.lutfi.crudmahasiswa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lutfi.crudmahasiswa.Adapter.MahasiswaAdapter;
import com.example.lutfi.crudmahasiswa.Model.Mahasiswa;
import com.example.lutfi.crudmahasiswa.Model.MahasiswaResult;
import com.example.lutfi.crudmahasiswa.Network.ApiClient;
import com.example.lutfi.crudmahasiswa.Network.MahasiswaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<Mahasiswa> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialisasi
        // final ListView lvuser = (ListView) findViewById(R.id.lv_user);

        // 1. Menyiapkan sumber data
        // 1.a. array string 1 dimensi
        //        ArrayList<String> user = new ArrayList<>();
        //        user.add("User 1");
        //        user.add("User 2");
        //        user.add("User 3");
        //        user.add("User 4");


        // 1.b. POJO (Plain Old Java Object)
//        Mahasiswa mahasiswa1 = new Mahasiswa();
//        mahasiswa1.setNama("Lutfi");
//        mahasiswa1.setNim("3.34.15.0.13");
//        mahasiswa1.setEmail("lutfi@gmail.com");
//        mahasiswa1.setFoto("https://picsum.photos/200/300/?random");
//
//        Mahasiswa mahasiswa2 = new Mahasiswa(
//                "Wati",
//                "3.34.15.0.12",
//                "wati@gmail.com",
//                "https://picsum.photos/200/300/?random"
//        );
//
//        Mahasiswa mahasiswa3 = new Mahasiswa(
//                "Fitri",
//                "3.34.15.0.08",
//                "fitri@gmail.com",
//                "https://picsum.photos/200/300/?random"
//        );
//
//        data = new ArrayList<>();
//        data.add(mahasiswa1);
//        data.add(mahasiswa2);
//        data.add(mahasiswa3);

        // 1.c. Sumber data dari API / JSON Object dari internet menggunakan Retrofit
        MahasiswaService mService = ApiClient.getmRetrofit()
                .create(MahasiswaService.class);

        Call<MahasiswaResult> mahasiswas = mService.getMahasiswas();
        mahasiswas.enqueue(new Callback<MahasiswaResult>() {
            @Override
            public void onResponse(Call<MahasiswaResult> call, Response<MahasiswaResult> response) {
                tampilkan(response.body().getMahasiswas());
                Toast.makeText(
                        getApplicationContext(),
                        "jumlah mahasiswa : " + response.body().getMahasiswas().size(),
                        Toast.LENGTH_SHORT
                ).show();
            }

            @Override
            public void onFailure(Call<MahasiswaResult> call, Throwable t) {

            }
        });


    }

    private void tampilkan(List<Mahasiswa> mahasiswas){
        // 2 Menyiapkan adapter
        // Fungsi adapter sendiri adalah mengkomunikasikan sumber data yang kita miliki dengan tampilan

        MahasiswaAdapter userAdapter = new MahasiswaAdapter(this, 0, mahasiswas);

        // 3. Tampilan Aplikasi
        ListView lvUser = (ListView) findViewById(R.id.lv_user);
        lvUser.setAdapter(userAdapter);
    }
