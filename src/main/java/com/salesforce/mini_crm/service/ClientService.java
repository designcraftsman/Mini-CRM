package com.salesforce.mini_crm.service;

import com.salesforce.mini_crm.dto.request.AddClientDto;
import com.salesforce.mini_crm.dto.request.UpdateClientDto;
import com.salesforce.mini_crm.dto.response.ClientListResponseDto;
import com.salesforce.mini_crm.dto.response.ClientResponseDto;
import com.salesforce.mini_crm.model.Client;
import com.salesforce.mini_crm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public void deleteClient(Long clientId) {
		clientRepository.deleteById(clientId);
	}

	public ClientResponseDto getClientById(Long clientId) {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));

		ClientResponseDto clientResponseDto = new ClientResponseDto(
				client.getName(),
				client.getEmail(),
				client.getPhone(),
				client.getCompany(),
				client.getStatus()
		);
		return clientResponseDto;
	}

	public ClientListResponseDto getAllClients(){
		List<Client> clients = clientRepository.findAll();
		List<ClientResponseDto> clientList = new ArrayList<ClientResponseDto>();
		for(Client client : clients){
			ClientResponseDto clientResponseDto = new ClientResponseDto(
					client.getName(),
					client.getEmail(),
					client.getPhone(),
					client.getCompany(),
					client.getStatus()
			);
			clientList.add(clientResponseDto);
		}
		ClientListResponseDto clientListResponseDto = new ClientListResponseDto(clientList, clients.size());
		return clientListResponseDto;
	}

	public ClientResponseDto updateClient(Long clientId, UpdateClientDto updateClientDto) {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));

		client.setName(updateClientDto.name());
		client.setEmail(updateClientDto.email());
		client.setPhone(updateClientDto.phone());
		client.setCompany(updateClientDto.company());
		client.setStatus(updateClientDto.status());

		Client updatedClient = clientRepository.save(client);

		return new ClientResponseDto(
				updatedClient.getName(),
				updatedClient.getEmail(),
				updatedClient.getPhone(),
				updatedClient.getCompany(),
				updatedClient.getStatus()
		);
	}

	public ClientResponseDto addClient(AddClientDto addClientDto){
		Client client = new Client();
		client.setName(addClientDto.name());
		client.setEmail(addClientDto.email());
		client.setPhone(addClientDto.phone());
		client.setCompany(addClientDto.company());
		client.setStatus(addClientDto.status());

		Client createdClient = clientRepository.save(client);

		return new ClientResponseDto(
				createdClient.getName(),
				createdClient.getEmail(),
				createdClient.getPhone(),
				createdClient.getCompany(),
				createdClient.getStatus()
		);
	}
}
