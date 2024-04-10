package org.example.models;

public class Review {
    private final int reviewid;
    private final int clientid;
    private final String text;
    private final int goodId;
    private final int stars;
    public Review(int blacklistid, int clientid,int good_id, String text, int stars){
        this.reviewid = blacklistid;
        this.clientid = clientid;
        this.text = text;
        this.goodId = good_id;
        this.stars = stars;
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
        return "BlackList{" +
                "id=" + reviewid +
                ", client_id='" + clientid + '\'' +
                ", text='" + text + '\'' +
                ", goodId='" + goodId + '\'' +
                '}';
    }
}
