package org.youcode.citronix.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.citronix.domain.entities.Tree;

import java.util.UUID;

public interface TreeRepository extends JpaRepository<Tree, UUID> {

    long countByFieldId(UUID fieldId);
    Page<Tree> findAllByFieldId(UUID fieldId, Pageable pageable);

}
