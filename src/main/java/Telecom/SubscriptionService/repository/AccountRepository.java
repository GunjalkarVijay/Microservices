package Telecom.SubscriptionService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Telecom.SubscriptionService.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
