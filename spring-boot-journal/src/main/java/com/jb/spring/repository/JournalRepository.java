package com.jb.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jb.spring.domain.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long> {

}
