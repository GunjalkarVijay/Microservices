package Telecom.SubscriptionService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@Service
@FeignClient(name = "billing-service")
public interface BillingService {

	@GetMapping("/Invoice/{subscriptionId}")
    public Object getInvoiceBySubscriptionId(@RequestParam Long subscriptionId);
}
