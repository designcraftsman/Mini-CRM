package com.salesforce.mini_crm.dto.response;

public record ClientResponseDto(
	String name,
	String email,
	String phoneNumber,
	String companyName,
	String status
) { }
