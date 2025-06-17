package com.mysite.sbb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class QuestionRepositoryTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	@DisplayName("findAll")
	void t1() {
		List<Question> questions = questionRepository.findAll();
		assertThat(questions).hasSize(2);

		Question q = questions.get(0);
		assertThat(q.getSubject()).isEqualTo("sbb가 무엇인가요?");
	}

	@Test
	@DisplayName("findById test")
	void t2() {
		Question question = questionRepository.findById(1).get();
		assertThat(question.getSubject()).isEqualTo("sbb가 무엇인가요?");
	}

	@Test
	@DisplayName("findBySubject test")
	void t3() {
		Question question = questionRepository.findBySubject("sbb가 무엇인가요?").get();

		assertThat(question.getId()).isEqualTo(1);
	}

	@Test
	@DisplayName("findBySubject test")
	void t4() {
		Question question = questionRepository.findBySubjectAndContent(
				"sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다."
		).get();

		assertThat(question.getId()).isEqualTo(1);
	}

	@Test
	@DisplayName("findBySubjectLike test")
	void t5() {
		List<Question> questions = questionRepository.findBySubjectLike("sbb%");
		assertThat(questions).hasSize(1);

		Question question = questions.get(0);
		assertThat(question.getSubject()).isEqualTo("sbb가 무엇인가요?");
	}

	@Test
	@DisplayName("수정")
	void t0() { // 이러면 오류가 발생 (테스트가 함수의 이름 순으로 실행됨)
		Question question = questionRepository.findById(1).get();
		assertThat(question).isNotNull();

		question.setSubject("수정된 제목");
		questionRepository.save(question);

		Question foundQuestion = questionRepository.findBySubject("수정된 제목").get();
		assertThat(foundQuestion).isNotNull();
	}
}
