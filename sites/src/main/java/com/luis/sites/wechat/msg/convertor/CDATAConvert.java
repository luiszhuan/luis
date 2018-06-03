package com.luis.sites.wechat.msg.convertor;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@Deprecated
public class CDATAConvert implements Converter {
    static String prefix = "<![CDATA[";
    static String suffix = "]]>";

    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {
        String trans = prefix + marshallingContext + suffix;
        hierarchicalStreamWriter.setValue(trans);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {

        String context = hierarchicalStreamReader.getValue();
        int endIndex = context.indexOf(suffix) < 0 ? context.length() : context.indexOf(suffix);
        int startIndex = context.indexOf(prefix) < 0 ? 0 : prefix.length();
        return context.substring(startIndex, endIndex);
    }

    @Override
    public boolean canConvert(Class aClass) {
        return String.class.isAssignableFrom(aClass);
    }
}
