package com.salesforce.mini_crm.dto.request;

public record UpdateClientStatusDto(
		Long clientId,
		String status
)
{ }
