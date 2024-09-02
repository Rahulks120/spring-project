package com.ivoyant.spring_project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ivoyant.spring_project.model.EmployeeFeedback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FeedbackDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addFeedback(EmployeeFeedback feedback) {
        String query = "INSERT INTO feedback (employee_name, topic, feedback) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, feedback.getEmployeeName(), feedback.getTopic(), feedback.getFeedback());
    }

    public List<EmployeeFeedback> getAllFeedback() {
        String query = "SELECT * FROM feedback";
        return jdbcTemplate.query(query, new FeedbackRowMapper());
    }

    public List<EmployeeFeedback> getFeedbackByTopic(String topic) {
        String query = "SELECT * FROM feedback WHERE LOWER(topic) = LOWER(?)";
        return jdbcTemplate.query(query, new FeedbackRowMapper(), topic);
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
