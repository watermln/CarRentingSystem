package server.business;

public class Review {
    private String reviewID;
    private String customerID;
    private String reviewText;
    private int rating; // e.g., 1 to 5 stars

    public Review(String reviewID, String customerID, String reviewText, int rating) {
        this.reviewID = reviewID;
        this.customerID = customerID;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public String getReviewID() {
        return reviewID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
