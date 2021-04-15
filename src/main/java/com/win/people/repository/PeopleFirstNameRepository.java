package com.win.people.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.win.people.domain.PeopleFirstName;

@Repository
public interface PeopleFirstNameRepository extends JpaRepository<PeopleFirstName, Long>{
	
//	@Transactional(readOnly=true)
//	@Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome")
//	public List<Cidade> findCidades(@Param("estadoId") Integer estado_id);
	
	@Transactional(readOnly = true)
	@Query(nativeQuery = true, value = "SELECT * FROM people_first_name ORDER BY RANDOM() LIMIT :count ")
//	@Query("SELECT p FROM PeopleFirstName p ORDER BY RANDOM")
	public List<PeopleFirstName> randomSearch(@Param("count") Integer count);
	
	@Transactional(readOnly = true)
	@Query(nativeQuery = true, value = "SELECT * FROM people_first_name WHERE gender LIKE :gender ORDER BY RANDOM() LIMIT :count ")
//	@Query("SELECT p FROM PeopleFirstName p WHERE p.gender LIKE :gender ORDER BY RANDOM")
	public List<PeopleFirstName> randomSearchGender(@Param("count") Integer count, @Param("gender") String gender);
	
}
