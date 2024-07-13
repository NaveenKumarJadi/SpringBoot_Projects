package com.naveen.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naveen.dto.StudentOrder;

public interface StudentOrderRepo extends JpaRepository<StudentOrder, Integer> {

	public StudentOrder findByRazorPayOrderId(String razorPayOrderId);
	
}
