/**
 * Copyright (c) 2012 Anar Software LLC <http://anars.com>
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
package com.anars.jbrackets.formatters;

import com.anars.jbrackets.SpanFormatter;

import java.util.Locale;

public class RandomCaseSpanFormatter
  extends SpanFormatter
{
  /**
   * @param string
   * @param locale
   * @return
   */
  public String format(String string, Locale locale)
  {
    StringBuffer stringBuffer = new StringBuffer();
    for (int index = 0; index < string.length(); index++)
      {
        char character = string.charAt(index);
        if (Character.isLetter(character))
          character = (int) (Math.random() * 2.0) == 1 ? Character.toLowerCase(character) : Character.toUpperCase(character);
        stringBuffer.append(character);
      }
    return (stringBuffer.toString());
  }
}
