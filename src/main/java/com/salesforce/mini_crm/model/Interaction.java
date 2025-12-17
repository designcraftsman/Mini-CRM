package com.salesforce.mini_crm.model;

import com.salesforce.mini_crm.enums.InteractionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Interaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	private InteractionType interactionType;

	private LocalDate interactionDate;

	private String comment;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "opportunity_id")
	private Opportunity opportunity;

}
