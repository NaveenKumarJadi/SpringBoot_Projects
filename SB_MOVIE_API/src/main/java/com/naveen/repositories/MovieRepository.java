package com.naveen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naveen.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
