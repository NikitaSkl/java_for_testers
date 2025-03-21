package ru.stqa.mantis.manager;

import jakarta.mail.*;
import ru.stqa.mantis.model.MailMessage;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class MailHelper extends HelperBase{

    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String username, String password, Duration duration) {
        var start=System.currentTimeMillis();
        while (System.currentTimeMillis()<start+duration.toMillis()){
            try {
                var inbox = getInbox(username, password);
                inbox.open(Folder.READ_ONLY); //режим открытия
                var messages = inbox.getMessages();
                var result = Arrays.stream(messages)
                        .map(m-> {
                            try {
                                return new MailMessage()
                                        .withFrom(m.getFrom()[0].toString())
                                        .withContent(m.getContent().toString());
                            } catch (MessagingException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .collect(Collectors.toList());
                inbox.close();
                inbox.getStore().close();
                if(result.size()>0){
                    return result;
                }
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("No mail received");
    }

    private static Folder getInbox(String username, String password){
        try {
            var session=Session.getInstance(new Properties()); //создаем сессию для работы с почтовым сервером
            //в рамках этой сесси получаем доступ к хранилищу почты
            Store store = session.getStore("pop3"); //выбираем протокол
            store.connect("localhost", username, password); //устанавливаем соединение
            var inbox = store.getFolder("INBOX");
            return inbox;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void drain(String username, String passwod){
        try {
            var inbox=getInbox(username,passwod);
            inbox.open(Folder.READ_WRITE);
            Arrays.stream(inbox.getMessages()).forEach(m-> {
                try {
                    m.setFlag(Flags.Flag.DELETED,true);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            inbox.close();
            inbox.getStore().close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
