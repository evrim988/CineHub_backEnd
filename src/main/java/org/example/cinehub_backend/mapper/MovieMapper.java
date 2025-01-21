package org.example.cinehub_backend.mapper;

import org.example.cinehub_backend.dto.response.BaseResponse;
import org.example.cinehub_backend.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    BaseResponse<Movie> toBaseResponse(Movie movie);


}