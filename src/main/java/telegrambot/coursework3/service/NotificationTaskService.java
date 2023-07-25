package telegrambot.coursework3.service;

import telegrambot.coursework3.entity.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

/**
 * сервис по работе с напоминаниями
 */
public interface NotificationTaskService {
    /**
     * Сохранить напоминание
     *
     * @param task напоминание
     */
    void save(NotificationTask task);

    /**
     * Удалить напоминание
     *
     * @param task напоминание
     */
    void delete(NotificationTask task);

    /**
     * Получить список напоминаний на текущее время
     *
     * @param notificationDateTime время для поиска напоминаний
     * @return список напоминаний
     */
    List<NotificationTask> findAllByNotificationDateTime(LocalDateTime notificationDateTime);
}






