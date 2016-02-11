/**
 * Copyright (c) 2012-2016 Anar Software LLC <http://anars.com>
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

import java.text.NumberFormat;

import java.util.Locale;

import java.util.logging.Level;

public class FileSizeSpanFormatter
  extends SpanFormatter
{
  private static String[] PREFIXES =
  {
    "b", "kB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"
  };

  /**
   * @param string
   * @param locale
   * @return
   */
  public String format(String string, Locale locale)
  {
    string = string.replaceAll("[^0-9\\-]", "");
    String prefix = null;
    double size = 0;
    try
    {
      long value = Long.parseLong(string);
      for (int index = PREFIXES.length - 1; index >= 0 && prefix == null; index--)
      {
        size = value / Math.pow(10, 3 * index);
        if (size >= 1.0)
          prefix = PREFIXES[index];
      }
      size = ((double) ((int) ((size * Math.pow(10, 1))))) / Math.pow(10, 1);
    }
    catch (Exception exception)
    {
      _logger.log(Level.SEVERE, "Unable to parse value", exception);
    }
    return (NumberFormat.getInstance(locale).format(size) + " " + (prefix != null ? " " + prefix : ""));
  }
}
