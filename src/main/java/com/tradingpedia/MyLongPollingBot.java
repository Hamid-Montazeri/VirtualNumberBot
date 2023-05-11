package com.tradingpedia;

import com.tradingpedia.util.BotConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

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

    }

}
