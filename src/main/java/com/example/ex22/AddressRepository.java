package com.example.ex22;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address,Long> {

    public List<Address> findAllByZipCode(String zipCode);
    public List<Address> findAllByAddressText(String addressText);

    @Query(value = "select address.* from address where address.bid = :buildingId", nativeQuery = true)
    public List<Address> findAllByBuildingId(@Param("buildingId") Long buildingId);
    
}
