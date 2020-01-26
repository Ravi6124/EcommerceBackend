package com.example.demoSpringLoginMicroService.repository;

import com.example.demoSpringLoginMicroService.entity.LoginHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface LoginHistoryRepository extends CrudRepository<LoginHistory,Integer> {



    @Query(value = "SELECT * FROM LOGIN_HISTORY lh where lh.user_id=:userId and DATE_PART('day',CAST(login_time AS timestamp)  - localtimestamp) between 0 and 180",nativeQuery = true)
    List<LoginHistory> findLoginHistory(@Param("userId") int id);

}
