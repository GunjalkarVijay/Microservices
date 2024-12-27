package Telecom.SubscriptionService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Telecom.SubscriptionService.dto.AccountDto;
import Telecom.SubscriptionService.model.Account;
import Telecom.SubscriptionService.model.User;
import Telecom.SubscriptionService.repository.AccountRepository;
import Telecom.SubscriptionService.repository.UserRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository repo;

	@Autowired
	UserRepository userRepo;


	public List<Account> getAll() {
		return repo.findAll();
	}

	public Account getById(Long id) {
		return repo.findById(id).orElse(null);
	}

	public Account getAllByUserId(Long userId) {
		User user = userRepo.findById(userId).orElse(null);
		if(user!=null) {
			return user.getAccount();
		}
		return null;
	}

	public Account update(Long id, AccountDto dto) {
		//		Account acc = repo.findById(id).orElse(null);
		//		if(acc!=null) {
		//			acc.setBalance(dto.getBalance());
		//			acc.setDetails(dto.getDetails());
		//			acc.setUser(dto.getUser());
		//			repo.save(acc);
		//		}
		Account account = repo.findById(id).orElse(null);
		assert account != null;
		if(dto.getUser()!=null) { account.setUser(dto.getUser()); }
		if(dto.getBalance()!=null) { account.setBalance(dto.getBalance()); }
		if(dto.getDetails()!=null) { account.setDetails(dto.getDetails()); }
		return repo.save(account);
	}

	public void add(AccountDto dto) {
		Account acc = new Account();
		acc.setBalance(dto.getBalance());
		acc.setDetails(dto.getDetails());
		acc.setUser(dto.getUser());
		repo.save(acc);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

}
