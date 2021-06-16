package com.example.fetch_json_internet;
//{
//        "id": 41,
//        "first_name": "Darnall",
//        "last_name": "Stirman",
//        "email": "dstirman14@hhs.gov",
//        "gender": "Female",
//        "ip_address": "23.187.34.128",
//        "avatar": "https://robohash.org/dolordelenitiut.png?size=50x50&set=set1"
//        }
public class Hero_data {
    String first_name, last_name, image_url, email, gender, ip_address;

    public  Hero_data(String first_name, String last_name, String image_url, String email, String gender, String ip_address){
        this.first_name = first_name;
        this.last_name = last_name;
        this.image_url = image_url;
        this.email = email;
        this.gender = gender;
        this.ip_address = ip_address;
    }

    public String getFirst_name(){
        return first_name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getIp_address() {
        return ip_address;
    }
}
