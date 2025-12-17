package com.salesforce.mini_crm.dto.request;

public record UpdateOpportunityDto(
		Long opportunityId,
		String name,
		String description,
		Double amount,
		String status,
		String expectedCloseDate
) { }
