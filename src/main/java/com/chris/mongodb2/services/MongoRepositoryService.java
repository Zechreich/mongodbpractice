package com.chris.mongodb2.services;

import com.chris.mongodb2.documents.koperasi.KoperasiDoc;
import com.chris.mongodb2.repositories.KoperasiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Service
public class MongoRepositoryService {

    @Autowired
    KoperasiRepo koperasiRepo;

    public Flux<KoperasiDoc> getAllKoperasi() {
        return koperasiRepo.findAll();
    }

    public Mono<KoperasiDoc> getKoperasiById(String id) {
        return koperasiRepo.findById(id);
    }

    public Mono<KoperasiDoc> saveKoperasi(KoperasiDoc koperasi) {
        return koperasiRepo.save(koperasi);
    }

    public Mono<KoperasiDoc> updateKoperasi(String id, KoperasiDoc koperasi) throws ExecutionException, InterruptedException {
        Mono<KoperasiDoc> loadMono = koperasiRepo.findById(id);
        KoperasiDoc load = loadMono.toFuture().get();
        if(load != null){
            koperasi.setId(id);
            return koperasiRepo.save(koperasi);
        }
        return null;
    }

    public Mono<Void> deleteKoperasi(String id){

        return koperasiRepo.deleteById(id);


    }
}
