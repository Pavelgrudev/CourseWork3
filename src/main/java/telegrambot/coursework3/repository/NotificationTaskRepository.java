package telegrambot.coursework3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telegrambot.coursework3.entity.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationTaskRepository extends JpaRepository<NotificationTask ,Long> {

    /**
     * Получить список напоминаний на текущее время
     * @param notificationDateTime время для поиска напоминаний
     * @return список напоминаний
     */
    List<NotificationTask> findAllByNotificationDateTime(LocalDateTime notificationDateTime);

}
