package com.zentra.zentra.domain.EmailList;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface EmailListRepository extends JpaRepository<EmailList, UUID> {

    @Modifying
    @Transactional
    @Query("""
        DELETE FROM EmailList e
        WHERE e.pdId = :pdId
          AND e.userId = :userId
    """)
    void deleteByPdIdAndUserId(UUID pdId, UUID userId);



    //Find by Product
    Optional<List<EmailList>> findByPdId(UUID pdId);
}
