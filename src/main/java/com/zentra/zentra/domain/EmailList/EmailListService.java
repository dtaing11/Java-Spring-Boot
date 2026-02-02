package com.zentra.zentra.domain.EmailList;


import com.zentra.zentra.domain.Orgs.roles.OrgRoles;
import com.zentra.zentra.domain.Orgs.roles.OrgsRoleService;
import com.zentra.zentra.domain.Product.Product;
import com.zentra.zentra.domain.Product.ProductSerivce;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmailListService {
    private final EmailListRepository emailListRepository;
    private final ProductSerivce productSerivce;
    private final OrgsRoleService orgsRoleService;
    public EmailListService (EmailListRepository emailListRepository , ProductSerivce productSerivce, OrgsRoleService orgsRoleService) {
        this.emailListRepository = emailListRepository;
        this.productSerivce = productSerivce;
        this.orgsRoleService = orgsRoleService;
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


    @Transactional
    public List<EmailList> findbyProduct (UUID userId,UUID productId) {
        try {
            Product product = productSerivce.findById(productId);
            orgsRoleService.findUserRole(userId, product.getOrgId());
        }
        catch (Exception e) {
            new IllegalArgumentException(e);
        }
        return emailListRepository.findByPdId(productId).orElse(null);


    }




}



