package com.ivoyant.spring_project.service;

import com.ivoyant.spring_project.model.EmployeeFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addFeedback(EmployeeFeedback feedback) {
        try {
            String query = "INSERT INTO feedback (employee_name, topic, feedback) VALUES (?, ?, ?)";
            jdbcTemplate.update(query, feedback.getEmployeeName(), feedback.getTopic(), feedback.getFeedback());
        } catch (Exception e) {
            System.err.println("Error adding feedback: " + e.getMessage());
        }
    }

    public List<EmployeeFeedback> getAllFeedback() {
        try {
            String query = "SELECT * FROM feedback";
            return jdbcTemplate.query(query, new FeedbackRowMapper());
        } catch (Exception e) {
            System.err.println("Error retrieving all feedback: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<EmployeeFeedback> getFeedbackByTopic(String topic) {
        try {
            String query = "SELECT * FROM feedback WHERE LOWER(topic) = LOWER(?)";
            return jdbcTemplate.query(query, new FeedbackRowMapper(), topic);
        } catch (Exception e) {
            System.err.println("Error retrieving feedback by topic: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private static class FeedbackRowMapper implements RowMapper<EmployeeFeedback> {
        @Override
        public EmployeeFeedback mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeeFeedback feedback = new EmployeeFeedback();
            feedback.setId(rs.getInt("id"));
            feedback.setEmployeeName(rs.getString("employee_name"));
            feedback.setTopic(rs.getString("topic"));
            feedback.setFeedback(rs.getString("feedback"));
            return feedback;
        }
    }
}
