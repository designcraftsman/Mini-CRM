package com.salesforce.mini_crm.dto.request;

public record UpdateClientDto(
		String name,
		String email,
		String phoneNumber,
		String companyName,
		String status
) { }
