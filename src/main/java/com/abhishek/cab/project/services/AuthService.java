package com.abhishek.cab.project.services;

import com.abhishek.cab.project.dto.DriverDto;
import com.abhishek.cab.project.dto.SignupDto;
import com.abhishek.cab.project.dto.UserDto;

public interface AuthService {
    String[] login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId , String vehicleId);

	String refreshToken(String refreshToken);

}
