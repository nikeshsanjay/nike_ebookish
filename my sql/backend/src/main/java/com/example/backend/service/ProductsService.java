package com.example.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.backend.model.Products;
import com.example.backend.repository.ProductsRepo;

@Service
public class ProductsService {
    
    @Autowired
    ProductsRepo pr;

    public Products create(Products pe)
    {
        if (pe != null) {
            return pr.save(pe);
        } else {
            // Handle null case (e.g., throw an exception, return a default value)
            throw new IllegalArgumentException("Signup object cannot be null");
        }
    }

    public List<Products> getAllProducts()
    {
        return pr.findAll();
    }

    public Products getProductById(int id)
    {
        return pr.findById(id).orElse(null);
    }

    public boolean updateProducts(int id, Products pe)
    {
        if(this.getProductById(id)==null)
            return false;

        if(pe!=null)
            pr.save(pe);
        else    
            throw new IllegalArgumentException("No user with given user Id found");

        return true;
    }

    public boolean deleteProduct(int id)
    {
        if(this.getProductById(id)==null)
            return false;
        
        pr.deleteById(id);

        return true;
    }

    public List<Products> getsort(int pageNumber,int pageSize,String field)     
    {          
        return pr.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))).getContent();     
    } 

    public List<Products> sort(String field)     
    {         
        Sort sort=Sort.by(Sort.Direction.ASC,field);         
        return pr.findAll(sort);     
    } 

    public List<Products> page(int pageSize,int pageNumber)     
    {         
        Pageable page=PageRequest.of(pageNumber, pageSize);         
        return pr.findAll(page).getContent();     
    } 

    public List<Products> filterByprice(int price)
    {
        return pr.filterByprice(price);
    }

}