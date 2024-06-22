package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "person") // Specify the root name for the JSON object
public class PersonV2 {
	
	 @JsonProperty("name") // Map the field to a different JSON key
	private Name name;

	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "PersonV2 [name=" + name + "]";
	}
}
