package com.horbatenko.testtask.controller;

import com.horbatenko.testtask.model.KPac;
import com.horbatenko.testtask.repository.KPacRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/data/kpacs/", produces = MediaType.APPLICATION_JSON_VALUE)
public class KPacController {

    private static Logger logger = LoggerFactory.getLogger(KPacController.class);


    private KPacRepository repository;

    public KPacController(KPacRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<KPac> getKPacs() {
        logger.debug("Get all KPacs");
        return repository.getAll();
    }

    @GetMapping("{id}")
    public KPac getKPac(@PathVariable("id") int id) {
        logger.debug("Get KPac by id: {}", id);
        return repository.getById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteKPac(@PathVariable("id") int id) {
        logger.debug("Delete KPac by id: {}", id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KPac> createKPac(@RequestBody KPac kPac) {
        logger.debug("Create KPac with body: {}", kPac);
        KPac save = repository.save(kPac);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.getById(save.getId()));
    }
}
