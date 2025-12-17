package com.salesforce.mini_crm.dto.response;

public record OpportunityResponseDto(
		String name,
		String description,
		Double amount,
		String status,
		String expectedCloseDate
) { }
