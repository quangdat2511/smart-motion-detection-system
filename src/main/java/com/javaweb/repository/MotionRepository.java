package com.javaweb.repository;

import com.javaweb.entity.MotionEntity;
import com.javaweb.repository.custom.MotionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MotionRepository extends JpaRepository<MotionEntity,Long>, MotionRepositoryCustom { ;
}
