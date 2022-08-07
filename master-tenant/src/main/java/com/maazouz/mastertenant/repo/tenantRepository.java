package com.maazouz.mastertenant.repo;

import com.maazouz.mastertenant.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tenantRepository extends JpaRepository<Tenant,Long> {
}
