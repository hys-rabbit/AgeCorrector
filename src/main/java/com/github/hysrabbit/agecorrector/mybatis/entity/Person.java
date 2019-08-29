package com.github.hysrabbit.agecorrector.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Person {
    Integer id;
    /** 名前 */
    String name;
    /** 年齢 */
    Integer age;
    /** 生年月日 */
    LocalDate birthday;
}
