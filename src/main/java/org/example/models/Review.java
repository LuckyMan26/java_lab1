package org.example.models;

public class Review {
    private final int reviewid;
    private final int clientid;
    private final String text;
    private final int goodId;
    private final int stars;
    public Review(int reviewid, int clientid,int good_id, String text, int stars){
        this.reviewid = reviewid;
        this.clientid = clientid;
        this.text = text;
        this.goodId = good_id;
        this.stars = stars;
    }
    public int getGoodId(){
        return goodId;
    }
    public int getClientid(){
        return clientid;
    }
    public String getText(){
        return text;
    }

    public int getReviewid(){
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
