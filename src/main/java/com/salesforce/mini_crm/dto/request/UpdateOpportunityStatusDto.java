package com.salesforce.mini_crm.dto.request;

public record UpdateOpportunityStatusDto(
	long opportunityId,
	String status
) { }
