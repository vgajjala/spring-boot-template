package com.vinay.spring.boot.template.config.mapper;

import com.vinay.spring.boot.template.dto.MyPojoDto;
import com.vinay.spring.boot.template.model.MyModel;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyModelMapper {
    MyModel toMyModel(MyPojoDto myPojoDto);
    MyPojoDto toMyPojoDto(MyModel myPojoDto);
}
