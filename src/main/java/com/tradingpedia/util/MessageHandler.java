package com.tradingpedia.util;

import com.tradingpedia.ButtonHelper;
import com.tradingpedia.api.ApiClient;
import com.tradingpedia.model.App;
import com.tradingpedia.model.Country;
import com.tradingpedia.model.Result;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tradingpedia.ButtonHelper.*;


public class MessageHandler {
    private final ButtonHelper buttonHelper;
    private final Map<String, String> callBackMap;

    public MessageHandler() {
        this.callBackMap = new HashMap<>();
        this.buttonHelper = new ButtonHelper();
    }

    public SendMessage handleMessage(Update update) throws Exception {
        if (update.hasMessage()) {
            switch (update.getMessage().getText()) {
                case "/start" -> {
                    return handleStartMessage(update);
                }
                case BTN_BUY_NUMBER -> {
                    return showBuyNumberOptions(update);
                }
                default -> {
                    return null;
                }
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Long chatId = callbackQuery.getMessage().getChatId();
            String selectedServiceCode = callbackQuery.getData();

            callBackMap.put("code", selectedServiceCode);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            // اسم اپ یا کشور
            if (selectedServiceCode.equals(CALL_BACK_DATA_APP)) {  // بر اساس اپلیکیشن
                return showAppsList(update);
            } else if (selectedServiceCode.equals(CALL_BACK_DATA_COUNTRY)) {  // بر اساس کشور
                return showCountriesList(update);
            } else if (callBackMap.get("service").equals("app")) {
                System.out.println("app selected with code " + callBackMap.get("code"));
            } else if (callBackMap.get("service").equals("country")) {
                System.out.println("country selected with code " + callBackMap.get("code"));
            }

        }

        return null;
    }

    private void buyNumber(String selectedService) throws IOException {
        Response<List<Result>> response = ApiClient.getClient().buyNumber("", "", selectedService).execute();
    }

    private SendMessage handleStartMessage(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("""
                🌺 به ربات خرید شماره مجازی خوش آمدید 🌺
                                
                ✅ در کوتاهترین زمان و از طریق درگاه بانکی زرین پال، شماره مجازی دریافت کنید.
                """);

        KeyboardButton keyboardButton = buttonHelper.createKeyboardButton(BTN_BUY_NUMBER);

        KeyboardRow keyboardRow = buttonHelper.createSingleKeyboardRow(keyboardButton);

        List<KeyboardRow> keyboardRowList = new ArrayList<>(List.of(keyboardRow));

        ReplyKeyboardMarkup replyKeyboardMarkup = buttonHelper.createReplyKeyboardMarkup(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    private SendMessage showBuyNumberOptions(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("یکی از گزینه های زیر را انتخاب نمایید:");

        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();

        InlineKeyboardButton keyboardButtonApp = buttonHelper.createInlineButton(BTN_BASED_ON_APP, CALL_BACK_DATA_APP, null);
        InlineKeyboardButton keyboardButtonCountry = buttonHelper.createInlineButton(BTN_BASED_ON_COUNTRY, CALL_BACK_DATA_COUNTRY, null);

        inlineKeyboardButtons.add(keyboardButtonCountry);
        inlineKeyboardButtons.add(keyboardButtonApp);

        InlineKeyboardMarkup inlineKeyboardMarkup = buttonHelper.createInlineReplyKeyboardMarkup(List.of(inlineKeyboardButtons));
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;
    }

    private SendMessage showAppsList(Update update) throws IOException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        Response<List<App>> appsListResponse = ApiClient.getClient().getAppsList().execute();

        if (!appsListResponse.isSuccessful()) {
            sendMessage.setText("خطا در دریافت اطلاعات...\nلطفاً مجدداً تلاش نمایید.");
            return sendMessage;
        }

        if (appsListResponse.body() != null) {
            List<App> activeApps = appsListResponse.body()
                    .stream()
                    .filter(app -> app.getActive().equals("1"))
                    .toList()
                    .subList(0, 5);

            sendMessage.setText("اپلیکیشن مورد نظر را انتخاب نمایید:");

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            List<List<InlineKeyboardButton>> keyboards = buttonHelper.generateButtons(activeApps);

            callBackMap.put("service", "app");

            inlineKeyboardMarkup.setKeyboard(keyboards);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }

        return sendMessage;
    }

    private SendMessage showCountriesList(Update update) throws IOException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        Response<List<Country>> countryResponse = ApiClient.getClient().getCountriesList().execute();
        if (!countryResponse.isSuccessful()) {
            sendMessage.setText("خطا در دریافت اطلاعات...\nلطفاً مجدداً تلاش نمایید.");
            return sendMessage;
        }

        if (countryResponse.body() != null) {
            List<Country> activeCountries = countryResponse.body().stream().filter(country -> country.getActive().equals("1")).toList().subList(0, 3);

            sendMessage.setText("کشور مورد نظر را انتخاب نمایید:");

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            List<List<InlineKeyboardButton>> keyboards = buttonHelper.generateButtons(activeCountries);

            callBackMap.put("service", "country");

            inlineKeyboardMarkup.setKeyboard(keyboards);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }
        return sendMessage;
    }

}
