package com.example.springSecurity.models;

public class LoginResponseDto {
    private ApplicationUser applicationUser;
    private String jwt;

    public LoginResponseDto(ApplicationUser applicationUser, String jwt) {
        this.applicationUser = applicationUser;
        this.jwt = jwt;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "LoginResponseDto{" +
                "applicationUser=" + applicationUser +
                ", jwt='" + jwt + '\'' +
                '}';
    }
}
