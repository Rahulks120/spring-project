package com.ivoyant.spring_project.service;

import com.ivoyant.spring_project.dao.FeedbackDAO;
import com.ivoyant.spring_project.model.EmployeeFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackDAO feedbackDAO;

    public void addFeedback(EmployeeFeedback feedback) {
        feedbackDAO.addFeedback(feedback);
    }

    public List<EmployeeFeedback> getAllFeedback() {
        return feedbackDAO.getAllFeedback();
    }

    public List<EmployeeFeedback> getFeedbackByTopic(String topic) {
        return feedbackDAO.getFeedbackByTopic(topic);
    }
}
