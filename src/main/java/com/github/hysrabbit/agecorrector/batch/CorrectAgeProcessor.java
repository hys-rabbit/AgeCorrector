package com.github.hysrabbit.agecorrector.batch;

import com.github.hysrabbit.agecorrector.mybatis.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class CorrectAgeProcessor implements ItemProcessor<Person, Person> {
    @Override
    public Person process(Person person) throws Exception {
        log.info("Correct {}.", person.getName());
        return new Person(
                person.getId(),
                person.getName(),
                LocalDate.now().getYear() - person.getBirthday().getYear(),
                person.getBirthday());
    }
}
