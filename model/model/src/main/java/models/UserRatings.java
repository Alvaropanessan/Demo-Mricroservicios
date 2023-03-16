package models;

import java.util.Arrays;
import java.util.List;

public class UserRatings {

    private  String userId;
    private List<Rating> userRating;

    public UserRatings() {
    }

    public UserRatings(String userId, List<Rating> userRating) {
        this.userId= userId;
        this.userRating = userRating;
    }

    public List<Rating> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserRatings initData(String userId){
        UserRatings userRatings = new UserRatings();
        userRatings.setUserId(userId);
        userRatings.setUserRating(Arrays.asList(
                new Rating("100",3),
                new Rating("200",4)
        ));
        return userRatings;
    }
}
