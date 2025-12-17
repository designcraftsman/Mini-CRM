package com.salesforce.mini_crm.service;

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
}
