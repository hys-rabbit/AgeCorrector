package com.github.hysrabbit.agecorrector.mybatis.mapper;

import com.github.hysrabbit.agecorrector.mybatis.entity.Person;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PersonMapper {

    public List<Person> findByBirthday(LocalDate today);

    public Integer save(Person person);

}
