package com.chris.mongodb2.controllers;

import com.chris.mongodb2.documents.game.GameDoc;
import com.chris.mongodb2.documents.game.GameSumOutput;
import com.chris.mongodb2.services.MongoTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MongoTemplateController {

    @Autowired
    MongoTemplateService gameService;

    @RequestMapping(value = "/loadAllGame", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseEntity<Flux<GameDoc>> getAllGame(){
        Flux<GameDoc> result = gameService.getAllGame();
        return new ResponseEntity<Flux<GameDoc>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/countGame", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseEntity<Mono<Long>> countGame(){
        Mono<Long> countMono = gameService.countGame();
        return new ResponseEntity<Mono<Long>>(countMono, HttpStatus.OK);
    }

    @RequestMapping(value = "/countGameByKategori", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseEntity<Mono<Long>> countGameByKategori(
            @RequestParam(value = "kategori") String kategori){
        Mono<Long> countMono = gameService.countGame(kategori);
        return new ResponseEntity<Mono<Long>>(countMono, HttpStatus.OK);
    }

    @RequestMapping(value = "/loadAllGamePaging", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseEntity<Flux<GameDoc>> getAllGamePaging(
            @RequestParam(name="page") Integer page,
            @RequestParam(name="size") Integer size){
        Flux<GameDoc> result = gameService.getAllGame(page, size);
        return new ResponseEntity<Flux<GameDoc>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/loadGameByKategoriPaging", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseEntity<Flux<GameDoc>> getGameByKategoriPaging(
            @RequestParam(name="kategori") String kategori,
            @RequestParam(name="page") Integer page,
            @RequestParam(name="size") Integer size){
        Flux<GameDoc> result = gameService.getAllGame(kategori, page, size);
        return new ResponseEntity<Flux<GameDoc>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/loadGameBetweenDurasi", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseEntity<Flux<GameDoc>> getGameByKategoriPaging(
            @RequestParam("start") Integer start,
            @RequestParam("end") Integer end){
        Flux<GameDoc> result = gameService.getGameBetweenDurasi(start, end);
        return new ResponseEntity<Flux<GameDoc>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/loadSumDurasiByKategori", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseEntity<Flux<GameSumOutput>> getSumDurasiByKategori(
            @RequestParam("kategori") String kategori){
        Flux<GameSumOutput> result = gameService.getSumDurasiByKategori(kategori);
        return new ResponseEntity<Flux<GameSumOutput>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/loadSumDurasiLookupKeteranganByKategori", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseEntity<Flux<GameSumOutput>> getSumDurasiLookupKeteranganByKategori(
            @RequestParam("kategori") String kategori){
        Flux<GameSumOutput> result = gameService.getSumDurasiLookupKeteranganByKategori(kategori);
        return new ResponseEntity<Flux<GameSumOutput>>(result, HttpStatus.OK);
    }
}
