package com.ivoyant.spring_project.controller;

import com.ivoyant.spring_project.model.EmployeeFeedback;
import com.ivoyant.spring_project.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/")
    public String showFeedbackForm(Model model) {
        model.addAttribute("employeeFeedback", new EmployeeFeedback());
        return "feedbackForm";
    }

    @PostMapping("/submitFeedback")
    public String submitFeedback(@ModelAttribute("employeeFeedback") EmployeeFeedback feedback) {
        feedbackService.addFeedback(feedback);
        return "redirect:/feedbacks";
    }

    @GetMapping("/feedbacks")
    public String viewAllFeedback(Model model) {
        List<EmployeeFeedback> feedbackList = feedbackService.getAllFeedback();
        model.addAttribute("feedbackList", feedbackList);
        return "feedbackList";
    }

    @GetMapping("/search")
    public String searchFeedbackByTopic(@RequestParam("topic") String topic, Model model) {
        List<EmployeeFeedback> topicFeedbackList = feedbackService.getFeedbackByTopic(topic);
        model.addAttribute("feedbackList", topicFeedbackList);
        return "feedbackList";
    }
}
