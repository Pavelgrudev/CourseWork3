package telegrambot.coursework3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telegrambot.coursework3.configuration.TelegramBotConfiguration;
import telegrambot.coursework3.entity.NotificationTask;

import java.lang.module.Configuration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CommandHandlerServiceImpl implements CommandHandlerService {
    private final NotificationTaskService notificationTaskService;
    private final TelegramBotConfiguration config;
    private static final String START_COMMAND = "/start";
    private static final String HELP_COMMAND = "/help";
    private final Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy  HH:mm");

    @Override
    public String handleCommand(Long chatId, String command) {
        switch (command) {
            case START_COMMAND:
                return config.getStartMsg();
            case HELP_COMMAND:
                return config.getHelpMsg();
            default:
                return handleCreateTaskCommand(chatId, command);
        }
    }

    private String handleCreateTaskCommand(Long chatId, String command) {
        if (command != null) {
            Matcher matcher = pattern.matcher(command);

            if (matcher.find()) {
                LocalDateTime notificationDate = parseNotificationDate(matcher.group(1));
                if (notificationDate == null) {
                    return config.getErrorMsg();
                } else {
                    String notificationText = matcher.group(3);
                    notificationTaskService.save(new NotificationTask(chatId, notificationText, notificationDate));
                }
                return config.getSuccessMsg();
            }
        }

        return config.getErrorMsg();
    }

    private LocalDateTime parseNotificationDate(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }


    }


}
