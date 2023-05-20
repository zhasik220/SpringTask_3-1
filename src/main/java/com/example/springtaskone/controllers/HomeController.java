package com.example.springtaskone.controllers;

import com.example.springtaskone.models.Student;
import com.example.springtaskone.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "home";
    }

    @GetMapping(value = "/add_student")
    public String AddStudentPage() {
        return "add_student";
    }

    @PostMapping(value = "add_student")
    public String addStudent(Student student) {
        student.setMark(markLetter(student.getExam()));
        studentRepository.save(student);
        return "redirect:/";
    }

    private String markLetter(int score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 75 && score <= 89) {
            return "B";
        } else if (score >= 60 && score <= 74) {
            return "C";
        } else if (score >= 50 && score <= 59) {
            return "D";
        } else {
            return "F";
        }
    }
}
