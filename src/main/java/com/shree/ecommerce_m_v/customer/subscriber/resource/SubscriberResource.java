package com.shree.ecommerce_m_v.customer.subscriber.resource;

import com.shree.ecommerce_m_v.customer.subscriber.model.dto.SubscriberDTO;
import com.shree.ecommerce_m_v.customer.subscriber.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriber")
public class SubscriberResource {

    @Autowired
    private SubscriberService subscriberService;

    @GetMapping
    public ResponseEntity<List<SubscriberDTO>> getSubscribers(@RequestHeader(value = "Authorization",required = false) String Authorization){
        return ResponseEntity.status(HttpStatus.OK)
                .body(subscriberService.getAllSubscribers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<SubscriberDTO> getSubscriberByEmail(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                              @RequestParam(value = "email",required = true) String email){
        return ResponseEntity.status(HttpStatus.OK)
                .body(subscriberService.getSubscriberWithEmail(email));
    }

    @DeleteMapping("/{subscriberId}")
    public ResponseEntity<String> deleteSubscriber(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                   @PathVariable Long subscriberId){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(subscriberService.deleteSubscriber(subscriberId));
    }

    @GetMapping("/{subscriberId}")
    public ResponseEntity<SubscriberDTO> getSubscriberById(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                           @PathVariable("subscriberId") final Long subscriberId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(subscriberService.getSubscriberById(subscriberId));
    }
}
