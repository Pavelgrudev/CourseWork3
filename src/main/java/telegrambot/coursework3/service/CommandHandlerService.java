package telegrambot.coursework3.service;
/**
 * сервис для обработки команд бота
 * */
public interface CommandHandlerService {
    /**
     *Обработать команду
     *
     * @param chatId идентификатор чата
     * @param command текст команды
     * @return ответ на команду
     */
    String handleCommand(Long chatId,String command);
}
