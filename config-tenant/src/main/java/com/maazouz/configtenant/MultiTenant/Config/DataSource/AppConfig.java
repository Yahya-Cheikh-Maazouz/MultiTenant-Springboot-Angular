package com.maazouz.configtenant.MultiTenant.Config.DataSource;

import com.maazouz.configtenant.MultiTenant.Config.Domain.DataSource;
import com.maazouz.configtenant.MultiTenant.Config.Domain.DataSourceRepository;
import com.maazouz.configtenant.MultiTenant.Interceptor.RequestInterceptor;
import com.maazouz.configtenant.MultiTenant.Config.Domain.SecurityAESEncoder;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Configuration
public class AppConfig implements WebMvcConfigurer {





    @Bean
    public RequestInterceptor GetRequestInterceptor() {
        return new RequestInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(GetRequestInterceptor());
    }



}
