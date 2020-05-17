package com.ilearn.scheduler;

import com.ilearn.config.AdminConfig;
import com.ilearn.domain.Homework;
import com.ilearn.domain.dto.Mail;
import com.ilearn.service.DbService;
import com.ilearn.service.EmailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

@Component
public class HomeworkScheduler {
    DbService dbService;
    EmailService emailService;
    AdminConfig adminConfig;

    public HomeworkScheduler(DbService dbService, EmailService emailService, AdminConfig adminConfig) {
        this.dbService = dbService;
        this.emailService = emailService;
        this.adminConfig = adminConfig;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void sendHomeworkReminder() {
        List<Homework> homeworkList = dbService.getHomeworkDatabase().getAllHomework();
        LocalDate todayDate = LocalDate.now();
        homeworkList.forEach(homework -> {
            LocalDate deadline = homework.getDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (Period.between(todayDate, deadline).getDays() > 0) {
                String userEmail = dbService.getUserDatabase().getUserByStudentId(homework.getStudent().getId()).getEmail();
                emailService.send(new Mail(
                        userEmail,
                        createSubject(homework.getContent()),
                        createMessage(Period.between(todayDate, deadline).getDays())));
            }

        });
    }

    private String createMessage(int daysLeft) {
        String time;
        if (daysLeft > 1) {
            time = " days";
        } else {
            time = " day";
        }
        String start = "You have ";
        String mid = time + " to give back your homework";
        String end = "\nlink";
        String greetings = "\n\nHave a good day!";
        String credits = "\n\nThis is auto-generated message, please don't answer to this e-mail";
        String from = "\nI-Learn aplication";
        return start + daysLeft + mid + end + greetings + credits + from;
    }

    private String createSubject(String homeworkContent) {
        return "Homework: " + homeworkContent;
    }
}
