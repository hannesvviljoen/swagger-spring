package io.swagger.api.repositories;

import io.swagger.model.db.IndividualEntity;
import io.swagger.model.dto.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividualRepository extends JpaRepository<IndividualEntity,String> {
    IndividualEntity findByPk(String pk);

}
