package com.example.ex22;

import java.util.List;

public interface AddressService {
    public List<Address> getAll();
    public Address getById(Long id);
    public void deleteById(Long id);
    public void update(Address a);
    public void create(Address a);
    public List<Address> findByZip(String zip);
    public List<Address> findByText(String text);
    public List<Address> findByBuilding(Long bId);
}
