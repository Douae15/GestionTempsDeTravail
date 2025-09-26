package com.employee.back_gestionTempsDeTravail.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.employee.back_gestionTempsDeTravail.entity.Employee;
import com.employee.back_gestionTempsDeTravail.repository.EmployeeRepository;
import com.employee.back_gestionTempsDeTravail.token.TokenService;

import java.sql.Date;
import java.util.List;

@Component
public class MailScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TokenService tokenService;

   // @Scheduled(cron = "0 0 18 * * MON-FRI") 
    @Scheduled(cron = "0 * * * * MON-FRI")
    public void scheduleTaskReminders() {
        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {
            String email = employee.getEmail();

            System.out.println(email);
            String subject = "Humeur de : " + new Date(System.currentTimeMillis());
            String message ="Cher employé,<br><br>" + 
            "Nous espérons que vous passez une excellente journée.<br><br>"
            +"Nous aimerions vous inviter à partager votre humeur actuelle avec nous."
            +"Votre bien-être est important pour nous, et votre humeur peut avoir un impact sur votre expérience au travail. "
            +"Nous vous encourageons donc à partager votre humeur en toute confidentialité en utilisant le lien suivant " +
                    "<a href='http://localhost:4200/humeur/" + tokenService.createToken() + "'>Cliquez ici</a>. "
            +" Cela ne prend que quelques instants."
            +"<br>Meilleures salutations,";

            emailService.sendReminderEmail(email, subject, message);
        }
    }
}
