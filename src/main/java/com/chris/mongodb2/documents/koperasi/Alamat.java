package com.chris.mongodb2.documents.koperasi;

import lombok.Data;

@Data
public class Alamat {

    String idProvinsi;
    String provinsi;
    String idKabupaten;
    String kabupaten;
    String idKecamatan;
    String kecamatan;
    String idKelurahan;
    String kelurahan;


}
