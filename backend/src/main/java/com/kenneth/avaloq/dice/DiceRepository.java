package com.kenneth.avaloq.dice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiceRepository extends MongoRepository<DiceSummary, String> {

}
