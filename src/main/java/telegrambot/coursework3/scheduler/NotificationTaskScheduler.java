package telegrambot.coursework3.scheduler;

import ch.qos.logback.core.util.FixedDelay;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import telegrambot.coursework3.entity.NotificationTask;
import telegrambot.coursework3.listener.TelegramBotUpdatesListener;
import telegrambot.coursework3.service.NotificationTaskService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class NotificationTaskScheduler {

    private final Logger logger = LoggerFactory.getLogger(NotificationTaskScheduler.class);
    private final NotificationTaskService notificationTaskService;
    private final TelegramBot telegramBot;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void sendNotifications() {
        LocalDateTime dateTimeToFindNotification = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        logger.info("Task scheduler started,{}", dateTimeToFindNotification);
        notificationTaskService.findAllByNotificationDateTime(dateTimeToFindNotification).forEach(this::sendNotification);
    }

    public void sendNotification(NotificationTask notification) {
        telegramBot.execute(new SendMessage(notification.getChatId(), notification.getMessage() + " " + notification.getNotificationDateTime()));
        notificationTaskService.delete(notification);
    }

}

