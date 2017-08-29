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

public class JSONStringEncodeSpanFormatter
    extends SpanFormatter {

    public String format(String string, Locale locale) {
        int stringLength = string.length();
        StringBuilder stringBuilder = new StringBuilder();
        for(int index = 0; index < stringLength; index++) {
            char character = string.charAt(index);
            switch(character) {
                case '"':
                    stringBuilder.append("\\\"");
                    break;
                case '\\':
                    stringBuilder.append("\\\\");
                    break;
                case '\b':
                    stringBuilder.append("\\b");
                    break;
                case '\f':
                    stringBuilder.append("\\f");
                    break;
                case '\n':
                    stringBuilder.append("\\n");
                    break;
                case '\r':
                    stringBuilder.append("\\r");
                    break;
                case '\t':
                    stringBuilder.append("\\t");
                    break;
                case '/':
                    stringBuilder.append("\\/");
                    break;
                default:
                    // Reference: http://www.unicode.org/versions/Unicode5.1.0/
                    if((character >= '\u0000' && character <= '\u001F') || (character >= '\u007F' && character <= '\u009F') || (character >= '\u2000' && character <= '\u20FF')) {
                        String ss = Integer.toHexString(character);
                        stringBuilder.append("\\u");
                        for(int k = 0; k < 4 - ss.length(); k++)
                            stringBuilder.append('0');
                        stringBuilder.append(ss.toUpperCase());
                    }
                    else {
                        stringBuilder.append(character);
                    }
            }
        }
        return (stringBuilder.toString());
    }
}
