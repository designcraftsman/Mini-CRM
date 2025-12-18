package com.salesforce.mini_crm.service;

import com.salesforce.mini_crm.dto.request.AddOpportunityDto;
import com.salesforce.mini_crm.dto.response.OpportunityResponseDto;
import com.salesforce.mini_crm.model.Client;
import com.salesforce.mini_crm.model.Opportunity;
import com.salesforce.mini_crm.repository.ClientRepository;
import com.salesforce.mini_crm.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OpportunityService {

	@Autowired
	private OpportunityRepository opportunityRepository;

	@Autowired
	private ClientRepository clientRepository;

	public OpportunityResponseDto addOpportunity(AddOpportunityDto addOpportunityDto) {
		if(addOpportunityDto==null) {
			return null;
		}
		Client client = clientRepository.findById(addOpportunityDto.clientId()).orElse(null);

		Opportunity opportunity = new Opportunity();
		opportunity.setClient(client);
		opportunity.setName(addOpportunityDto.name());
		opportunity.setEstimatedAmount(addOpportunityDto.estimatedAmount());
		opportunity.setExpectedCloseDate(LocalDate.parse(addOpportunityDto.expectedCloseDate()));
		opportunity.setStatus(addOpportunityDto.status());

		Opportunity savedOpportunity = opportunityRepository.save(opportunity);

		return new OpportunityResponseDto(
				savedOpportunity.getClient(),
				savedOpportunity.getName(),
				savedOpportunity.getEstimatedAmount(),
				savedOpportunity.getStatus(),
				savedOpportunity.getExpectedCloseDate().toString()
		);
	}

	public OpportunityResponseDto getOpportunityById(Long id) {
		Optional<Opportunity> opportunityOpt = opportunityRepository.findById(id);
		if(opportunityOpt.isEmpty()) {
			return null;
		}
		Opportunity opportunity = opportunityOpt.get();
		return new OpportunityResponseDto(
				opportunity.getClient(),
				opportunity.getName(),
				opportunity.getEstimatedAmount(),
				opportunity.getStatus(),
				opportunity.getExpectedCloseDate().toString()
		);
	}

	public void deleteOpportunityById(Long id) {
		opportunityRepository.deleteById(id);
	}

	public OpportunityResponseDto updateOpportunity(Long id, AddOpportunityDto addOpportunityDto) {
		Optional<Opportunity> opportunityOpt = opportunityRepository.findById(id);
		if(opportunityOpt.isEmpty() || addOpportunityDto==null) {
			return null;
		}
		Opportunity opportunity = opportunityOpt.get();

		Client client = clientRepository.findById(addOpportunityDto.clientId()).orElse(null);

		opportunity.setClient(client);
		opportunity.setName(addOpportunityDto.name());
		opportunity.setEstimatedAmount(addOpportunityDto.estimatedAmount());
		opportunity.setExpectedCloseDate(LocalDate.parse(addOpportunityDto.expectedCloseDate()));
		opportunity.setStatus(addOpportunityDto.status());

		Opportunity savedOpportunity = opportunityRepository.save(opportunity);

		return new OpportunityResponseDto(
				savedOpportunity.getClient(),
				savedOpportunity.getName(),
				savedOpportunity.getEstimatedAmount(),
				savedOpportunity.getStatus(),
				savedOpportunity.getExpectedCloseDate().toString()
		);
	}

}
