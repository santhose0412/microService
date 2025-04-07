package com.example.product_service.controller;

import com.example.product_service.entity.Product;
import com.example.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/insert")
    public ResponseEntity<?> insertProduct(@RequestBody List<Product> products)
    {
        List<Product> product = productService.insertProducts(products);
        return ResponseEntity.ok(product);
    }

    @GetMapping("fetch-all")
    public ResponseEntity<?> fetchAllProducts()
    {
        List<Product> products = productService.fetchAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id)
    {
        try
        {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @RequestParam Long id)
    {
        try
        {
            Product products = productService.updateProduct(product,id);
            return ResponseEntity.ok(products);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam Long id)
    {
        try
        {
            String msg = productService.deleteProduct(id);
            return ResponseEntity.ok(msg);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
