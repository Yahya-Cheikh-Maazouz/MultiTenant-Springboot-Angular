package com.maazouz.configtenant.MultiTenant.Config.DataSource;

import com.maazouz.configtenant.MultiTenant.Config.Domain.DataSource;
import com.maazouz.configtenant.MultiTenant.Config.Domain.DataSourceRepository;
import com.maazouz.configtenant.MultiTenant.Config.Domain.SecurityAESEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TenantDataSource implements Serializable {

    private final DataSourceRepository configRepo;
    private  HashMap<String, javax.sql.DataSource> dataSources = new HashMap<>();



    @Value("${decoder.key}")
    String keyDecoder;
    @Value("${prefixTenant}")
    String prefix;
    String AutoCreate="?createDatabaseIfNotExist=true";

    public javax.sql.DataSource getDataSource(String name) {
        if (dataSources.get(name) != null) {
            return dataSources.get(name);
        }
        javax.sql.DataSource dataSource = createDataSource(name);

        if (dataSource != null) {
            dataSources.put(name, dataSource);
        }
        return dataSource;
    }

    @PostConstruct
    public Map<String, javax.sql.DataSource> getAll() {
        List<DataSource> configList = configRepo.findAll();
        Map<String, javax.sql.DataSource> result = new HashMap<>();
        for (DataSource config : configList) {
            javax.sql.DataSource dataSource = getDataSource(config.getName());
            result.put(config.getName(), dataSource);
        }
        return result;
    }


    public javax.sql.DataSource createDataSource(String name) {
        DataSource config = configRepo.findByName(name);
        if (config != null) {

            return DataSourceBuilder
                    .create()
                    .driverClassName(SecurityAESEncoder.decrypt(config.getDriverClassName(),keyDecoder))
                    .username(SecurityAESEncoder.decrypt(config.getUsername(),keyDecoder))
                    .password(SecurityAESEncoder.decrypt(config.getPassword(),keyDecoder))
                    .url(SecurityAESEncoder.decrypt(config.getUrl(),keyDecoder)+"/"+ prefix +config.getName()+AutoCreate)
                    .build();
        }
        return null;
    }

}
