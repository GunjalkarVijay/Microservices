package Telecom.SubscriptionService.controller;

import java.util.List;

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

import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import Telecom.SubscriptionService.dto.ResponseMessage;
import Telecom.SubscriptionService.dto.SubscriptionDto;
import Telecom.SubscriptionService.model.Subscription;
import Telecom.SubscriptionService.service.SubscriptionService;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

	@Autowired
	SubscriptionService service;
	
	@GetMapping()
	public List<Subscription> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public Subscription getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/userId/{userId}")
	public List<Subscription> getAllByUserId(@PathVariable Long userId){
		return service.getAllByUserId(userId);
	}
	
	@PutMapping("/{id}")
	public ResponseMessage update(@PathVariable Long id, @RequestBody SubscriptionDto dto) {
		service.update(id, dto);
		return new ResponseMessage("Subscription Updated Successfully");
	}
	
//	@PostMapping()
//	@ResponseStatus(HttpStatus.CREATED)
//	@HystrixCommand(fallbackMethod = "createSubscriptionFallback")
//	public ResponseMessage createSubscription(@RequestBody SubscriptionDto dto) throws InternalServerError, NotFoundException{
//		service.add(dto);
//		return new ResponseMessage("Subscription Created Successfully");
//	}
//	
//	public ResponseMessage createSubscriptionFallback(@RequestBody SubscriptionDto dto) {
////		service.add(dto);
//		return new ResponseMessage("Server Down");
//	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @HystrixCommand(fallbackMethod = "createSubscriptionFallback")
    public ResponseMessage createSubscription(@RequestBody SubscriptionDto subscriptionDto) throws InternalServerError, NotFoundException {
        service.add(subscriptionDto);
        return new ResponseMessage("Subscription Created Successfully");
    }

    public ResponseMessage createSubscriptionFallback(@RequestBody SubscriptionDto subscriptionDto) {
        return new ResponseMessage("Services not available");
    }
	
	@DeleteMapping("/{id}")
	public ResponseMessage delete(@PathVariable Long id) {
		service.delete(id);
		return new ResponseMessage("Subscription Deleted Successfully");
	}
}
