package mx.edu.utez.bitacoradigitalservices.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String toEmail, String token) {
        // Ajustar el endpoint
        String resetLink = "http://localhost:5173/recovery?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("andoid2006@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Solicitud de restablecimiento de contraseña - Bitácora Digital");
        message.setText("Hola,\n\n" +
                "Ha solicitado restablecer su contraseña. Por favor haga clic en el enlace a continuación para cambiarlo:\n\n" +
                resetLink + "\n\n" +
                "Si no solicitó esto, ignore este correo electrónico.\n\n");

        mailSender.send(message);
    }
}
