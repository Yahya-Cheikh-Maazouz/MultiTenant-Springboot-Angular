package com.maazouz.configtenant.MultiTenant.Config.Domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSource, Long> {
    DataSource findByName(String name);
}
