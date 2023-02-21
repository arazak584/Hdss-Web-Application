package com.arn.hdss.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arn.hdss.entity.Cluster;
import com.arn.hdss.repository.ClusterRepository;
import com.arn.hdss.service.ClusterService;


@Service
public class ClusterServiceImpl implements ClusterService {
	
	private ClusterRepository clusterRepository;
	
	@Autowired
	public ClusterServiceImpl(ClusterRepository thedistrictRepository) {
		clusterRepository = thedistrictRepository;
	}

	@Override
	public List<Cluster> findAll() {
		return clusterRepository.findAll();
	}

	@Override
	public Cluster findById(String theId) {
		Optional<Cluster> result = clusterRepository.findById(theId);
		Cluster theCluster = null;
        if(result.isPresent())
        	theCluster = result.get();
        else
            throw new RuntimeException("Cluster id not found - " + theId);
        return theCluster;
	}

	@Override
	public void save(Cluster theCluster) {
		clusterRepository.save(theCluster);
		
	}

	@Override
	public void deleteById(String theId) {
		clusterRepository.deleteById(theId);
		
	}


}
