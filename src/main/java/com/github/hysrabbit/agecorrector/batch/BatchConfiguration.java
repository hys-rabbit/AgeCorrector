package com.github.hysrabbit.agecorrector.batch;

import com.github.hysrabbit.agecorrector.mybatis.entity.Person;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {

    public final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    private final SqlSessionFactory sqlSessionFactory;

    private final CorrectAgeProcessor correctAgeProcessor;

    @Bean
    public MyBatisCursorItemReader<Person> reader() {
        return new MyBatisCursorItemReaderBuilder<Person>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.github.hysrabbit.agecorrector.mybatis.mapper.PersonMapper.findByBirthday")
                .build();
    }

    @Bean
    public MyBatisBatchItemWriter<Person> writer() {
        return new MyBatisBatchItemWriterBuilder<Person>()
                .sqlSessionFactory(sqlSessionFactory)
                .statementId("com.github.hysrabbit.agecorrector.mybatis.mapper.PersonMapper.save")
                .build();
    }

    @Bean
    public Job correctAge(JobListener jobListener, Step step) {
        return jobBuilderFactory.get("correctAge")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step(ItemReader<Person> reader, ItemWriter<Person> writer) {
        return stepBuilderFactory.get("step")
                .<Person, Person> chunk(1)
                .reader(reader)
                .processor(correctAgeProcessor)
                .writer(writer)
                .build();
    }

}
