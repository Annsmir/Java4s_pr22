package com.example.ex22;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService bsvc;

    // return a list of all
    @GetMapping
    public ResponseEntity<List<Building>> getAll() {
        // Query<Building> q = ss.createQuery("from Building", Building.class);

        return new ResponseEntity<>(bsvc.getAll(), HttpStatus.OK);
    }

    // return a single item
    @GetMapping({"/{id}"})
    public ResponseEntity<Building> getById(@PathVariable long id) {

        Building a = bsvc.getById(id); // ss.get(Building.class, id);

        if(a == null) {
            return new ResponseEntity<>(a, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(a, HttpStatus.OK);
        }
        
        
    }

    // filter by zip
    @GetMapping("/fortype")
    public ResponseEntity<List<Building>> findByType(@RequestParam String type) {

        return new ResponseEntity<>(bsvc.findByType(type), HttpStatus.OK);
    }

    // filter by text - to pass wildcard (%) use '%25' in URL
    @GetMapping("/fordate")
    public ResponseEntity<List<Building>> findByCreationDate(@RequestParam String date) {

        return new ResponseEntity<>(bsvc.findByCreationDate(date), HttpStatus.OK);
    }

    // creates a new item and saves it to the DB and returns the count of created items
    @PostMapping
    public ResponseEntity<Building> create(@RequestBody Building a) {

        bsvc.create(a);
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    // deletes the specified Id and returns the count of deleted items
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Building> delete(@PathVariable("id") Long id) {
        Building a = bsvc.getById(id); // ss.get(Building.class, id);

        if(a == null) {
            return new ResponseEntity<>(a, HttpStatus.NOT_FOUND);
        } else {

            bsvc.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    // updates the specified Id and returns the count of updated items
    @PutMapping({"/{id}"})
    public ResponseEntity<Building> edit(@PathVariable("id") Long id, @RequestBody Building changed) {
        Building a = bsvc.getById(id); // ss.get(Building.class, id);

        if(a == null) {
            return new ResponseEntity<>(a, HttpStatus.NOT_FOUND);
        } else {
            a.setType(changed.getType());
            a.setCreationDate(changed.getCreationDate());

            bsvc.update(a);
            return new ResponseEntity<>(a, HttpStatus.OK);
        }

    }

}
