package com.mysite.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    // 변수명 매핑,
    // 그리고 해당 질문에 대한 답변들을 삭제함
    // 근데 보통 칼럼에는 리스트를 받지는 않기 때문에 실제로 테이블에 생성되지 않음.
    @OneToMany(
            mappedBy = "question",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Answer> answers = new ArrayList<>();

    public Answer addAnswer(String content) {
        Answer answer = new Answer();
        answer.setContent("네 자동으로 생성됩니다.");
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(this);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
        answers.add(answer);

        return answer;
    }
}