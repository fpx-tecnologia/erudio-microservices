package br.com.erudio.services;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.Model.Person;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PersonRepository  personRepository;
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public Person findById(Long id) {
		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}
	
	public List<Person> findAll(){
		return personRepository.findAll();
	}
	
	public Person create(Person person) {
		return personRepository.save(person);
	}
	
	public Person update(Person person) {
		var entity = personRepository.findById(person.getId())
		.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		personRepository.save(entity);
		return entity;
	}
	
	public void delete(Long id) {

		var entity = personRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		personRepository.delete(entity);
		
	}
	
	
	

}
