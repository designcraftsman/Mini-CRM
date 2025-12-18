package com.salesforce.mini_crm.repository;

import com.salesforce.mini_crm.model.Client;
import com.salesforce.mini_crm.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
	Optional<Interaction> findByClient(Client client);
}
