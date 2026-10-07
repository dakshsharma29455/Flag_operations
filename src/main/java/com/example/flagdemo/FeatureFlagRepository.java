package com.example.flagdemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureFlagRepository extends JpaRepository<FeatureFlag, String> {
    // JpaRepository automatically provides:
    // - save()
    // - findById()
    // - findAll()
    // - deleteById()
    // - existsById()
}
