package com.horbatenko.testtask.repository;


import com.horbatenko.testtask.model.KPac;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        com.horbatenko.testtask.config.JdbcConfig.class,
        com.horbatenko.testtask.config.WebConfig.class,
        com.horbatenko.testtask.config.WebAppInitializer.class}
 )
@WebAppConfiguration
@Transactional
class KPacRepositoryTest {

    @Autowired
    KPacRepository repository;

    public static final KPac NEW_KPAC = new KPac(null, "test111", "descr111", LocalDate.now());


    @Test
    void save() {
        String newTitle = "NEW TITLE " + (int)(Math.random() * 10000);
        KPac save = repository.save(new KPac(null, "test111", "descr111", LocalDate.now()));
        save.setTitle(newTitle);
        repository.save(save);
        assertTrue(repository.getAll().stream().anyMatch(k -> k.getTitle().equals(newTitle)));
    }

    @Test
    void getById() {
        KPac kPac = repository.save(NEW_KPAC);
        assertNotNull(repository.getById(kPac.getId()));
    }

    @Test
    void deleteById() {
        KPac kPac = repository.save(NEW_KPAC);
        assertNotNull(repository.deleteById(kPac.getId()));
        assertNull(repository.getById(kPac.getId()));
    }

    @Test
    void getAll() {
        int size = repository.getAll().size();
        repository.save(NEW_KPAC);
        assertEquals(size+1, repository.getAll().size());
    }
}