package com.salesforce.mini_crm.repository;

import com.salesforce.mini_crm.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {
}
