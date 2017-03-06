package com.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.permission.core.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer>
												,JpaSpecificationExecutor<Organization>{

}
