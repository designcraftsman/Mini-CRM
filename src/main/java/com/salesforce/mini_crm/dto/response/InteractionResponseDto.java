package com.salesforce.mini_crm.dto.response;

import com.salesforce.mini_crm.enums.InteractionType;

public record InteractionResponseDto (
		InteractionType type,
		String date,
		String comment,
		Long clientId,
		Long opportunityId
) { }
