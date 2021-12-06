package com.craffic.batch.config;

import com.craffic.batch.domain.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;


@Configuration
public class CsvBatchJobConfig {
    /**
     * JobBuilderFactory用来构建Job
     */
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    /**
     * StepBuilderFactory用来构建Step
     */
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    /**
     * Datasource用来支持持久化操作
     */
    private DataSource dataSource;

    /**
     * 配置一个ItemReader
     * FlagFileItemReader是一个加载普通文件的itemReader
     * 在FlagFileItemReader配置过程中，由于csv的第一行是标题，因此跳过第一行
     * 通过setResource设置data.csv的位置，new ClassPathResource("data.csv")可以读取到classpath下的文件
     * 通过setLineMapper设置每一行的数据信息
     * setNames设置了data.csv文件共有4列：id,userName,address,gender
     * setDelimiter设置了猎户列之间的间隔符
     * @return
     */
    @Bean
    @StepScope
    FlatFileItemReader<User> itemReader(){
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("data.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<User>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames("id", "username", "address", "gender");
                setDelimiter("\t");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<User>(){{
                setTargetType(User.class);
            }});
        }});
        return reader;
    }

    /**
     * 配置ItemWriter，即数据的写入逻辑
     * JdbcBatchItemWriter是通过JDBC将数据写到一个关系型数据库中
     * JdbcBatchItemWriter主要配置了数据以及数据插入SQL，注意占位符的写法是:属性名
     * 最后通过BeanPropertyItemSqlParameterSourceProvider实例将实体类的属性和SQL中的占位符一一映射
     */
    @Bean
    JdbcBatchItemWriter jdbcBatchItemWriter(){
        JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("insert into BATCH_USER(id,username,address,gender) " +
                "values(:id,:username,:address,:gender)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
        return writer;
    }

    /**
     * 配置一个step
     * step通过stepBuilderFactory
     * get的参数就是该Step的name
     * 然后调用chunk()的参数为2，表示美肤祛两条数据就执行一次write操作
     * 最后分别配置reader和writer
     * @return
     */
    @Bean
    Step csvStep(){
        return stepBuilderFactory.get("csvStep").<User, User>chunk(2)
                .reader(itemReader())
                .writer(jdbcBatchItemWriter())
                .build();
    }

    /**
     * 通过jobBuilderFactory构建一个job
     * get的参数就是job的name
     * 最后配置该job的step
     * @return
     */
    @Bean
    Job csvJob(){
        return jobBuilderFactory.get("csvJob")
                .start(csvStep())
                .build();
    }

}
