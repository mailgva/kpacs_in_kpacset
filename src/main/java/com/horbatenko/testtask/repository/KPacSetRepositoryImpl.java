package com.horbatenko.testtask.repository;

import com.horbatenko.testtask.model.KPac;
import com.horbatenko.testtask.model.KPacSet;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class KPacSetRepositoryImpl implements KPacSetRepository {

    private static final RowMapper<KPacSet> ROW_MAPPER = BeanPropertyRowMapper.newInstance(KPacSet.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insert;

    public KPacSetRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insert = new SimpleJdbcInsert(dataSource)
                .withTableName("k_pac_set")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    @Override
    public KPacSet save(KPacSet kPacSet) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", kPacSet.getId())
                .addValue("title", kPacSet.getTitle());

        if (kPacSet.getId() == null) {
            Number newId = insert.executeAndReturnKey(map);
            kPacSet.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE k_pac_set " +
                            "   SET title=:title " +
                            "WHERE id=:id"
                    , map) == 0) {
                return null;
            }
        }
        saveKPacs(kPacSet);
        return kPacSet;
    }

    @Override
    public KPacSet getById(int id) {
        String sql = "SELECT * FROM k_pac_set WHERE id = ?";
        KPacSet kPacSet = (KPacSet) jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper(KPacSet.class));
        getKPacsByKPacSet(kPacSet);
        return kPacSet;
    }

    @Transactional
    @Override
    public boolean deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM k_pac_set WHERE id=?", id) != 0;
    }

    @Override
    public List<KPacSet> getAll() {
        String sql = "SELECT * FROM k_pac_set";
        List<KPacSet> kPacsSet = jdbcTemplate.query(sql, ROW_MAPPER);
        kPacsSet.forEach(kPacSet ->
                getKPacsByKPacSet(kPacSet)
        );
        return kPacsSet;
    }

    private void getKPacsByKPacSet(KPacSet kPacSet) {
        String sql = "SELECT k_pac.* " +
                "FROM k_pac_set_k_pacs " +
                "JOIN k_pac ON k_pac_set_k_pacs.id_k_pac = k_pac.id " +
                "WHERE k_pac_set_k_pacs.id_k_pac_set = ?";
        List<KPac> kPacs = jdbcTemplate.query(sql, new Object[] {kPacSet.getId()},
                BeanPropertyRowMapper.newInstance(KPac.class));
        kPacSet.setKPacs(new HashSet<>(kPacs));
    }

    private void saveKPacs(KPacSet kPacSet) {
        deleteKPacsBySetId(kPacSet.getId());
        insertKPacsToKPacSet(kPacSet);
    }

    private void deleteKPacsBySetId(int id_k_pac_set) {
        String deleteQuery = "DELETE FROM k_pac_set_k_pacs WHERE id_k_pac_set = ?";
        jdbcTemplate.update(deleteQuery, id_k_pac_set);
    }

    private void insertKPacsToKPacSet(KPacSet kPacSet) {
        String insertQuery = "INSERT INTO k_pac_set_k_pacs (id_k_pac_set, id_k_pac) VALUES (?,?)";
        kPacSet.getKPacs().forEach(kPac -> {
                    Object[] args = new Object[] {kPacSet.getId(), kPac.getId()};
                    jdbcTemplate.update(insertQuery, args);
                }
        );
    }

}
