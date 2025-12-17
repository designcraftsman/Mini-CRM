package com.salesforce.mini_crm.dto.request;

public record AddClientDto(
		String name,
		String email,
		String phoneNumber,
		String companyName,
		String status
) { }
