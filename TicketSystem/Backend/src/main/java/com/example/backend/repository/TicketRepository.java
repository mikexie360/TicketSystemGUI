package com.example.backend.repository;

import com.example.backend.entity.Ticket;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @Query(value ="SELECT * From ticket where isResolved = 'false'"
            ,nativeQuery = true)
    List<Ticket> findUnResolvedTickets();
    @Query(value ="SELECT * From ticket where isResolved = 'true'"
            ,nativeQuery = true)
    List<Ticket> findResolvedTickets();
    @Modifying
    @Transactional
    @Query(value ="UPDATE ticket t SET t.isResolved = 'true' WHERE t.id = ?1"
            ,nativeQuery = true)
    void resolveTicketById(Long id);
}
