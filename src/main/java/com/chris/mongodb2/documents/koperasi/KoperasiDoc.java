package com.chris.mongodb2.documents.koperasi;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "koperasi")
public class KoperasiDoc {

    @Id
    String id;
    String namaKoperasi;
    String jenisKoperasi;
    Alamat alamat;
    List<Pegawai> lstPegawai;
}

/*

{
    "id": "",
    "namaKoperasi": "Pabrik Tahu",
    "jenisKoperasi": "UKM",
    "alamat": {
        "idProvinsi": "1",
        "provinsi": "DKI Jakarta",
        "idKabupaten": "2",
        "kabupaten": "Jakarta Timus",
        "idKecamatan": "3",
        "kecamatan": "Ciracas",
        "idKelurahan": "4",
        "kelurahan": "Kelapa Dua Wetan"
    },
    "lstPegawai": [
        {
            "nama": "Benjo",
            "divisi": "Kantor",
            "bagian": "Marketing",
            "usia": 25,
            "lstKeluarga": [
                {
                    "nama": "Iriana",
                    "hubungan": "istri",
                    "usia": 24
                },
                {
                    "nama": "Inda",
                    "hubungan": "anak",
                    "usia": 5
                },
                {
                    "nama": "anto",
                    "hubungan": "anak",
                    "usia": 7
                }
            ]
        },
        {
            "nama": "Asep",
            "divisi": "Lapangan",
            "bagian": "Delivery",
            "usia": 30,
            "lstKeluarga": [
                {
                    "nama": "Henny",
                    "hubungan": "istri",
                    "usia": 21
                },
                {
                    "nama": "Ucok",
                    "hubungan": "anak",
                    "usia": 10
                },
                {
                    "nama": "andi",
                    "hubungan": "anak",
                    "usia": 17
                }
            ]
        }
    ]
}


* */