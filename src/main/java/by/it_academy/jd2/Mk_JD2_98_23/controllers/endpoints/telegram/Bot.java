package by.it_academy.jd2.Mk_JD2_98_23.controllers.endpoints.telegram;

import by.it_academy.jd2.Mk_JD2_98_23.service.api.IArtistService;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.ArtistServiceFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    private IArtistService artistService;

    public Bot() {
        this.artistService = ArtistServiceFactory.getInstance();
    }

    @Override
    public String getBotUsername() {
        return "fdsfdsfdsfdsfdsf_bot";
    }

    @Override
    public String getBotToken() {
        return "5940094447:AAFH-h63E7UGyxfzruKRBi6i_KOKyRDbVhY";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        SendMessage sm = new SendMessage();
        String messageText = update.getMessage().getText().trim();

        if (messageText.matches("\\d+")) {
            int id = Integer.parseInt(messageText);
            if (artistService.get(id) != null) {
                sm = SendMessage.builder()
                        .chatId(update.getMessage().getChatId().toString()) //Who are we sending a message to
                        .text(artistService.get(id).getName()).build();    //Message content
            } else {
                sm = SendMessage.builder()
                        .chatId(update.getMessage().getChatId().toString()) //Who are we sending a message to
                        .text("Число " + id + " не найдено в базе данных").build();    //Message content
            }
        } else {
            if (messageText.equals("all")) {
                sm = SendMessage.builder()
                        .chatId(update.getMessage().getChatId().toString()) //Who are we sending a message to
                        .text(artistService.get().toString()).build();    //Message content
            } else {
                sm = SendMessage.builder()
                        .chatId(update.getMessage().getChatId().toString()) //Who are we sending a message to
                        .text("Пожалуйста, введите число").build();    //Message content}
            }
        }

//        SendMessage sm = SendMessage.builder()
//                .chatId(update.getMessage().getChatId().toString()) //Who are we sending a message to
//                .text(artistService.get().toString()).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
