package com.salesforce.mini_crm.dto.request;

import com.salesforce.mini_crm.enums.OpportunityStatus;
import com.salesforce.mini_crm.model.Opportunity;

public record AddOpportunityDto(
		String name,
		Double estimatedAmount,
		OpportunityStatus status,
		String expectedCloseDate,
		Long clientId
) { }
