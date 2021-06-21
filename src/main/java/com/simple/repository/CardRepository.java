package com.simple.repository;

import com.simple.entity.Card;
import liquibase.pro.packaged.C;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByCardAccountId(Long cardAccountId);
}
