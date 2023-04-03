package com.horbatenko.testtask.controller;

import com.horbatenko.testtask.model.KPacSet;
import com.horbatenko.testtask.repository.KPacSetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/data/sets/", produces = MediaType.APPLICATION_JSON_VALUE)
public class KPacSetController {

    private static Logger logger = LoggerFactory.getLogger(KPacSetController.class);

    private KPacSetRepository repository;

    public KPacSetController(KPacSetRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<KPacSet> getKPacSets() {
        logger.debug("Get all KPacSets");
        return repository.getAll();
    }

    @GetMapping("{id}")
    public KPacSet getKPacSet(@PathVariable("id") int id) {
        logger.debug("Get KPacSet by id: {}", id);
        return repository.getById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteKPacSet(@PathVariable("id") int id) {
        logger.debug("Delete KPacSet by id: {}", id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KPacSet> createKPacSet(@RequestBody KPacSet kPacSet) {
        logger.debug("Create KPacSet with body: {}", kPacSet);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(kPacSet));
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KPacSet> updateKPacSet(@PathVariable("id") Integer id,
                                                 @RequestBody KPacSet kPacSet) {
        logger.debug("Update KPacSet by Id={} with body: {}", id, kPacSet);
        kPacSet.setId(id);
        repository.save(kPacSet);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
