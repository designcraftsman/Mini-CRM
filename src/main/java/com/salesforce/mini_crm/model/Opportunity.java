package com.salesforce.mini_crm.model;


import com.salesforce.mini_crm.enums.OpportunityStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Opportunity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	String name;

	double estimatedAmount;

	OpportunityStatus status;

	LocalDate expectedCloseDate;

	@ManyToOne
	@JoinColumn(name = "client_id")
	Client client;

}
