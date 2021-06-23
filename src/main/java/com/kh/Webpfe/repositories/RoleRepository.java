package com.kh.Webpfe.repositories;




import org.springframework.data.mongodb.repository.MongoRepository;

import com.kh.Webpfe.models.Role;


public interface RoleRepository extends MongoRepository<Role, String> {
    
    Role findByRole(String role);
    
}