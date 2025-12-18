package com.salesforce.mini_crm.service;

import com.salesforce.mini_crm.dto.request.AddInteractionDto;
import com.salesforce.mini_crm.dto.response.ClientResponseDto;
import com.salesforce.mini_crm.dto.response.InteractionResponseDto;
import com.salesforce.mini_crm.model.Client;
import com.salesforce.mini_crm.model.Interaction;
import com.salesforce.mini_crm.model.Opportunity;
import com.salesforce.mini_crm.repository.ClientRepository;
import com.salesforce.mini_crm.repository.InteractionRepository;
import com.salesforce.mini_crm.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteractionService {

	@Autowired
	private InteractionRepository interactionRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private OpportunityRepository opportunityRepository;

	public InteractionResponseDto addInteraction(AddInteractionDto addInteractionDto) {

		if(addInteractionDto == null) {
			throw new IllegalArgumentException("AddInteractionDto cannot be null");
		}

		Interaction interaction = new Interaction();

		Client client = clientRepository.findById(addInteractionDto.clientId())
				.orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + addInteractionDto.clientId()));

		interaction.setInteractionType(addInteractionDto.interactionType());
		interaction.setComment(addInteractionDto.comment());
		interaction.setClient(client);
		if(addInteractionDto.opportunityId() != null) {
			Opportunity opportunity = opportunityRepository.findById(addInteractionDto.opportunityId())
					.orElseThrow(() -> new IllegalArgumentException("Opportunity not found with id: " + addInteractionDto.opportunityId()));
			interaction.setOpportunity(opportunity);
		}

		Interaction savedInteraction = interactionRepository.save(interaction);

		return new InteractionResponseDto(
				savedInteraction.getInteractionType(),
				savedInteraction.getInteractionDate().toString(),
				savedInteraction.getComment(),
				savedInteraction.getClient().getId(),
				savedInteraction.getOpportunity() != null ? savedInteraction.getOpportunity().getId() : null
		);
	}

}
