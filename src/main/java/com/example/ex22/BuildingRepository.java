package com.example.ex22;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building,Long> {
    public List<Building> findByCreationDate(String creationDate);
    public List<Building> findByType(String type);
}
