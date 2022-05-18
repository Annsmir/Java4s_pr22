package com.example.ex22;

import java.util.List;

public interface BuildingService {
    public List<Building> getAll();
    public Building getById(long id);
    public void deleteById(long id);
    public void update(Building a);
    public void create(Building a);
    public List<Building> findByCreationDate(String creationDate);
    public List<Building> findByType(String type);
}
