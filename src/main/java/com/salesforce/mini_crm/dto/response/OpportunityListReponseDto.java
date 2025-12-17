package com.salesforce.mini_crm.dto.response;

import com.salesforce.mini_crm.model.Opportunity;

import java.util.List;

public record OpportunityListReponseDto(
		List<OpportunityResponseDto> opportunityList,
		int totalOpportunities
) { }
