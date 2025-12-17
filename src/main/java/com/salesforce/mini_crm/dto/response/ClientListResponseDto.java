package com.salesforce.mini_crm.dto.response;

import java.util.List;

public record ClientListResponseDto(
	List<ClientListResponseDto> clientListResponseDto,
	int totalClients
) { }
