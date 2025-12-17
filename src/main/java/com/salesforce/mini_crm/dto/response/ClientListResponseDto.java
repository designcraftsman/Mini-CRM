package com.salesforce.mini_crm.dto.response;

import java.util.ArrayList;
import java.util.List;

public record ClientListResponseDto(
		List<ClientResponseDto> clients,
		int totalClients
) { }
