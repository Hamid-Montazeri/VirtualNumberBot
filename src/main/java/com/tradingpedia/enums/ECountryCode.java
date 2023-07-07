package com.tradingpedia.enums;


public enum ECountryCode {
    RUSSIA("1", "Russia"),
    UKRAINE("2", "Ukraine"),
    KAZAKHSTAN("3", "Kazakhstan"),
    CHINA("4", "China"),
    PHILIPPINES("5", "Philippines"),
    MYANMAR("6", "Myanmar"),
    INDONESIA("7", "Indonesia"),
    MALAYSIA("8", "Malaysia"),
    KENYA("9", "Kenya"),
    TANZANIA("10", "Tanzania"),
    VIETNAM("11", "Vietnam"),
    UNITED_KINGDOM("12", "United Kingdom"),
    LATVIA("13", "Latvia"),
    ROMANIA("14", "Romania"),
    ESTONIA("15", "Estonia"),
    USA("16", "UnitedStates"),
    USA_SPECIAL("17", "United States (Special)"),
    KYRGYZSTAN("18", "Kyrgyzstan"),
    FRANCE("19", "France"),
    PALESTINE("20", "Palestine"),
    CAMBODIA("21", "Cambodia"),
    MACAU("22", "Macau"),
    HONG_KONG("23", "Hong Kong"),
    BRAZIL("24", "Brazil"),
    POLAND("25", "Poland"),
    PARAGUAY("26", "Paraguay"),
    NETHERLANDS("27", "Netherlands"),
    LITHUANIA("28", "Lithuania"),
    MADAGASCAR("29", "Madagascar"),
    CONGO("30", "Congo"),
    NIGERIA("31", "Nigeria"),
    SOUTH_AFRICA("32", "South Africa"),
    PANAMA("33", "Panama"),
    EGYPT("34", "Egypt"),
    INDIA("35", "India"),
    IRELAND("36", "Ireland"),
    IVORY_COAST("37", "Ivory Coast"),
    SERBIA("38", "Serbia"),
    LAOS("39", "Laos"),
    MOROCCO("40", "Morocco"),
    YEMEN("41", "Yemen"),
    GHANA("42", "Ghana"),
    CANADA("43", "Canada"),
    ARGENTINA("44", "Argentina"),
    IRAQ("45", "Iraq"),
    GERMANY("46", "Germany"),
    CAMEROON("47", "Cameroon"),
    TURKEY("48", "Turkey"),
    NEW_ZEALAND("49", "New Zealand"),
    AUSTRIA("50", "Austria"),
    SAUDI_ARABIA("51", "Saudi Arabia"),
    MEXICO("52", "Mexico"),
    SPAIN("53", "Spain"),
    ALGERIA("54", "Algeria"),
    SLOVENIA("55", "Slovenia"),
    CROATIA("56", "Croatia"),
    BELARUS("57", "Belarus"),
    FINLAND("58", "Finland"),
    SWEDEN("59", "Sweden"),
    GEORGIA("60", "Georgia"),
    ETHIOPIA("61", "Ethiopia"),
    ZAMBIA("62", "Zambia"),
    PAKISTAN("63", "Pakistan"),
    THAILAND("64", "Thailand"),
    TAIWAN("65", "Taiwan"),
    PERU("66", "Peru"),
    NEW_GUINEA("67", "New Guinea"),
    CHAD("68", "Chad"),
    MALI("69", "Mali"),
    BANGLADESH("70", "Bangladesh"),
    GUINEA("71", "Guinea"),
    SRI_LANKA("72", "Sri Lanka"),
    UZBEKISTAN("73", "Uzbekistan"),
    SENEGAL("74", "Senegal"),
    COLOMBIA("75", "Colombia"),
    VENEZUELA("76", "Venezuela"),
    HAITI("77", "Haiti"),
    IRAN("78", "Iran"),
    MOLDOVA("79", "Moldova"),
    MOZAMBIQUE("80", "Mozambique"),
    GAMBIA("81", "Gambia"),
    AFGHANISTAN("82", "Afghanistan"),
    UGANDA("83", "Uganda"),
    AUSTRALIA("84", "Australia"),
    UAE("85", "United Arab Emirates"),
    CHILE("86", "Chile"),
    GUYANA("87", "Guyana"),
    NEPAL("88", "Nepal"),
    DJIBOUTI("89", "Djibouti"),
    GABON("90", "Gabon"),
    NICARAGUA("91", "Nicaragua"),
    BOSNIA("92", "Bosnia"),
    TOGO("93", "Togo"),
    ANGOLA("94", "Angola"),
    BOLIVIA("95", "Bolivia"),
    URUGUAY("96", "Uruguay"),
    ECUADOR("97", "Ecuador"),
    ITALY("98", "Italy"),
    GUATEMALA("99", "Guatemala"),
    TUNISIA("100", "Tunisia"),
    HUNGARY("101", "Hungary"),
    KUWAIT("102", "Kuwait"),
    AZERBAIJAN("103", "Azerbaijan"),
    SUDAN("104", "Sudan"),
    RWANDA("105", "Rwanda"),
    CAPE_VERDE("106", "Cape Verde"),
    MARTINIQUE("107", "Martinique"),
    COSTA_RICA("108", "Costa Rica"),
    HONDURAS("109", "Honduras"),
    EL_SALVADOR("110", "El Salvador"),
    BURUNDI("111", "Burundi"),
    GUINEA_BISSAU("112", "Guinea-Bissau"),
    TURKMENISTAN("113", "Turkmenistan"),
    SYRIA("114", "Syria"),
    TRINIDAD_AND_TOBAGO("115", "Trinidad and Tobago"),
    ST_LUCIA("116", "St. Lucia"),
    PUERTO_RICO("117", "Puerto Rico"),
    BULGARIA("118", "Bulgaria"),
    BELGIUM("119", "Belgium"),
    CZECH_REPUBLIC("120", "Czech Republic"),
    SLOVAKIA("121", "Slovakia"),
    NORWAY("122", "Norway"),
    PORTUGAL("123", "Portugal"),
    LUXEMBOURG("124", "Luxembourg"),
    ARMENIA("125", "Armenia"),
    JAMAICA("126", "Jamaica"),
    DOMINICAN_REPUBLIC("127", "Dominican Republic"),
    BHUTAN("128", "Bhutan"),
    JORDAN("129", "Jordan"),
    OMAN("130", "Oman"),
    BAHRAIN("131", "Bahrain"),
    QATAR("132", "Qatar"),
    MONGOLIA("133", "Mongolia"),
    MALDIVES("134", "Maldives"),
    LIBYA("135", "Libya"),
    MAURITANIA("136", "Mauritania"),
    BURKINA_FASO("137", "Burkina Faso"),
    NIGER("138", "Niger"),
    BENIN("139", "Benin"),
    LIBERIA("140", "Liberia"),
    SOMALI("141", "Somali"),
    ZIMBABWE("142", "Zimbabwe"),
    NAMIBIA("143", "Namibia"),
    MALAWI("144", "Malawi"),
    LESOTHO("145", "Lesotho"),
    BOTSWANA("146", "Botswana"),
    SWATINE("147", "Swatine"),
    SWITZERLAND("148", "Switzerland"),
    GUADELOUPE("149", "Guadeloupe"),
    BARBADOS("150", "Barbados"),
    ANTIGUA_AND_BARBUDA("151", "Antigua and Barbuda"),
    SAINT_KITTS("152", "Saint Kitts"),
    SAINT_VINCENT("153", "Saint Vincent"),
    ANGUILLA("154", "Anguilla"),
    SINGAPORE("155", "Singapore"),
    TAJIKISTAN("156", "Tajikistan"),
    TIMOR_LESTE("158", "Timor Leste"),
    Cyprus("159", "Cyprus"),
    DEMOCRATIC_PEOPLES_REPUBLIC_OF_KOREA("160", "Democratic Peoples Republic of Korea"),
    REPUBLIC_OF_KOREA("161", "Republic of Korea");

    private final String value;
    private final String title;

    ECountryCode(String value, String title) {
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
