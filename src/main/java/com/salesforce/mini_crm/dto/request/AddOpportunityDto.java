package com.salesforce.mini_crm.dto.request;

public record AddOpportunityDto(
		String name,
		String description,
		Double estimatedAmount,
		String status,
		String expectedCloseDate,
		Long clientId
) { }
