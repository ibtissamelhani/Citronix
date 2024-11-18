package org.youcode.citronix.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.citronix.domain.entities.Field;

import java.util.UUID;

public interface FieldRepository extends JpaRepository<Field, UUID> {
    Page<Field> findAllByFarmId(UUID farmId, Pageable pageable);

}
