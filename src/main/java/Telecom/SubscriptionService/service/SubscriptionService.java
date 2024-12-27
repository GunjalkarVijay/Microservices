package Telecom.SubscriptionService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import Telecom.SubscriptionService.dto.SubscriptionDto;
import Telecom.SubscriptionService.feign.BillingService;
import Telecom.SubscriptionService.model.Subscription;
import Telecom.SubscriptionService.model.User;
import Telecom.SubscriptionService.repository.SubscriptionRepository;
import Telecom.SubscriptionService.repository.UserRepository;

@Service
public class SubscriptionService {

	@Autowired
	SubscriptionRepository repo;

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BillingService billingService;
	
	private RestTemplate restTemplate;
	
	public SubscriptionService(RestTemplateBuilder builder) {
		restTemplate = builder.build();
	}


	public List<Subscription> getAll() {
		return repo.findAll();
	}

	public Subscription getById(Long id) {
		return repo.findById(id).orElse(null);
	}

	public List<Subscription> getAllByUserId(Long userId) {
		User user = userRepo.findById(userId).orElse(null);
		if(user!=null) {
			return user.getSubscriptionList();
		}
		return null;
	}

	public void update(Long id, SubscriptionDto dto) {
		Subscription subs = repo.findById(id).orElse(null);
		if(subs!=null) {
			subs.setPlanDetails(dto.getPlanDetails());
			subs.setPlanName(dto.getPlanName());
			subs.setPrice(dto.getPrice());
			User user = userRepo.findById(dto.getUserId()).orElse(null);
			subs.setUser(user);
			repo.save(subs);
		}
	}

	public void add(SubscriptionDto dto) {
		Subscription subs = new Subscription();
		subs.setPlanDetails(dto.getPlanDetails());
		subs.setPlanName(dto.getPlanName());
		subs.setPrice(dto.getPrice());
		User user = userRepo.findById(dto.getUserId()).orElse(null);
		subs.setUser(user);
		repo.save(subs);
		if(user!=null) {
//			String url = "http://localhost:8082/Invoice/"+subs.getId();
//			restTemplate.getForEntity(url, Object.class);
			billingService.getInvoiceBySubscriptionId(subs.getId());
			
		}
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}



}
