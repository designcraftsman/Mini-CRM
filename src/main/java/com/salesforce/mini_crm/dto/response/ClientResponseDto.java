package com.salesforce.mini_crm.dto.response;

import com.salesforce.mini_crm.enums.ClientStatus;

public record ClientResponseDto(
	String name,
	String email,
	String phoneNumber,
	String companyName,
	ClientStatus status
) { }
