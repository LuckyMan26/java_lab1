package org.example;

public class BlackListElement {
    private final int blacklistid;
    private final int clientid;
    private final String reason;
    BlackListElement(int blacklistid, int clientid, String reason){
        this.blacklistid = blacklistid;
        this.clientid = clientid;
        this.reason = reason;
    }
    int getBlacklistid(){
        return blacklistid;
    }
    int getClientid(){
        return clientid;
    }
    String getReason(){
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
