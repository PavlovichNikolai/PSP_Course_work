package entities;

public class Branch {
    private int b_id;
    private String name;
    private String city;
    private String street;
    private String house;
    private String begin;
    private String end;

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) { this.b_id = b_id; }

    public String getBranch() {
        return name;
    }

    public void setBranch(String name) {
        this.name = name;
    }

    public String getCity() { return city; }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() { return street; }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String login) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Branch(int b_id, String name, String city, String street, String house, String begin, String end) {
        this.b_id = b_id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.house = house;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString(){
        return b_id+" "+name+" "+city+" "+street+" "+house+" "+begin+" "+end;
    }
}
