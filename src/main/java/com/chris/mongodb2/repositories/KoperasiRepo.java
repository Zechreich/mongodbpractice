package com.chris.mongodb2.repositories;

import com.chris.mongodb2.documents.koperasi.KoperasiDoc;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoperasiRepo extends ReactiveMongoRepository<KoperasiDoc, String> {

}
