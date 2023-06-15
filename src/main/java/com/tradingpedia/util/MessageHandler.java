package com.tradingpedia.util;

import com.tradingpedia.ButtonHelper;
import com.tradingpedia.api.ApiClient;
import com.tradingpedia.enums.NumberType;
import com.tradingpedia.model.App;
import com.tradingpedia.model.Country;
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
import java.util.List;
import java.util.Map;

import static com.tradingpedia.ButtonHelper.*;


public class MessageHandler {
    private final ButtonHelper buttonHelper;
    private final List<String> callBackDatas;

    public MessageHandler() {
        this.buttonHelper = new ButtonHelper();
        this.callBackDatas = new ArrayList<>();
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
            String selectedCallback = callbackQuery.getData();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            System.out.println("data = " + selectedCallback + " , callback = " + callBackDatas);

            // اسم اپ یا کشور
            if (selectedCallback.equals(CALL_BACK_DATA_APP)) {  // بر اساس اپلیکیشن
                return getNumbers(update, NumberType.APP);
            } else if (selectedCallback.equals(CALL_BACK_DATA_COUNTRY)) {  // بر اساس کشور
                return getNumbers(update, NumberType.COUNTRY);
            } else {

            }

        }

        return null;
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

    private SendMessage getNumbers(Update update, NumberType numberType) throws IOException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        if (numberType == NumberType.APP) {
            Response<List<App>> appResponse = ApiClient.getClient().getBaseOnApps().execute();

            if (!appResponse.isSuccessful()) {
                sendMessage.setText("خطا در دریافت اطلاعات...\nلطفاً مجدداً تلاش نمایید.");
                return sendMessage;
            }

            if (appResponse.body() != null) {
                List<App> activeApps = appResponse.body().stream().filter(app -> app.getActive().equals("1")).toList().subList(0, 5);
                callBackDatas.addAll(activeApps.stream().map(App::getNameEn).toList());

                sendMessage.setText("اپلیکیشن مورد نظر را انتخاب نمایید:");

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

                Map<String, Object> map = buttonHelper.generateButtons(activeApps);
                List<List<InlineKeyboardButton>> keyboards = (List<List<InlineKeyboardButton>>) map.get("list");

                inlineKeyboardMarkup.setKeyboard(keyboards);
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            }
        }
        if (numberType == NumberType.COUNTRY) {
            Response<List<Country>> countryResponse = ApiClient.getClient().getBaseOnCountries().execute();
            if (!countryResponse.isSuccessful()) {
                sendMessage.setText("خطا در دریافت اطلاعات...\nلطفاً مجدداً تلاش نمایید.");
                return sendMessage;
            }

            if (countryResponse.body() != null) {
                List<Country> activeCountries = countryResponse.body().stream().filter(country -> country.getActive().equals("1")).toList().subList(0, 3);
                callBackDatas.addAll(activeCountries.stream().map(Country::getNameEn).toList());

                sendMessage.setText("اپلیکیشن مورد نظر را انتخاب نمایید:");

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

                Map<String, Object> map = buttonHelper.generateButtons(activeCountries);
                List<List<InlineKeyboardButton>> keyboards = (List<List<InlineKeyboardButton>>) map.get("list");

                inlineKeyboardMarkup.setKeyboard(keyboards);
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            }
        }

        return sendMessage;
    }

}
