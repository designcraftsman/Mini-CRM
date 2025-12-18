package com.salesforce.mini_crm.service;

import com.salesforce.mini_crm.dto.request.AddInteractionDto;
import com.salesforce.mini_crm.dto.response.ClientResponseDto;
import com.salesforce.mini_crm.dto.response.InteractionListResponseDto;
import com.salesforce.mini_crm.dto.response.InteractionResponseDto;
import com.salesforce.mini_crm.model.Client;
import com.salesforce.mini_crm.model.Interaction;
import com.salesforce.mini_crm.model.Opportunity;
import com.salesforce.mini_crm.repository.ClientRepository;
import com.salesforce.mini_crm.repository.InteractionRepository;
import com.salesforce.mini_crm.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

	public InteractionListResponseDto getClientInteractions(Long clientId) {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + clientId));

		Optional<Interaction> clientInteractions = interactionRepository.findById(clientId);
		if (clientInteractions.isEmpty()) {
			throw new IllegalArgumentException("No interactions found for client with id: " + clientId);
		}

		List<InteractionResponseDto> interactionDtos = clientInteractions.stream()
				.map(interaction -> new InteractionResponseDto(
						interaction.getInteractionType(),
						interaction.getInteractionDate().toString(),
						interaction.getComment(),
						interaction.getClient().getId(),
						interaction.getOpportunity() != null ? interaction.getOpportunity().getId() : null
				))
				.toList();

		return new InteractionListResponseDto(
				interactionDtos,
				interactionDtos.size()
		);
	}

	public void deleteAllInteractions(Long clientId) {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + clientId));

		Optional<Interaction> interactions = interactionRepository.findByClient(client);

		interactions.ifPresent(interactionRepository::delete);
	}

	public InteractionResponseDto getOpportunityById(Long interactionId) {
		Interaction interaction = interactionRepository.findById(interactionId)
				.orElseThrow(() -> new IllegalArgumentException("Interaction not found with id: " + interactionId));
		return new InteractionResponseDto(
			interaction.getInteractionType(),
				interaction.getInteractionDate().toString(),
				interaction.getComment(),
				interaction.getClient().getId(),
				interaction.getOpportunity().getId()
		);
	}

	public void deleteInteractionById(Long interactionId) {
		Interaction interaction = interactionRepository.findById(interactionId)
				.orElseThrow(() -> new IllegalArgumentException("Interaction not found with id: " + interactionId));
		interactionRepository.delete(interaction);
	}

	public List<InteractionResponseDto> getAllInteractions() {
		List<Interaction> interactions = interactionRepository.findAll();
		return interactions.stream()
				.map(interaction -> new InteractionResponseDto(
						interaction.getInteractionType(),
						interaction.getInteractionDate().toString(),
						interaction.getComment(),
						interaction.getClient().getId(),
						interaction.getOpportunity() != null ? interaction.getOpportunity().getId() : null
				))
				.toList();
	}

	public List<InteractionResponseDto> getInteractionsByClientId(Long clientId) {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + clientId));
		List<Interaction> interactions = interactionRepository.findAll().stream()
				.filter(interaction -> interaction.getClient().getId().equals(clientId))
				.toList();
		return interactions.stream()
				.map(interaction -> new InteractionResponseDto(
						interaction.getInteractionType(),
						interaction.getInteractionDate().toString(),
						interaction.getComment(),
						interaction.getClient().getId(),
						interaction.getOpportunity() != null ? interaction.getOpportunity().getId() : null
				))
				.toList();
	}
}
