package org.example.models;

public class BlackListElement {
    private final int blacklistid;
    private final int clientid;
    private final String reason;
    public BlackListElement(int blacklistid, int clientid, String reason){
        this.blacklistid = blacklistid;
        this.clientid = clientid;
        this.reason = reason;
    }
    public int getBlacklistid(){
        return blacklistid;
    }
    public int getClientid(){
        return clientid;
    }
    public String getReason(){
        return reason;
    }

    @Override
    public String toString() {
        return "BlackList{" +
                "id=" + blacklistid +
                ", client_id='" + clientid + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
