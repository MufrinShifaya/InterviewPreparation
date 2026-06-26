package org.example.careercompassai.service;

import lombok.RequiredArgsConstructor;
import org.example.careercompassai.dto.AiRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final RestClient restClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    private String askGemini(String prompt) {

        Map<String, Object> body = Map.of(
                "contents",
                new Object[]{
                        Map.of(
                                "parts",
                                new Object[]{
                                        Map.of("text", prompt)
                                }
                        )
                }
        );

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

        return restClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .body(String.class);
    }

    // Companies based on Location
    public String getCompanies(AiRequest request) {

        String prompt = """
                You are an IT Career Assistant.

                User selected location:
                %s

                List the top IT companies in this location.

                For each company provide:
                - Company Name
                - Short Description

                Return only plain text.
                """.formatted(request.getLocation());

        return askGemini(prompt);
    }

    // Roles based on Company
    public String getRoles(AiRequest request) {

        String prompt = """
                Company:
                %s

                List all software and IT roles available in this company.

                Return only role names as a list.
                """.formatted(request.getCompany());

        return askGemini(prompt);
    }

    // Role Details
    public String getRoleDetails(AiRequest request) {

        String prompt = """
                Company:
                %s

                Role:
                %s

                Provide:

                1. Job Description

                2. Required Skills

                3. Salary Range in India

                4. Eligibility

                Return in clean readable format.
                """.formatted(
                request.getCompany(),
                request.getRole());

        return askGemini(prompt);
    }

    // Interview Rounds
    public String getInterviewRounds(AiRequest request) {

        String prompt = """
                Company:
                %s

                Role:
                %s

                Explain the complete interview process.

                Mention each round with explanation.

                Return in points.
                """.formatted(
                request.getCompany(),
                request.getRole());

        return askGemini(prompt);
    }

    // Interview Questions
    public String getInterviewQuestions(AiRequest request) {

        String prompt = """
                Company:
                %s

                Role:
                %s

                Generate 10 interview questions.

                Return only questions.
                """.formatted(
                request.getCompany(),
                request.getRole());

        return askGemini(prompt);
    }

    // Roadmap
    public String getRoadmap(AiRequest request) {

        String prompt = """
                Company:
                %s

                Role:
                %s

                Give a complete learning roadmap.

                Include:

                - Skills
                - Technologies
                - Learning Order
                - Practice Plan

                Return in steps.
                """.formatted(
                request.getCompany(),
                request.getRole());

        return askGemini(prompt);
    }

    // Resume Tips
    public String getResumeTips(AiRequest request) {

        String prompt = """
                Company:
                %s

                Role:
                %s

                Give resume tips to get shortlisted.

                Mention:

                - Skills
                - Projects
                - Resume Summary
                - Certifications

                Return in bullet points.
                """.formatted(
                request.getCompany(),
                request.getRole());

        return askGemini(prompt);
    }

    // Project Ideas
    public String getProjects(AiRequest request) {

        String prompt = """
                Company:
                %s

                Role:
                %s

                Suggest 5 impressive projects suitable for this role.

                For each project provide:

                - Project Name
                - Description
                - Technologies Used

                Return in clean format.
                """.formatted(
                request.getCompany(),
                request.getRole());

        return askGemini(prompt);
    }
}