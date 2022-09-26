package com.chris.mongodb2.controllers;

import com.chris.mongodb2.documents.koperasi.KoperasiDoc;
import com.chris.mongodb2.services.MongoRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
public class MongoRepositoryController {

    @Autowired
    MongoRepositoryService koperasiService;

    @RequestMapping(value = "/loadAllKoperasi", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseEntity<Flux<KoperasiDoc>> getAllKoperasi(){
        Flux<KoperasiDoc> result = koperasiService.getAllKoperasi();
        return new ResponseEntity<Flux<KoperasiDoc>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/loadKoperasiById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseEntity<Mono<KoperasiDoc>> loadKoperasiById(@PathVariable(name = "id") String id){
        Mono<KoperasiDoc> result = koperasiService.getKoperasiById(id);
        return new ResponseEntity<Mono<KoperasiDoc>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveKoperasi", method = RequestMethod.POST)
    public ResponseEntity<Mono<KoperasiDoc>> saveKoperasi(@RequestBody KoperasiDoc koperasi){
        koperasi.setId(UUID.randomUUID().toString());
        Mono<KoperasiDoc> result = koperasiService.saveKoperasi(koperasi);
        return new ResponseEntity<Mono<KoperasiDoc>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateKoperasi/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Mono<KoperasiDoc>> updateKoperasi(
            @PathVariable(name = "id") String id,
            @RequestBody KoperasiDoc koperasi) throws ExecutionException, InterruptedException {
        Mono<KoperasiDoc> result = koperasiService.updateKoperasi(id, koperasi);
        return new ResponseEntity<Mono<KoperasiDoc>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteKoperasi/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Mono<Void>> deleteKoperasi(@PathVariable(name = "id") String id){
        koperasiService.deleteKoperasi(id);
        return new ResponseEntity<Mono<Void>>(HttpStatus.OK);
    }
}
