package com.horbatenko.testtask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KPacSet {
    private Integer id;
    private String title;
    private Set<KPac> kPacs = new HashSet<>();
}
