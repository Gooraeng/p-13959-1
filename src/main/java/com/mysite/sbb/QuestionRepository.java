package com.mysite.sbb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Optional<Question> findBySubject(String s);

    Optional<Question> findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subjectLike);
}
