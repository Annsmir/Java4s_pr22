package com.example.ex22;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuldingServiceImpl implements BuildingService {
    private final BuildingRepository rep;

    @Override
    @Transactional(readOnly = true)
    public List<Building> getAll() {
        log.info("Building Service getAll()");
        return rep.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Building getById(long id) {
        log.info("Building Service getById( {} )", id);
        if(rep.existsById(id)) {
            return rep.getById(id);
        } else {
            return null;
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(long id) {
        log.info("Building Service deleteById( {} )", id);
        rep.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Building a) {
        log.info("Building Service update( {} )", a);
        rep.saveAndFlush(a);
    }

    @Override
    @Transactional(readOnly = false)
    public void create(Building a) {
        log.info("Building Service create( {} )", a);
        rep.saveAndFlush(a);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Building> findByCreationDate(String creationDate) {
        log.info("Building Service findByCreationDate( {} )", creationDate);
        return rep.findByCreationDate(creationDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Building> findByType(String type) {
        log.info("Building Service findByType( {} )", type);
        return rep.findByType(type);
    }
}
