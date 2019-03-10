package com.example.demo.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Advertisement;

import java.util.List;

@Repository
public interface AdvRepository extends CrudRepository<Advertisement, Long> {

    List<Advertisement> findByStatus(String status);

    List<Advertisement> findByUserIdAndStatus(long userId,  String status);
    
    @Query("from Advertisement a where a.id BETWEEN  :start and :end")
    List<Advertisement> findBetween(@Param("start") int start, @Param("end") int end);


}
