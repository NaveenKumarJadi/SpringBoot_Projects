package com.naveen.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.naveen.auth.entities.ForgotPassword;
import com.naveen.auth.entities.User;

import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {

    @Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.user = ?2")
    Optional<com.naveen.auth.entities.ForgotPassword> findByOtpAndUser(Integer otp, User user);
}
