package com.spring.hello.mapper;

import com.spring.hello.model.Person;
import org.apache.ibatis.annotations.Param;

/**
 * Person Mapper
 * Created by ustc-lezg on 05/01/2017.
 */

public interface PersonMapper {

    Person findPerson(int id);
}
