package com.horbatenko.testtask.repository;

import com.horbatenko.testtask.model.KPac;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class KPacRepositoryImpl implements KPacRepository {
    private static final RowMapper<KPac> ROW_MAPPER = BeanPropertyRowMapper.newInstance(KPac.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insert;


    public KPacRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate,
                              NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insert = new SimpleJdbcInsert(dataSource)
                .withTableName("k_pac")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    @Override
    public KPac save(KPac kPac) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", kPac.getId())
                .addValue("title", kPac.getTitle())
                .addValue("description", kPac.getDescription());

        if (kPac.getId() == null) {
            map.addValue("creation_date", LocalDate.now());
            Number newId = insert.executeAndReturnKey(map);
            kPac.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE k_pac " +
                            "   SET title=:title, description=:description " +
                            "WHERE id=:id"
                    , map) == 0) {
                return null;
            }
        }
        return kPac;
    }

    @Override
    public KPac getById(int id) {
        String sql = "SELECT * FROM k_pac WHERE id = ?";

        return (KPac) jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper(KPac.class));
    }


    @Transactional
    @Override
    public boolean deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM k_pac WHERE id=?", id) != 0;
    }

    @Override
    public List<KPac> getAll() {
        String sql = "SELECT * FROM k_pac";
        List<KPac> kPacs = jdbcTemplate.query(sql, ROW_MAPPER);
        return kPacs;
    }
}
