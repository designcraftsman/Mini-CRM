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
	long id;

	InteractionType interactionType;

	LocalDate interactionDate;

	String commentaire;

	@ManyToOne
	@JoinColumn(name = "opportunity_id")
	Opportunity opportunity;

}
