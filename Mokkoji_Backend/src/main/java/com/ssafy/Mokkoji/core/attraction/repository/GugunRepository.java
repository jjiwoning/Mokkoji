package com.ssafy.Mokkoji.core.attraction.repository;

import com.ssafy.Mokkoji.core.attraction.domain.Gugun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GugunRepository extends JpaRepository<Gugun, Long> {

    @Query("select g from Gugun g where g.sido.sidoCode = :sidoCode")
    List<Gugun> getAllGugunBySidoCode(@Param("sidoCode") int sidoCode);

}
