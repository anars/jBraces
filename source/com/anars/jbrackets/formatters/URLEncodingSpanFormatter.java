package com.anars.jbrackets.formatters;

import com.anars.jbrackets.SpanFormatter;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import java.util.Locale;
import java.util.logging.Level;

public class URLEncodingSpanFormatter
  extends SpanFormatter
{
  public String format(String string, Locale locale)
  {
    try
      {
        return (URLEncoder.encode(string, "utf-8"));
      }
    catch (UnsupportedEncodingException unsupportedEncodingException)
      {
        _logger.log(Level.SEVERE, "Unsupported Encoding", unsupportedEncodingException);
        return (string);
      }
  }
}
