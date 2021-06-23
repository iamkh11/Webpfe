package com.kh.Webpfe.repositories;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;


import com.kh.Webpfe.models.Players;







@Repository
public interface PlayersRepository extends MongoRepository<Players, String> {
	
	    @Override
	    public void delete(Players players);
	
	    List<Players> findAll();
	
	
	
	
}
