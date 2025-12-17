package com.salesforce.mini_crm.model;

import com.salesforce.mini_crm.enums.ClientStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;

	String name;

	String email;

	String phone;

	String company;

	ClientStatus status;


}
