package com.app.RentWheelz.repositories;

import com.app.RentWheelz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByUserName(String username);
    User findByMobileNumber(String mobileNumber);
}
