package io.swagger.model.db.mappers;

import io.swagger.model.db.interfaces.IPrimaryKey;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractMappingBuilder {

    protected ModelMapper modelMapper = new ModelMapper();

    public AbstractMappingBuilder() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
    }

    public ModelMapper getMapper() {
        return modelMapper;
    }

    public <T> T convert(Object entity, Class<T> destinationClass) {
        T MapperObject = getMapper().map(entity, destinationClass);
        return MapperObject;
    }

    public <S, D> D map(final S entity, Class<D> outClass) {
        return (getMapper()).map(entity, outClass);
    }

    public <S, D> List<D> convertAll(final Collection<S> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    public Object merge(Object source, Object destination) {
        modelMapper.map(source, destination);
        return destination;
    }

    public <D, T> List<D> mergeAll(Collection<T> source, Collection<D> destination) {
        return destination.stream()
                .map(entity -> {
                    Optional<T> objectSource = source.stream().filter(a -> ((IPrimaryKey) a).getPk().equals(((IPrimaryKey) entity).getPk())).findFirst();
                    if(objectSource.isPresent()) {
                        return (D) merge(objectSource.get(), entity);
                    }else{
                        return entity;
                    }
                })
                .collect(Collectors.toList());
    }

}
