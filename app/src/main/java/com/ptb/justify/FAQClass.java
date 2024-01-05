package com.ptb.justify;

public class FAQClass {
    String pertanyaanFAQ, deskripsiFAQ;
    boolean isVisible;

    public FAQClass(String pertanyaanFAQ, String deskripsiFAQ, boolean isVisible) {
        this.pertanyaanFAQ = pertanyaanFAQ;
        this.deskripsiFAQ = deskripsiFAQ;
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return  isVisible;
    }
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}

