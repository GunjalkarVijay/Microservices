package Telecom.SubscriptionService.dto;

import Telecom.SubscriptionService.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

	private User user;
	private String details;
	private String balance;
}
