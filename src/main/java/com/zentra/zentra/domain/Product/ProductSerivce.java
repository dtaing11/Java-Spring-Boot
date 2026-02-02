package com.zentra.zentra.domain.Product;


import com.zentra.zentra.domain.Orgs.roles.OrgRoles;
import com.zentra.zentra.domain.Orgs.roles.OrgsRoleService;
import com.zentra.zentra.domain.User.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductSerivce {

    ProductRepository productRepository;
    OrgsRoleService orgsRoleService;
    public ProductSerivce(ProductRepository productRepository,  OrgsRoleService orgsRoleService) {
        this.productRepository = productRepository;
        this.orgsRoleService = orgsRoleService;
    }


    @Transactional
    public Product create(UUID createBy, String name, Optional<String> description, UUID orgId){
        orgsRoleService.findUserRole(createBy, orgId);
        Product PD = new Product(
                name,
                description.orElse(null),
                createBy,
                orgId
        );
        return productRepository.save(PD);
    }
    @Transactional
    public Product updateProduct(UUID pd_id, Optional<String> name, Optional<String> description, UUID user_id){
        orgsRoleService.findUserRole(user_id, pd_id);
        Product pd = productRepository.findById(pd_id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        name.ifPresent(pd::setName);
        description.ifPresent(pd::setDescription);

        return productRepository.save(pd);
    }

    public Product findById(UUID pd_id){
        return productRepository.findById(pd_id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Transactional
    public List<Product> findByOrgsId(UUID userId,UUID orgsId){
        orgsRoleService.findUserRole(userId, orgsId);
        return productRepository.findByOrgId(orgsId);
    }


}
