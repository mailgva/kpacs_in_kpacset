package com.horbatenko.testtask.repository;

import com.horbatenko.testtask.model.KPac;

import java.util.List;

public interface KPacRepository {
    KPac save(KPac kPac);
    KPac getById(int id);
    boolean deleteById(int id);
    List<KPac> getAll();
}
