package com.chris.mongodb2.documents.game;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "game_reff")
public class GameReffDoc {
    @Id
    String id;
    String kategori;
    String keterangan;
}