package com.tradingpedia.enums;


public enum EServiceCode {
    TELEGRAM("1", "Telegram"),
    INSTAGRAM("2", "Instagram"),
    WHATSAPP("3", "whatsapp"),
    VIBER("4", "viber"),
    WECHAT("5", "wechat"),
    GOOGLE("6", "google"),
    FACEBOOK("7", "facebook"),
    TWITTER("8", "twitter"),
    MICROSOFT("9", "microsoft"),
    LINE("10", "line"),
    YAHOO("11", "yahoo"),
    BEETALK("13", "beetalk"),
    LINKEDIN("14", "linkedin"),
    PAYPAL("15", "paypal"),
    FIVERR("16", "fiverr"),
    TINDER("17", "tinder"),
    IMO("18", "imo"),
    WEBMONEY("19", "webmoney"),
    VK("20", "vk"),
    AMAZON("21", "amazon"),
    MAIL_RU("22", "mail.ru"),
    NETFLIX("23", "netflix"),
    STEAM("24", "steam"),
    DISCORD("25", "discord"),
    APPLE("26", "apple"),
    QIWI("27", "qiwi"),
    SITE_1688_COM("31", "1688.com"),
    BLIZZARD("32", "Blizzard"),
    TIKTOK("33", "tiktok"),
    LOCALBITCOINS("34", "LocalBitcoins"),
    G2A_com("35", "G2A.com"),
    CLUBHOUSE("36", "Clubhouse"),
    BIGO_LIVE("37", "Bigo Live"),
    TENCENT_QQ("38", "Tencent QQ"),
    AIRBNB("39", "Airbnb"),
    SNAPCHAT("40", "Snapchat"),
    ALIBABA("41", "Alibaba"),
    SIGNAL("45", "signal"),
    ALIEXPRESS("46", "AliExpress"),
    TANGO("47", "Tango"),
    ADIDAS("48", "Adidas"),
    NIKE("49", "Nike"),
    EBAY("51", "ebay"),
    MEGA("52", "MEGA"),
    HUAWEI("53", "huawei"),
    ALIPAY("54", "AliPay"),
    ZOHO("55", "Zoho"),
    AOL("56", "AOL"),
    SKRILL("57", "Skrill"),
    BLOCKCHAIN("58", "blockchain"),
    TAOBAO("59", "Taobao"),
    LIKEE("60", "Likee"),
    COINBASE("61", "Coinbase"),
    UPWORK("62", "Upwork"),
    PROTONMAIL("63", "ProtonMail"),
    FASTMAIL("64", "Fastmail"),
    GAMEFLIP("65", "Gameflip"),
    TRADINGVIEW("66", "TradingView"),
    KAKAOTALK("68", "KakaoTalk"),
    CRYPTOCOM("69", "cryptocom"),
    ODNOKLASSNIKI("70", "odnoklassniki"),
    MAMBA("71", "Mamba"),
    OPEN_AI("72", "open ai"),
    KAGGLE("73", "kaggle"),
    GOOGLE_VOICE("74", "google voice");

    private final String value;
    private final String title;

    EServiceCode(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }
}
