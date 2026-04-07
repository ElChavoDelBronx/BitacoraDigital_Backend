package mx.edu.utez.bitacoradigitalservices.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("andoid2006@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Código de Recuperación - Bitácora Digital");
        message.setText("Hola,\n\n" +
                "Tu código para restablecer la contraseña es:\n\n" +
                code + "\n\n" +
                "Si no solicitaste este cambio, por favor ignora este correo.\n\n");

        mailSender.send(message);
    }
}
