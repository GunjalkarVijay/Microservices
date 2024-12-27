package Telecom.SubscriptionService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import Telecom.SubscriptionService.dto.UserDto;
import Telecom.SubscriptionService.feign.SupportService;
import Telecom.SubscriptionService.model.User;
import Telecom.SubscriptionService.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	SupportService supportService;
	
	private RestTemplate restTemplate;
	
	public UserService(RestTemplateBuilder builder) {
		restTemplate = builder.build();
	}

	public List<User> getAll() {
		return repo.findAll();
	}

	public User getById(Long id) {
		return repo.findById(id).orElse(null);
	}

	public User getByName(String name) {
		return repo.findByName(name);
	}

	public void update(Long id, UserDto dto) {
		User user = repo.findById(id).orElse(null);
		if(user!=null) {
			user.setAccount(dto.getAccount());
			user.setAddress(dto.getAddress());
			user.setContact(dto.getContact());
			user.setEmail(dto.getEmail());
			user.setName(dto.getName());
			user.setSubscriptionList(dto.getSubscriptionList());
			repo.save(user);
		}
	}

	public void add(UserDto dto) {
		User user = new User();
		user.setAccount(dto.getAccount());
		user.setAddress(dto.getAddress());
		user.setContact(dto.getContact());
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setSubscriptionList(dto.getSubscriptionList());
		repo.save(user);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public User getByEmail(String email) {
		return repo.findByEmail(email);
	}

	public List<Object> getAllTickets(Long userId) {
//		String userServiceUrl = "http://localhost:8083/ticket/userId/";
//		ResponseEntity<List<Object>>  response = restTemplate.exchange(userServiceUrl + "/" + userId, HttpMethod.GET,null,new ParameterizedTypeReference<List<Object>>() {});
//		return response.getBody();
		return supportService.getTicketByUserId(userId);
	}

}
