package Telecom.SubscriptionService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {

	private int price;
	private String planName;
	private String planDetails;
	private Long userId;
}
