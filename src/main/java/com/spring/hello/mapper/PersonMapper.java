package com.spring.hello.mapper;

import com.spring.hello.model.Person;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ustc-lezg on 05/01/2017.
 */
public interface PersonMapper {

    @Select("select * from person where id = #{id}")
    Person findPerson(@Param("id") int id);
}
