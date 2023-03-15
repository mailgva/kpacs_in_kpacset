package com.horbatenko.testtask.repository;

import com.horbatenko.testtask.model.KPac;
import com.horbatenko.testtask.model.KPacSet;

import java.util.List;

public interface KPacSetRepository {
    KPacSet save(KPacSet kPacSet);

    KPacSet getById(int id);

    boolean deleteById(int id);

    List<KPacSet> getAll();
}