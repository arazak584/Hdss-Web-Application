package com.arn.hdss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arn.hdss.entity.Cluster;

public interface ClusterRepository extends JpaRepository <Cluster, String> {


}
