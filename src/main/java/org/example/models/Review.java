package org.example.models;

public class Review {
    private final Long reviewid;
    private final Long clientid;
    private final String text;
    private final Long goodId;
    private final int stars;
    public Review(Long reviewid, Long clientid,Long good_id, String text, int stars){
        this.reviewid = reviewid;
        this.clientid = clientid;
        this.text = text;
        this.goodId = good_id;
        this.stars = stars;
    }
    public Long getGoodId(){
        return goodId;
    }
    public Long getClientid(){
        return clientid;
    }
    public String getText(){
        return text;
    }

    public Long getReviewid(){
        return reviewid;
    }
    public int getStars(){
        return stars;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + reviewid +
                ", client_id='" + clientid + '\'' +
                ", text='" + text + '\'' +
                ", goodId='" + goodId + '\'' +
                '}';
    }
}
