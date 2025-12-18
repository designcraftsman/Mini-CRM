package com.salesforce.mini_crm.dto.request;

import com.salesforce.mini_crm.enums.InteractionType;

public record AddInteractionDto (
	InteractionType interactionType,
	String notes,
	String comment,
	Long opportunityId,
	Long clientId
){ }
