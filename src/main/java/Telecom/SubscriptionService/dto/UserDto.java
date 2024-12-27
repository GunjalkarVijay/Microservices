package Telecom.SubscriptionService.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import Telecom.SubscriptionService.model.Account;
import Telecom.SubscriptionService.model.Subscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String name;
	private String email;
	private BigInteger contact;
	private String address;
	private Account account;
	private List<Subscription> subscriptionList = new ArrayList<>();
}
