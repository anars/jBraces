package com.anars.jbraces.samples.formatters;

import com.anars.jbraces.TemplateProcessor;
import com.anars.jbraces.formatters.EnglishOrdinalIndicatorSpanFormatter;

public class EnglishOrdinalIndicatorSpanFormatterSample1 {

    public EnglishOrdinalIndicatorSpanFormatterSample1() {
        TemplateProcessor templateProcessor = new TemplateProcessor();
        templateProcessor.putSpanFormatter("EnglishOrdinalIndicator", new EnglishOrdinalIndicatorSpanFormatter());
        String template = "{repeat:numbers:99}{format:EnglishOrdinalIndicator}{get:numbers.-offset}{/format:EnglishOrdinalIndicator} {/repeat:numbers}";
        System.out.println(template);
        template = templateProcessor.apply(template);
        System.out.println(template);
    }

    public static void main(String[] args) {
        new EnglishOrdinalIndicatorSpanFormatterSample1();
    }
}
