package com.example.springboot12.pack;

public class DevProfile implements SystemProfile {
    @Override

    public String getProfile() {
        return "Current profile is dev";
    }
}
