package com.br.varsolutions.infraestructure.persistence;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.core.env.Environment;
    import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
    import org.springframework.jdbc.datasource.DriverManagerDataSource;
    import org.springframework.orm.jpa.JpaTransactionManager;
    import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
    import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
    import org.springframework.transaction.PlatformTransactionManager;
    import org.springframework.transaction.annotation.EnableTransactionManagement;

    import javax.persistence.EntityManagerFactory;
    import javax.sql.DataSource;
    import java.util.Properties;

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(basePackages = {"com.br.varsolutions.domain.repositories"})
    public class HibernateConfig {

        @Autowired
        private Environment env;

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
            LocalContainerEntityManagerFactoryBean en = new LocalContainerEntityManagerFactoryBean();
            en.setDataSource(dataSource());
            en.setPackagesToScan((new String[]{"com.br.varsolutions.domain.entities"}));
            en.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            en.setJpaProperties(additionalProperties());

            return en;
        }


        @Bean
        public DataSource dataSource(){
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
            dataSource.setUrl(env.getProperty("spring.datasource.url"));
            dataSource.setUsername(env.getProperty("spring.datasource.username"));
            dataSource.setPassword(env.getProperty("spring.datasource.password"));

            return dataSource;
        }

        @Bean
        public PlatformTransactionManager transactionManager(EntityManagerFactory enf){
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(enf);

            return transactionManager;
        }

        private Properties additionalProperties(){
            Properties properties = new Properties();
            properties.setProperty("hibernate.dialect",env.getProperty("spring.jpa.database-platform"));
            properties.setProperty("hibernate.show_sql",env.getProperty("spring.jpa.show-sql"));
            properties.setProperty("hibernate.hbm2dd.auto",env.getProperty("spring.jpa.hibernate.ddl-auto"));

            return properties;
        }
    }
