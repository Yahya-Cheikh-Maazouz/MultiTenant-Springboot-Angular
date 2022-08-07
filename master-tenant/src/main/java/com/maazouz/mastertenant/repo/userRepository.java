package com.maazouz.mastertenant.repo;


import com.maazouz.mastertenant.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<user,Long> {
}
