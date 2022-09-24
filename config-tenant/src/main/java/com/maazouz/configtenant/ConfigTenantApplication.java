package com.maazouz.configtenant;

import com.maazouz.configtenant.MultiTenant.Config.Domain.DataSource;
import com.maazouz.configtenant.MultiTenant.Config.Domain.DataSourceRepository;
import com.maazouz.configtenant.MultiTenant.Config.Domain.SecurityAESEncoder;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SpringBootApplication
public class ConfigTenantApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigTenantApplication.class, args);
    }

    @Autowired
    private DataSourceRepository configRepo;

    @Value("${decoder.key}")
    String keyDecoder;
    @Value("${prefixTenant}")
    String prefix;

    String AutoCreate="?createDatabaseIfNotExist=true";


    @Bean
    public void AutoDDLConfig() {
        List<DataSource> configTenants = configRepo.findAll();
        // configTenants.stream().forEach(item->System.out.println(item));
        if (configTenants.isEmpty()) {
            return;
        }


        for (DataSource tenant : configTenants) {

            System.out.println(tenant.toString());

            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(Objects.requireNonNull(SecurityAESEncoder.decrypt(tenant.getDriverClassName(), keyDecoder)));
            dataSource.setUrl(SecurityAESEncoder.decrypt(tenant.getUrl(),keyDecoder)+"/"+ prefix +tenant.getName()+AutoCreate);
            dataSource.setUsername(SecurityAESEncoder.decrypt(tenant.getUsername(),keyDecoder));
            dataSource.setPassword(SecurityAESEncoder.decrypt(tenant.getPassword(),keyDecoder));

            LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
            emfBean.setDataSource(dataSource);
            emfBean.setPackagesToScan("com.*"); // Here mention JPA entity path / u can leave it scans all packages

            emfBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            emfBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
            Map<String, Object> properties = new HashMap<>();
            properties.put("hibernate.default_schema", tenant.getName());
            properties.put("hibernate.hbm2ddl.auto", "update");
            emfBean.setJpaPropertyMap(properties);
            emfBean.setPersistenceUnitName(String.valueOf(dataSource));

            emfBean.afterPropertiesSet();

        }

    }

}
