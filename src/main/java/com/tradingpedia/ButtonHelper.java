package com.tradingpedia;

import com.tradingpedia.model.App;
import com.tradingpedia.model.Country;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ButtonHelper {
    public static final String BTN_BUY_NUMBER = "خرید شماره مجازی";
    public static final String BTN_BASED_ON_APP = "بر اساس اپلیکیشن";
    public static final String BTN_BASED_ON_COUNTRY = "بر اساس کشور";
    public static final String CALL_BACK_DATA_APP = "app";
    public static final String CALL_BACK_DATA_COUNTRY = "country";

    public KeyboardButton createKeyboardButton(String buttonText) {
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(buttonText);
        return keyboardButton;
    }

    public InlineKeyboardButton createInlineButton(String buttonText, String callBackData, String url) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(buttonText);
        if (callBackData != null) {
            inlineKeyboardButton.setCallbackData(callBackData);
        }
        if (url != null) {
            inlineKeyboardButton.setUrl(url);
        }
        return inlineKeyboardButton;
    }

    public KeyboardRow createSingleKeyboardRow(KeyboardButton keyboardButton) {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(keyboardButton);
        return keyboardRow;
    }

    public KeyboardRow createDoubleKeyboardRow(List<KeyboardButton> keyboardButtons) {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.addAll(keyboardButtons);
        return keyboardRow;
    }

    public ReplyKeyboardMarkup createReplyKeyboardMarkup(List<KeyboardRow> keyboardRowList) {
        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboardRowList)
                .resizeKeyboard(true)
                .selective(true)
                .build();
    }

    public InlineKeyboardMarkup createInlineReplyKeyboardMarkup(List<List<InlineKeyboardButton>> inlineKeyboardButtons) {
        return InlineKeyboardMarkup.builder()
                .keyboard(inlineKeyboardButtons)
                .build();
    }

    public Map<String, Object> generateButtons(List<?> appsOrCountries) {
        Map<String, Object> map = new HashMap<>();
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        for (Object appsOrCountry : appsOrCountries) {
            String buttonText;
            InlineKeyboardButton keyboardButton = null;
            if (appsOrCountry instanceof App app) {
                buttonText = concatNameAndEmoji(app.getName(), app.getEmoji());
                keyboardButton = createInlineButton(buttonText, app.getNameEn(), null);
                map.putIfAbsent("callback", app.getNameEn());
            } else if (appsOrCountry instanceof Country country) {
                buttonText = concatNameAndEmoji(country.getName(), country.getEmoji());
                keyboardButton = createInlineButton(buttonText, country.getNameEn(), null);
                map.putIfAbsent("callback", country.getNameEn());
            }
            List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
            inlineKeyboardButtons.add(keyboardButton);
            list.add(inlineKeyboardButtons);
        }
        map.putIfAbsent("list", list);
        return map;
    }

    public String concatNameAndEmoji(String name, String emoji) {
        if (emoji != null) {
            return String.format("%s %s %s", emoji, name, emoji);
        } else {
            return name;
        }
    }

}
