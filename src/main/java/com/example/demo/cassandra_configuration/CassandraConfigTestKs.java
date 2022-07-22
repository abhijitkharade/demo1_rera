package com.example.demo.cassandra_configuration;

import com.datastax.driver.core.Cluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import javax.annotation.Resource;

@Configuration
@EnableCassandraRepositories(basePackages = "com.example.demo.repository.cassandra_repository", cassandraTemplateRef = "keyspaceSquareyardsCassandraTemplate")
@DependsOn("cassandraClusterCustom")
public class CassandraConfigTestKs {

    @Autowired
    CassandraConfig cassandraConfig;
    @Resource(name = "cluster" + "${test_ks.cluster}")
    Cluster cluster;

    private String keyspace = "test_ks";

    @Bean("keyspaceSquareyardsSession")
    public CassandraSessionFactoryBean session() {

        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster);
        session.setKeyspaceName(keyspace);
        session.setConverter(cassandraConfig.converter());
        session.setSchemaAction(SchemaAction.NONE);
        return session;
    }

    @Bean("keyspaceSquareyardsCassandraTemplate")
    public CassandraTemplate cassandraTemplate() {

        return new CassandraTemplate(session().getObject());
    }
}
