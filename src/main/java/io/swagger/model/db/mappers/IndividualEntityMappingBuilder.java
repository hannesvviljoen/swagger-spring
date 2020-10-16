package io.swagger.model.db.mappers;

import io.swagger.model.db.IndividualEntity;
import io.swagger.model.dto.Individual;
import io.swagger.model.interfaces.IIndividual;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class IndividualEntityMappingBuilder extends AbstractMappingBuilder {

    private static IndividualEntityMappingBuilder INSTANCE;
    private   IndividualEntityMappingBuilder(){
        modelMapper.createTypeMap(IIndividual.class , IndividualEntity.class);
    }

    public static IndividualEntityMappingBuilder getInstance(){
        if(INSTANCE == null){
            INSTANCE = new IndividualEntityMappingBuilder();
        }
        return INSTANCE;
    }

}
