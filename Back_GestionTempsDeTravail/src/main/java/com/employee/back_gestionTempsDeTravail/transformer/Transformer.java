package com.employee.back_gestionTempsDeTravail.transformer;

import java.util.List;

public interface Transformer<DTO, Entity> {

    DTO fromEntityToDto(Entity entity);
    Entity fromDtoToEntity(DTO dto);
    List<DTO> fromEntityListToDtoList(List<Entity> entities);
    List<Entity> fromDtoListToEntityList(List<DTO> dtoList);
    
}
