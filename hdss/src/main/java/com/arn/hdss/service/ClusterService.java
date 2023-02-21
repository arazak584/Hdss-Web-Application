package com.arn.hdss.service;

import java.util.List;


import com.arn.hdss.entity.Cluster;


public interface ClusterService {

    public List<Cluster> findAll();
    
    public Cluster findById(String theId);
    
    public void save(Cluster cluster);
    
    public void deleteById(String theId);


}
