package com.chris.mongodb2.documents.koperasi;

import lombok.Data;

import java.util.List;

@Data
public class Pegawai {

    String nama;
    String divisi;
    String bagian;
    Integer usia;
    List<Keluarga> lstKeluarga;

}
