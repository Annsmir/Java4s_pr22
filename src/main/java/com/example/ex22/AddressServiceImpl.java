package com.example.ex22;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {
    private final AddressRepository rep;

    // private final EmailService JMsender;
    // private final SpringEmailService SJMsender;

    @Override
    @Transactional(readOnly = true)
    public List<Address> getAll() {
        log.info("Address Service getAll()");
        return rep.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Address getById(Long id) {
        log.info("Address Service getById( {} )", id);
        if(rep.existsById(id)) {
            return rep.getById(id);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id){
        log.info("Address Service deleteById( {} )", id);
        rep.deleteById(id);
        // SJMsender.sendMail("Database object deleted", "Address object id " + id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Address a) {
        log.info("Address Service update( {} )", a);
        rep.saveAndFlush(a);
    }

    @Override
    @Transactional(readOnly = false)
    public void create(Address a) {
        log.info("Address Service create( {} )", a);
        rep.saveAndFlush(a);
        // JMsender.sendEmail("Database object saved", "Address object " + a);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findByZip(String zip) {
        log.info("Address Service findByZip( {} )", zip);
        return rep.findAllByZipCode(zip);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findByText(String text) {
        log.info("Address Service findByText( {} )", text);
        return rep.findAllByAddressText(text);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findByBuilding(Long bId) {
        log.info("Address Service findByBuilding( {} )", bId);
        return rep.findAllByBuildingId(bId);
    }

}
