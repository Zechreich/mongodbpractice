package com.chris.mongodb2.documents.game;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "durasiGame")
public class GameDoc {

    @Id
    String id;
    Integer nomorUrut;
    String kategori;
    String judul;
    Integer durasiPermainan;

}
