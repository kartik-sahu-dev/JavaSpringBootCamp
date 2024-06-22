package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionPerson() {
		return new PersonV1("Kartik Sahu");
	}

	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionPerson() {
		PersonV2 p =  new PersonV2(new Name("Kartik","Sahu"));
  		return p;
	}

	@GetMapping(path="/person",params="version=1")
	public PersonV1 getFirstVersionPersonParameterRequest() {
		return new PersonV1("Kartik Sahu");
	}

	@GetMapping(path="/person",params="version=2")
	public PersonV2 getSecondVersionPersonParameterRequest() {
		return new PersonV2(new Name("Kartik","Sahu"));
  		
	}


	@GetMapping(path="/person",headers="X-API-VERSION=1")
	public PersonV1 getFirstVersionPersonRequestHeaders() {
		return new PersonV1("Kartik Sahu");
	}

	@GetMapping(path="/person",headers="X-API-VERSION=2")
	public PersonV2 getSecondVersionPersonRequestHeaders() {
		return new PersonV2(new Name("Kartik","Sahu"));
  	}

	@GetMapping(path="/person",produces="application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionPersonAcceptHeaders() {
		return new PersonV1("Kartik Sahu");
	}

	@GetMapping(path="/person",produces="application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionPersonAcceptHeaders() {
		return new PersonV2(new Name("Kartik","Sahu"));
  	}

	
}
