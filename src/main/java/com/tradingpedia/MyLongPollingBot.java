package com.tradingpedia;

import com.tradingpedia.util.BotConfig;
import com.tradingpedia.util.MessageHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class MyLongPollingBot extends TelegramLongPollingBot {

    private final MessageHandler messageHandler;

    public MyLongPollingBot() {
        messageHandler = new MessageHandler();
    }

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
        try {
            SendMessage sendMessage = messageHandler.handleMessage(update);
            if (sendMessage != null) {
                execute(sendMessage);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
