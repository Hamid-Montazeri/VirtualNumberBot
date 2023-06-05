package com.tradingpedia;

import com.tradingpedia.util.BotConfig;
import com.tradingpedia.util.MessageHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyLongPollingBot extends TelegramLongPollingBot {

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_USERNAME;
    }

    @Override
    public void onUpdateReceived(Update update) {
        MessageHandler messageHandler = new MessageHandler();
        SendMessage sendMessage = messageHandler.handleMessage(update);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
