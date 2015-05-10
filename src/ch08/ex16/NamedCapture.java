/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex16;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Use a regular expression with named capturing groups to parse a line
 * containing a city, state, and zip code. Accept both 5- and 9-digit zip codes.
 *
 * 市（city）、州（state）、郵便番号（zip code）を含む行を解析するために名前付きキャプチャ グループを用いた正規表現を使用しなさい。5
 * 桁と9 桁の郵便番号の両方を受け付けるよ うにしなさい。
 */
public class NamedCapture {

    static public class CityInfo {

        public final String city;
        public final String state;
        public final String zipCode;

        private CityInfo(String city, String state, String zipCode) {
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
        }
    }

    static public CityInfo extractCityInfo(String cityInfo) {
        Objects.requireNonNull(cityInfo, "cityInfo is null");
        Pattern pattern = Pattern.compile("(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2})\\s+(?<zipCode>[0-9]{5}|[0-9]{5}-[0-9]{4})");
        Matcher matcher = pattern.matcher(cityInfo);
        if (matcher.matches()) {
            return new CityInfo(matcher.group("city"), matcher.group("state"), matcher.group("zipCode"));

        }
        throw new IllegalArgumentException("No match");
    }

}
