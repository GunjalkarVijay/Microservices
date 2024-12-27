package Telecom.SubscriptionService.controller;

import java.util.*;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import Telecom.SubscriptionService.dto.AccountDto;
import Telecom.SubscriptionService.dto.ResponseMessage;
import Telecom.SubscriptionService.dto.UserDto;
import Telecom.SubscriptionService.model.Account;
import Telecom.SubscriptionService.model.User;
import Telecom.SubscriptionService.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping()
	public List<User> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public User getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/name/{name}")
	public User getByName(@PathVariable String name){
		return service.getByName(name);
	}
	
	@GetMapping("/email/{email}")
	public User getByEmail(@PathVariable String email){
		return service.getByEmail(email);
	}
	
	@PutMapping("/{id}")
	public ResponseMessage update(@PathVariable Long id, @RequestBody UserDto dto) {
		service.update(id, dto);
		return new ResponseMessage("User Updated Successfully");
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseMessage add(@RequestBody UserDto dto) {
		service.add(dto);
		return new ResponseMessage("User Created Successfully");
	}
	
	@DeleteMapping("/{id}")
	public ResponseMessage delete(@PathVariable Long id) {
		service.delete(id);
		return new ResponseMessage("User Deleted Successfully");
	}
	
	@GetMapping("/tickets/{userId}")
	@HystrixCommand(fallbackMethod = "getUserTicketsFallback")
	public List<Object> getUserTickets(@PathVariable Long userId)throws NotFoundException{
		return service.getAllTickets(userId);
	}
	
	public List<Object> getUserTicketsFallback(@PathVariable Long userId) throws NotFoundException{
        return new ArrayList<>(Collections.singleton("Service not available"));
    }

}
