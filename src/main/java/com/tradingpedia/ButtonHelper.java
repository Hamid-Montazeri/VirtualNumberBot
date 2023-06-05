package com.tradingpedia;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonHelper {
    public static final String BTN_BUY_NUMBER = "خرید شماره مجازی";

    public KeyboardButton createButton(String buttonText) {
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(buttonText);
        return keyboardButton;
    }

    public KeyboardRow createKeyboardRow(KeyboardButton keyboardButton) {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(keyboardButton);
        return keyboardRow;
    }

    public ReplyKeyboardMarkup createReplyKeyboardMarkup(List<KeyboardRow> keyboardRowList) {
        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboardRowList)
                .resizeKeyboard(true)
                .selective(true)
                .build();
    }

}
