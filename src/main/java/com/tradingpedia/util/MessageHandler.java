package com.tradingpedia.util;

import com.tradingpedia.ButtonHelper;
import com.tradingpedia.api.ApiClient;
import com.tradingpedia.api.NumberLandApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static com.tradingpedia.ButtonHelper.BTN_BUY_NUMBER;


public class MessageHandler {

    private final ButtonHelper buttonHelper;

    public MessageHandler() {
        this.buttonHelper = new ButtonHelper();
    }

    public SendMessage handleMessage(Update update) {
        String text = update.getMessage().getText();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());

        switch (text) {
            case "/start":
                handleStartMessage(sendMessage);
                break;
            case BTN_BUY_NUMBER:
                handleBuyNumberMessage();
                break;
            default:
                break;
        }
        return sendMessage;
    }

    private void handleStartMessage(SendMessage sendMessage) {
        sendMessage.setText("""
        🌺 به ربات خرید شماره مجازی خوش آمدید 🌺
                        
        ✅ در کوتاهترین زمان و از طریق درگاه بانکی زرین پال، شماره مجازی دریافت کنید.
        """);

        KeyboardButton keyboardButton = buttonHelper.createButton(BTN_BUY_NUMBER);
        KeyboardRow keyboardRow = buttonHelper.createKeyboardRow(keyboardButton);

        List<KeyboardRow> keyboardRowList = new ArrayList<>(List.of(keyboardRow));

        ReplyKeyboardMarkup replyKeyboardMarkup = buttonHelper.createReplyKeyboardMarkup(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }


    private void handleBuyNumberMessage() {
        NumberLandApi numberLandApi = ApiClient.getClient();

    }

}
