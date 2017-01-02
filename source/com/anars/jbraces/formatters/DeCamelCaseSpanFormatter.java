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
package com.anars.jbraces.formatters;

import com.anars.jbraces.SpanFormatter;

import java.util.Locale;

public class DeCamelCaseSpanFormatter
    extends SpanFormatter {

    public String format(String string, Locale locale) {
        String[] words = string.replaceAll("([a-z])([A-Z]+)", "$1 $2").replaceAll("([A-Z])([^A-Z]+)", " $1$2").trim().split("\\s+");
        StringBuffer stringBuffer = new StringBuffer();
        for(int index = 0; index < words.length; index++)
            if(index == 0) {
                stringBuffer.append(words[index].substring(0, 1).toUpperCase(locale));
                if(words[index].length() > 1)
                    stringBuffer.append(words[index].substring(1));
            }
            else {
                stringBuffer.append(" ");
                if(words[index].length() == 1) {
                    stringBuffer.append(words[index].toLowerCase(locale));
                }
                else if(Character.isLowerCase(words[index].charAt(1))) {
                    stringBuffer.append(words[index].substring(0, 1).toLowerCase(locale));
                    if(words[index].length() > 1)
                        stringBuffer.append(words[index].substring(1));
                }
                else {
                    stringBuffer.append(words[index]);
                }
            }
        return (stringBuffer.toString());
    }
}
