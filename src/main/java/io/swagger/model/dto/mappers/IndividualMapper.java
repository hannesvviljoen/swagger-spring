package io.swagger.model.dto.mappers;

import io.swagger.model.db.IndividualEntity;
import io.swagger.model.dto.Individual;
import org.hibernate.validator.constraints.NotBlank;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.threeten.bp.OffsetDateTime;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IndividualMapper {

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "href", target = "href")
    @Mapping(source = "aristocraticTitle", target = "aristocraticTitle")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "countryOfBirth", target = "countryOfBirth")
    @Mapping(source = "deathDate", target = "deathDate")
    @Mapping(source = "familyName", target = "familyName")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "baseType", target = "baseType")
    @Mapping(source = "schemaLocation", target = "schemaLocation")
    @Mapping(source = "type", target = "type")
    Individual toItem(IndividualEntity individualEntity);
    List<Individual> toItems(List<IndividualEntity> individualEntities);

}

