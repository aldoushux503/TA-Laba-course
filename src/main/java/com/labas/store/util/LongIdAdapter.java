package com.labas.store.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LongIdAdapter extends XmlAdapter<String, Long> {

    @Override
    public Long unmarshal(String s) {
        return Long.parseLong(s);
    }

    @Override
    public String marshal(Long aLong) {
        return aLong.toString();
    }
}
