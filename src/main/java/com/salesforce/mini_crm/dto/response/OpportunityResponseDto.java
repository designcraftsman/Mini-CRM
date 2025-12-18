package com.salesforce.mini_crm.dto.response;

import com.salesforce.mini_crm.enums.OpportunityStatus;
import com.salesforce.mini_crm.model.Client;

public record OpportunityResponseDto(
		Client client,
		String name,
		Double amount,
		OpportunityStatus status,
		String expectedCloseDate
) { }
