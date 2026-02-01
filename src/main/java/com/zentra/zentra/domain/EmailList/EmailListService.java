package com.zentra.zentra.domain.EmailList;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmailListService {
    private EmailListRepository emailListRepository;

    public EmailListService (EmailListRepository emailListRepository) {
        this.emailListRepository = emailListRepository;
    }

    public EmailList signUp (UUID userId, String email, UUID pdID) {
       EmailList emailList = new EmailList(
               email,
               pdID,
               userId
       );

       return emailListRepository.save(emailList);
    }

    public void delete (UUID pdId, UUID userId){
        emailListRepository.deleteByPdIdAndUserId(pdId,userId);
    }

    public List<EmailList> findbyProduct (UUID productId) {
        return emailListRepository.findByPdId(productId).orElse(null);
    }




}



