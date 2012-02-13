package com.anars.jbrackets.formatters;

import com.anars.jbrackets.SpanFormatter;

import java.text.NumberFormat;

import java.util.Locale;
import java.util.logging.Level;

public class FileSizeSpanFormatter
  extends SpanFormatter
{
  private static String[] PREFIXES = { "b", "kB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };

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
