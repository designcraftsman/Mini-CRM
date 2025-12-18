package com.salesforce.mini_crm.dto.request;

import com.salesforce.mini_crm.enums.ClientStatus;

public record UpdateClientDto(
		String name,
		String email,
		String phone,
		String company,
		ClientStatus status
) { }
