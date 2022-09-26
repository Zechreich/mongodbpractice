package com.chris.mongodb2.documents.game;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class GameSumOutput {

    @Id
    String _id;
    Long sumJamMain;
    List<GameReffDoc> keterangan;
}
