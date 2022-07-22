package com.example.demo.cassandra_configuration;

import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.LatencyAwarePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.util.List;

@Configuration
public class CassandraConfig {

    @Value("${cassandra.contact-points}")
    private String contactPoints;
    @Value("${cassandra.port}")
    private Integer port;
    @Value("${cassandra.username}")
    private String username;
    @Value("${cassandra.password}")
    private String password;
    @Value("${cassandra.clusters}")
    private List<String> clusters;
    @Autowired
    private GenericWebApplicationContext context;

    @Bean
    public String cassandraClusterCustom() {

        clusters.forEach(cluster -> {
            String[] x = cluster.split("-");
            String beanId = x[0];
            String dataCenter = x[1];
            context.registerBean("cluster" + beanId, CassandraClusterFactoryBean.class, () -> getCassandraClusterFactoryBean(dataCenter));
        });
        return "";
    }

    public CassandraClusterFactoryBean getCassandraClusterFactoryBean(String dc) {

        LatencyAwarePolicy loadBalancingPolicy = LatencyAwarePolicy.builder(DCAwareRoundRobinPolicy.builder().withLocalDc(dc).build()).build();
        CassandraClusterFactoryBean cassandraClusterFactoryBean = new CassandraClusterFactoryBean();
        cassandraClusterFactoryBean.setContactPoints(contactPoints);
        cassandraClusterFactoryBean.setPort(port);
        cassandraClusterFactoryBean.setUsername(username);
        cassandraClusterFactoryBean.setPassword(password);
        cassandraClusterFactoryBean.setJmxReportingEnabled(false);
        cassandraClusterFactoryBean.setLoadBalancingPolicy(loadBalancingPolicy);
        return cassandraClusterFactoryBean;
    }

    public CassandraMappingContext mappingContext() {

        return new CassandraMappingContext();
    }

    public CassandraConverter converter() {

        return new MappingCassandraConverter(mappingContext());
    }
}
