package com.salesforce.mini_crm.dto.response;

public record InteractionResponseDto (
		String type,
		String date,
		String notes,
		Long clientId,
		Long opportunityId
) { }
