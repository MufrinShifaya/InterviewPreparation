package org.example.careercompassai.controller;

import lombok.RequiredArgsConstructor;
import org.example.careercompassai.dto.AiRequest;
import org.example.careercompassai.service.GeminiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {

    private final GeminiService geminiService;

    @PostMapping("/companies")
    public String getCompanies(@RequestBody AiRequest request) {
        return geminiService.getCompanies(request);
    }

    @PostMapping("/roles")
    public String getRoles(@RequestBody AiRequest request) {
        return geminiService.getRoles(request);
    }

    @PostMapping("/details")
    public String getRoleDetails(@RequestBody AiRequest request) {
        return geminiService.getRoleDetails(request);
    }

    @PostMapping("/interview-rounds")
    public String getInterviewRounds(@RequestBody AiRequest request) {
        return geminiService.getInterviewRounds(request);
    }

    @PostMapping("/interview-questions")
    public String getInterviewQuestions(@RequestBody AiRequest request) {
        return geminiService.getInterviewQuestions(request);
    }

    @PostMapping("/roadmap")
    public String getRoadmap(@RequestBody AiRequest request) {
        return geminiService.getRoadmap(request);
    }

    @PostMapping("/resume")
    public String getResumeTips(@RequestBody AiRequest request) {
        return geminiService.getResumeTips(request);
    }

    @PostMapping("/projects")
    public String getProjects(@RequestBody AiRequest request) {
        return geminiService.getProjects(request);
    }
}