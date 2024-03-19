package org.example;

public class Client {
    private final int cliendId;
    private final String name;
    private final String email;
    private final String address;
    public Client(int cliendId, String name, String email, String address){
        this.cliendId = cliendId;
        this.name = name;
        this.email = email;
        this.address = address;
    }
    int getCliendId(){
        return cliendId;
    }
    String getName(){
        return name;
    }
    String getEmail(){
        return email;
    }
    String getAddress(){
        return address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + cliendId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +

                '}';
    }
}
