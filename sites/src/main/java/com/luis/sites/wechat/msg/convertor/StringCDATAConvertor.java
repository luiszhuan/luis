package com.luis.sites.wechat.msg.convertor;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class StringCDATAConvertor implements SingleValueConverter {
    static String prefix = "<![CDATA[";
    static String suffix = "]]>";

    @Override
    public String toString(Object o) {
        return prefix + o + suffix;
    }

    @Override
    public Object fromString(String context) {
        int endIndex = context.indexOf(suffix) < 0 ? context.length() : context.indexOf(suffix);
        int startIndex = context.indexOf(prefix) < 0 ? 0 : prefix.length();
        return context.substring(startIndex, endIndex);
    }

    @Override
    public boolean canConvert(Class aClass) {
        return String.class.equals(aClass);
    }
}
