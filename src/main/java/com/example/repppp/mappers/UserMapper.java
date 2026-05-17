package com.example.repppp.mappers;

import com.example.repppp.models.AppUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    AppUser findById(@Param("id") Long id);

    AppUser findByUsername(@Param("username") String username);

    int insert(AppUser user);

    int updateReputation(
            @Param("id") Long id,
            @Param("reputation") int reputation
    );
}