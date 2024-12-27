package Telecom.SubscriptionService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import Telecom.SubscriptionService.dto.AccountDto;
import Telecom.SubscriptionService.dto.ResponseMessage;
import Telecom.SubscriptionService.dto.SubscriptionDto;
import Telecom.SubscriptionService.model.Account;
import Telecom.SubscriptionService.model.Subscription;
import Telecom.SubscriptionService.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService service;
	
	@GetMapping()
	public List<Account> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public Account getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/userId/{userId}")
	public Account getAllByUserId(@PathVariable Long userId){
		return service.getAllByUserId(userId);
	}
	
	@PutMapping("/{id}")
	public Account update(@PathVariable Long id, @RequestBody AccountDto dto) {
		return service.update(id, dto);
//		return new ResponseMessage("Account Updated Successfully");
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseMessage add(@RequestBody AccountDto dto) {
		service.add(dto);
		return new ResponseMessage("Account Created Successfully");
	}
	
	@DeleteMapping("/{id}")
	public ResponseMessage delete(@PathVariable Long id) {
		service.delete(id);
		return new ResponseMessage("Account Deleted Successfully");
	}
}
