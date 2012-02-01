package com.anars.jbrackets.formatters;

import com.anars.jbrackets.SpanFormatter;

import java.text.NumberFormat;

import java.util.Locale;
import java.util.logging.Level;

public class FormatCurrencySpanFormatter
  extends SpanFormatter
{
  public String format(String string, Locale locale)
  {
    string = string.replaceAll("[^0-9\\-\\.]", "");
    double value = 0;
    try
      {
        value = Double.parseDouble(string);
      }
    catch (Exception exception)
      {
        _logger.log(Level.SEVERE, "Unable to parse value", exception);
      }
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
    return (numberFormat.format(value));
  }
}
