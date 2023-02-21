package com.arn.hdss.service;

import java.util.List;

import com.arn.hdss.entity.Codebook;

public interface CodebookService {

public List<Codebook> findAll();
    
    public Codebook findById(Integer theId);
    
    public void save(Codebook codebook);
    
    public void deleteById(Integer theId);
    

}
