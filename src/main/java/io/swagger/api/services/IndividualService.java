package io.swagger.api.services;

import io.swagger.annotations.Api;
import io.swagger.api.ApiException;
import io.swagger.api.constants.ApiErrorCodes;
import io.swagger.api.repositories.IndividualRepository;
import io.swagger.model.db.IndividualEntity;
import io.swagger.model.db.mappers.IndividualEntityMappingBuilder;
import io.swagger.model.dto.Individual;
import io.swagger.model.dto.IndividualCreate;
import io.swagger.model.dto.IndividualUpdate;
import io.swagger.model.dto.mappers.IndividualMapper;
import io.swagger.model.interfaces.IIndividual;
import lombok.Getter;
import lombok.NonNull;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
public class IndividualService {
    @Getter
    private static final IndividualMapper individualMapper = Mappers.getMapper(IndividualMapper.class);

    @Getter static final IndividualEntityMappingBuilder individualEntityMapper = IndividualEntityMappingBuilder.getInstance();


    private IndividualRepository individualRepository;

    @Autowired
    public IndividualService(IndividualRepository individualRepository){
        this.individualRepository = individualRepository;
    }

    public List<Individual> listAll(PageRequest pageRequest){
        List<IndividualEntity> findAll = individualRepository.findAll(pageRequest).getContent();
        return individualMapper.toItems( findAll );
    }
    public List<Individual> listAll(){
        List<IndividualEntity> findAll = individualRepository.findAll();
        return individualMapper.toItems( findAll );
    }



    public Individual create(@NonNull IndividualCreate individual) throws ApiException{
        IndividualEntity entity = individualRepository.findByPk(individual.getId());
        if(entity != null){
            throw new ApiException(ApiErrorCodes.ERROR_EXIST.getCode(),ApiErrorCodes.ERROR_EXIST.getInfo());
        }
        entity = new IndividualEntity(individual.getId(),individual.getFullName());
        individualEntityMapper.merge(individual,entity);
        return individualMapper.toItem(individualRepository.saveAndFlush(entity));
    }

    public Individual update(@NonNull String id,@NonNull IndividualUpdate individual) throws ApiException{
        IndividualEntity entity = individualRepository.findByPk(id);
        if(entity == null){
            throw new ApiException(ApiErrorCodes.ERROR_NOT_FOUND.getCode(),ApiErrorCodes.ERROR_NOT_FOUND.getInfo());
        }
        individualEntityMapper.merge(individual,entity);
        return individualMapper.toItem(individualRepository.saveAndFlush(entity));
    }

    public Individual delete (@NonNull String id) throws ApiException{
        IndividualEntity entity = individualRepository.findByPk(id);
        if(entity == null){
            throw new ApiException(ApiErrorCodes.ERROR_NOT_FOUND.getCode(),ApiErrorCodes.ERROR_NOT_FOUND.getInfo());
        }
        individualRepository.delete(id);
        return individualMapper.toItem( entity);

    }

    public Individual findById(@NonNull String id){
        IndividualEntity entity = individualRepository.findByPk(id);
        return individualMapper.toItem(entity);
    }


}
