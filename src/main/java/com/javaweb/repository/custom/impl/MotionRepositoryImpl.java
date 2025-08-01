package com.javaweb.repository.custom.impl;

import com.javaweb.entity.MotionEntity;
import com.javaweb.model.dto.MotionDTO;
import com.javaweb.repository.custom.MotionRepositoryCustom;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MotionRepositoryImpl implements MotionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    public List<MotionEntity> findAll(MotionDTO motionDTO) {
        StringBuilder sql = new StringBuilder("SELECT distinct m.* FROM motion as m ");
        sql.append(" ORDER BY m.time DESC");
        Pageable pageable = PageRequest.of(motionDTO.getPage() - 1, motionDTO.getMaxPageItems());
        sql.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        Query query = entityManager.createNativeQuery(sql.toString(), MotionEntity.class);
        return query.getResultList();
    }
    @Override
    public int countTotalItems() {
        String sql = "SELECT * FROM motion";
        Query query = entityManager.createNativeQuery(sql.toString());
        return query.getResultList().size();
    }
}
