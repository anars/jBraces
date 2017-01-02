/**
 * Copyright (c) 2012-2017 Anar Software LLC <http://anars.com>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal 
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is furnished 
 * to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.anars.jbraces.samples.formatters;

import com.anars.jbraces.TemplateProcessor;
import com.anars.jbraces.formatters.ReverseSpanFormatter;

public class ReverseSpanFormatterSample1 {

    public ReverseSpanFormatterSample1() {
        TemplateProcessor templateProcessor = new TemplateProcessor();
        templateProcessor.putSpanFormatter("Reverse", new ReverseSpanFormatter());
        String template = "{format:Reverse}Copyright (c) 2012-2017 Anar Software LLC <http://anars.com>{/format:Reverse}";
        System.out.println(template);
        template = templateProcessor.apply(template);
        System.out.println(template);
    }
    public static void main(String[] args) {
        new ReverseSpanFormatterSample1();
    }
}
