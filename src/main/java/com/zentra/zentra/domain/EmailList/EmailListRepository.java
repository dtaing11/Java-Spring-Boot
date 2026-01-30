package com.zentra.zentra.domain.EmailList;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailListRepository extends JpaRepository<EmailList, UUID> {
}
