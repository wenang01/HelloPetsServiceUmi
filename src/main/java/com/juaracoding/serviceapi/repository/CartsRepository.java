package com.juaracoding.serviceapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.juaracoding.serviceapi.entity.Carts;
import com.juaracoding.serviceapi.entity.User;

public interface CartsRepository extends CrudRepository<Carts, Long>{

//	Optional<Carts> findUser(Long userId);
//	User findUser(User userId);
	
	@Query(value="select * from carts as c where c.users_id = :userId", nativeQuery = true)
	Optional<Carts> findByUserId(Long userId);
}
