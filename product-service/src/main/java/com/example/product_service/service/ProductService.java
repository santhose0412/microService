package com.example.product_service.service;

import com.example.product_service.ProductServiceApplication;
import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.events.ProcessingInstruction;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> insertProducts(List<Product> products)
    {
        return productRepo.saveAll(products);
    }

    public List<Product> fetchAllProducts()
    {
        return productRepo.findAll();
    }

    public Product getProductById(Long id)
    {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product with id \"+id+\" not found!..."));
    }

    @Modifying
    @Transactional
    public Product updateProduct(Product products,Long id) throws Exception
    {
        Product product = productRepo.findById(id).orElseThrow(() -> new Exception("Product with id "+id+" not found!..."));
        product.setName(products.getName());
        product.setPrice(products.getPrice());

        return productRepo.save(product);
    }

    @Modifying
    @Transactional
    public String deleteProduct(Long id) throws Exception
    {
        Product product = productRepo.findById(id).orElseThrow(() -> new Exception("Product with id "+id+" not found!..."));
        productRepo.delete(product);
        return  "Product with id "+id+" deleted successfully...";
    }

}
