package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionRepository questionRepository;

    @GetMapping("/question/list")
    @ResponseBody
    public String list() {
        List<Question> questions = questionRepository.findAll();

        String questionList = questions
                .stream()
                .map(q -> "<li>%d / %s</li>".formatted(q.getId(), q.getSubject()))
                .collect(Collectors.joining("\n"));

        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset='utf-8'>
                    <title>질문 목록</title>
                </head>
                <body>
                    <ul>
                        %s
                    <ul>
                </body>
                </html>
                """.formatted(questionList);
    }
}