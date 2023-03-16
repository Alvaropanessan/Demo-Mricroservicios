package com.example.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import models.Rating;
import models.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRatings",
            threadPoolKey = "userRatingInfoPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")

            }
    )
    public UserRatings getUserRatings(@PathVariable("userId") String userId) {
        UserRatings userRatings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId, UserRatings.class);
        return userRatings;
    }
    public UserRatings getFallbackUserRatings(@PathVariable("userId") String userId){
        UserRatings userRatings = new UserRatings();
        userRatings.setUserId(userId);
        userRatings.setUserRating(Arrays.asList(new Rating("0",0)));
        return userRatings;
    }
}
