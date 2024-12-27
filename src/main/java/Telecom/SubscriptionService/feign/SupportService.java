package Telecom.SubscriptionService.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;


@Service
@FeignClient(name = "support-service")
public interface SupportService {

	@GetMapping("/ticket/userId/{userId}")
    public List<Object> getTicketByUserId(@PathVariable Long userId);
}
