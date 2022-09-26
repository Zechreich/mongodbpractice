package com.chris.mongodb2.services;

import com.chris.mongodb2.documents.game.GameDoc;
import com.chris.mongodb2.documents.game.GameSumOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class MongoTemplateService {

    @Autowired
    ReactiveMongoTemplate mongoTemplate;
    public Flux<GameDoc> getAllGame() {
        return mongoTemplate.findAll(GameDoc.class);
    }

    public Mono<Long> countGame() {
        Query query = new Query();
        Mono<Long> countMono = mongoTemplate.count(query, GameDoc.class);
        return countMono;
    }

    public Mono<Long> countGame(String kategori) {
        Query query = new Query();
        query.addCriteria(Criteria.where("kategori").is(kategori));
        Mono<Long> countMono = mongoTemplate.count(query, GameDoc.class);
        return countMono;
    }

    public Flux<GameDoc> getAllGame(Integer page, Integer size) {
        Query query = new Query();
        query.with(PageRequest.of(page, size));
        query.with(Sort.by(Sort.Direction.ASC, "nomorUrut"));

        return mongoTemplate.find(query, GameDoc.class);
    }

    public Flux<GameDoc> getAllGame(String kategori, Integer page, Integer size) {
        Query query = new Query();
        query.addCriteria(Criteria.where("kategori").is(kategori));
        query.with(PageRequest.of(page, size));
        query.with(Sort.by(Sort.Direction.ASC, "nomorUrut"));

        return mongoTemplate.find(query, GameDoc.class);
    }

    public Flux<GameDoc> getGameBetweenDurasi(Integer start, Integer end) {

        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "nomorUrut"));
        query.addCriteria(Criteria.where("durasiPermainan").gt(start).lt(end));

        return mongoTemplate.find(query, GameDoc.class);
    }

    public Flux<GameSumOutput> getSumDurasiByKategori(String kategori) {
        Aggregation agg = Aggregation.newAggregation(
                //match(Criteria.where("kategori").is(kategori)),
                group("kategori").sum("durasiPermainan").as("sumJamMain")
        );

        Flux<GameSumOutput> results = mongoTemplate.aggregate(agg, "durasiGame", GameSumOutput.class);
        return results;
    }

    public Flux<GameSumOutput> getSumDurasiLookupKeteranganByKategori(String kategori) {
        Aggregation agg = Aggregation.newAggregation(
                //match(Criteria.where("kategori").is(kategori)),
                group("kategori").sum("durasiPermainan").as("sumJamMain"),
                lookup("game_reff","_id","kategori", "keterangan")
        );

        Flux<GameSumOutput> results = mongoTemplate.aggregate(agg, "durasiGame", GameSumOutput.class);
        return results;
    }
}
