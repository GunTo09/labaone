package ru.gunto09.java.labaone.service;
import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gunto09.java.labaone.model.Joke;
import ru.gunto09.java.labaone.service.JokeService;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TelegramBotService  {

    private final TelegramBot telegramBot; //Забираем наш бин с ботом из Spring-а

    private final JokeService jokeService;

    public TelegramBotService(@Autowired TelegramBot telegramBot, JokeService jokeService) { //Конструктор для вставки бина
        this.telegramBot = telegramBot;
        this.jokeService = jokeService;
        this.telegramBot.setUpdatesListener(updates -> { //Лямбда - регистрируем слушателя обновлений
            updates.forEach(this::processUpdate); //В лямбде забираем все обновления - и вызываем обработку их
            return UpdatesListener.CONFIRMED_UPDATES_ALL; //Подтверждаем, что все забрали
        }, Throwable::printStackTrace); //Если поймали ошибку - выводим трейс, чтобы понять, в чем дело
    }

    private void buttonClickReact(Update update) { //Реагируем на событие
        //Подготавливаем сообщение на ответ
        SendMessage request = new SendMessage(update.message().chat().id(), "Ты дурак? Нормально команду напиши, дятел") //update.message().chat().id() - Id, в какой чат отправлять сообщение, в данном случае - тому, кто написал
                .parseMode(ParseMode.HTML) //Без понятия, что такое, но было в документации
                .disableWebPagePreview(true) //Без понятия, что такое, но было в документации
                .disableNotification(true) //Без понятия, что такое, но было в документации
                .replyToMessageId(update.message().messageId()); //Делаем наш ответ как ответ на отправленное ранее сообщение
        this.telegramBot.execute(request); //Отправляем подготовленное сообщение
    }

    private void processUpdate(Update update) {
        if (update.message() != null && update.message().text() != null && update.message().text().equals("/start")) {
            sendStartMessage(update.message().chat().id());
        }else if(update.message() != null && update.message().text() != null && update.message().text().equals("/hi")) {
            sendMessage(update.message().chat().id());
        }else if (update.callbackQuery() != null) {
            handleCallbackQuery(update.callbackQuery());
        }else if(update.message() != null && update.message().text() != null && update.message().text().equals("/joke")) {
            sendJokeInMessage(update.message().chat().id());
        }else{
            buttonClickReact(update);
        }
    }

    private void sendMessage(Long chatId) {
        SendMessage request = new SendMessage(chatId, "hi");
        this.telegramBot.execute(request);
    }

    private void sendJokeInMessage(Long chatId){
        Optional<Joke> jokes = jokeService.getJokeById(new Random().nextLong(6)+1);
        if (!jokes.isEmpty()) {
            Joke randomJoke = jokes.get();
            SendMessage request = new SendMessage(chatId, randomJoke.getTextJoke());
            this.telegramBot.execute(request);
        } else {
            SendMessage request = new SendMessage(chatId, "К сожалению, нет доступных шуток в данный момент.");
            this.telegramBot.execute(request);
        }
    }



    //изменение 1
    private void sendStartMessage(Long chatId) {
        SendMessage request = new SendMessage(chatId, "Привет! Хочешь шутку?");
        request.replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("Да, хочу").callbackData("/joke")));
        this.telegramBot.execute(request);
    }

    private void handleCallbackQuery(com.pengrad.telegrambot.model.CallbackQuery callbackQuery){
        String data = callbackQuery.data();
        if ("/joke".equals(data)){
            sendRandomJoke(callbackQuery.message().chat().id());
            telegramBot.execute(new AnswerCallbackQuery(callbackQuery.id()));
        }
    }

    private void sendRandomJoke(Long chatId) {
        Optional<Joke> jokes = jokeService.getJokeById(new Random().nextLong(6)+1);
        if (!jokes.isEmpty()) {
            Joke randomJoke = jokes.get();
            SendMessage request = new SendMessage(chatId, randomJoke.getTextJoke());
            this.telegramBot.execute(request);
        } else {
            SendMessage request = new SendMessage(chatId, "К сожалению, нет доступных шуток в данный момент.");
            this.telegramBot.execute(request);
        }
    }

}

