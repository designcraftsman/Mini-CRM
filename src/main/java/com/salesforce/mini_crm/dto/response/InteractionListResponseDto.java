package com.salesforce.mini_crm.dto.response;

import java.util.List;

public record InteractionListResponseDto(
		List<InteractionResponseDto> interactionList,
		int totalInteractions
) { }
