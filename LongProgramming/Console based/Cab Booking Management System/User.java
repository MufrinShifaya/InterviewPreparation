public class User {
    private String name;
    private int userId;
    private long phonenumber;
    private String pickuploc;
    private String destinationloc;
    private int distance;
public User(){

}
    User(String name, int userId, long phonenumber, String pickuploc, String destinationloc,int distance){this.name=name;
    this.userId=userId;
    this.phonenumber=phonenumber;
    this.pickuploc=pickuploc;
    this.destinationloc=destinationloc;
    this.distance=distance;
}

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public int getuserId() {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }

    public long getphonenumber() {
        return phonenumber;
    }

    public void setphonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getpickuploc() {
        return pickuploc;
    }

    public void setpickuploc(String pickuploc) {
        this.pickuploc = pickuploc;
    }

    public String getdestinationloc() {
        return destinationloc;
    }

    public void setdestinationloc(String destinationloc) {
        this.destinationloc = destinationloc;
    }

    public int getdistance() {
        return distance;
    }

    public void setdistance(int distance) {
        this.distance = distance;
    }
}

