package com.tradingpedia.util;

import com.tradingpedia.ButtonHelper;
import com.tradingpedia.api.ApiClient;
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
            String data = callbackQuery.getData();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            System.out.println("data = " + data + " , callback = " + callBackDatas);

            if (data.equals(CALL_BACK_DATA_APP)) { // بر اساس اپلیکیشن
                return getNumbersForApps(update);
            } else if (data.equals(CALL_BACK_DATA_COUNTRY)) { // بر اساس کشور
                return getNumbersForCountries(update);
            } else if (data.equals("")) { // اسم اپ یا کشور

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

    private SendMessage getNumbersForApps(Update update) throws IOException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        Response<List<App>> response = ApiClient.getClient().getBaseOnApps().execute();

        if (!response.isSuccessful()) {
            sendMessage.setText("خطا در دریافت اطلاعات...\nلطفاً مجدداً تلاش نمایید.");
            return sendMessage;
        }

        if (response.body() != null) {
            List<App> apps = response.body()
                    .stream()
                    .filter(app -> app.getActive().equals("1"))
                    .toList();

            sendMessage.setText("اپلیکیشن مورد نظر را انتخاب نمایید:");

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> finalList = new ArrayList<>();

            for (App app : apps) {
                String buttonText = buttonHelper.concatNameAndEmoji(app.getName(), app.getEmoji());
                InlineKeyboardButton keyboardButton = buttonHelper.createInlineButton(buttonText, app.getNameEn(), null);
                List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
                inlineKeyboardButtons.add(keyboardButton);
                finalList.add(inlineKeyboardButtons);
                callBackDatas.add(app.getNameEn());
            }

            inlineKeyboardMarkup.setKeyboard(finalList);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }

        return sendMessage;
    }

    private SendMessage getNumbersForCountries(Update update) throws IOException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        Response<List<Country>> response = ApiClient.getClient().getBaseOnCountries().execute();

        if (!response.isSuccessful() || response.body() == null) {
            sendMessage.setText("خطا در دریافت اطلاعات...\nلطفاً مجدداً تلاش نمایید.");
        } else {
            sendMessage.setText("کشور مورد نظر را انتخاب نمایید:");

            List<Country> countries = response.body()
                    .stream()
                    .filter(app -> app.getActive().equals("1"))
                    .toList();

            System.out.println("countries: " + response.body().size() + ", active: " + countries.size());

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> finalList = new ArrayList<>();

            for (Country country : countries) {
                String buttonText = buttonHelper.concatNameAndEmoji(country.getName(), country.getEmoji());
                InlineKeyboardButton keyboardButton = buttonHelper.createInlineButton(buttonText, country.getNameEn(), null);
                List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
                inlineKeyboardButtons.add(keyboardButton);
                finalList.add(inlineKeyboardButtons);
                callBackDatas.add(country.getNameEn());
            }

            inlineKeyboardMarkup.setKeyboard(finalList);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }
        return sendMessage;
    }

}
