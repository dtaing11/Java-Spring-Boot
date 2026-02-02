package com.zentra.zentra.Api.Product;


import com.zentra.zentra.Api.Product.Request.CreateRequest;
import com.zentra.zentra.Api.Product.Request.UpdateRequest;
import com.zentra.zentra.domain.Product.Product;
import com.zentra.zentra.domain.Product.ProductSerivce;
import com.zentra.zentra.domain.User.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
public ProductSerivce productSerivce;
public ProductController(ProductSerivce productSerivce) {
    this.productSerivce = productSerivce;
}


@PostMapping("/create")
    public ResponseEntity<Product> create(@AuthenticationPrincipal User user, @RequestBody CreateRequest createRequest) {
    Product product = productSerivce.create(
            user.getId(),
            createRequest.name(),
            createRequest.description(),
            createRequest.orgId()
    );
    return ResponseEntity.ok(product);
}


@PutMapping("/update")
    public ResponseEntity<?> update(@AuthenticationPrincipal User user, @RequestBody UpdateRequest updateRequest) {
    Product product = productSerivce.updateProduct(
            updateRequest.pd_id(),
            updateRequest.name(),
            updateRequest.description(),
            user.getId()
    );
    return ResponseEntity.ok("Product has been updated:" + product.getName());
}

@GetMapping("/find/{orgId}")
    public ResponseEntity<List<Product>> getProduct (@AuthenticationPrincipal User user, @PathVariable("orgId") UUID orgId) {
    List<Product> productList = productSerivce.findByOrgsId(user.getId(), orgId);
    return ResponseEntity.ok(productList);
}





}