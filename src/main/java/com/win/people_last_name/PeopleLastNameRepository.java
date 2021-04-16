package com.win.people_last_name;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PeopleLastNameRepository extends PagingAndSortingRepository<PeopleLastName, Long>{
	
	@Transactional(readOnly = true)
	@Query(nativeQuery = true, value = "SELECT * FROM people_last_name ORDER BY RANDOM() LIMIT :count")
	public List<PeopleLastName> randomSearch(@Param("count") Integer count);
	

}
