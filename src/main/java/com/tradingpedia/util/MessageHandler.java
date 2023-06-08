package com.tradingpedia.util;

import com.tradingpedia.ButtonHelper;
import com.tradingpedia.api.ApiClient;
import com.tradingpedia.model.App;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Single;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.tradingpedia.ButtonHelper.*;


public class MessageHandler {
    private final ButtonHelper buttonHelper;
    private String callBackData;

    public MessageHandler() {
        this.buttonHelper = new ButtonHelper();
        this.callBackData = null;
    }

    public SendMessage handleMessage(Update update) {
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

            System.out.println("data = " + data);

            if (data.equals(CALL_BACK_DATA_APP)) { // بر اساس اپلیکیشن
                return getNumbersForApps(update);
            } else if (data.equals(CALL_BACK_DATA_COUNTRY)) { // بر اساس کشور
                getNumbersForCountries(update);
            } else if (data.equals(callBackData)) { // اسم اپ یا کشور

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

    private SendMessage getNumbersForApps(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        List<App> apps = new ArrayList<>();

        do {
            ApiClient.getClient()
                    .getBaseOnApps(NumberLandConstants.NUMBER_LAND_API_KEY, "getservice")
                    .enqueue(new Callback<List<App>>() {
                        @Override
                        public void onResponse(Call<List<App>> call, Response<List<App>> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                List<App> activeAppNumbers = response.body()
                                        .stream()
                                        .filter(app -> app.getActive().equals("1"))
                                        .toList();

                                apps.addAll(activeAppNumbers);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<App>> call, Throwable throwable) {
                            sendMessage.setText("خطا در دریافت اطلاعات...\nلطفاً مجدداً تلاش نمایید.");
                        }
                    });
        } while (apps.isEmpty());

        sendMessage.setText("اپلیکیشن مورد نظر را انتخاب نمایید:");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> finalList = new ArrayList<>();
        for (App app : apps) {
            InlineKeyboardButton keyboardButton = buttonHelper.createInlineButton(app.getName(), app.getNameEn(), null);
            List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
            inlineKeyboardButtons.add(keyboardButton);
            finalList.add(inlineKeyboardButtons);
        }

        inlineKeyboardMarkup.setKeyboard(finalList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    private void getNumbersForCountries(Update update) {

    }

}
