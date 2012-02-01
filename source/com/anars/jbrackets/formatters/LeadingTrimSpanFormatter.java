package com.anars.jbrackets.formatters;

import com.anars.jbrackets.SpanFormatter;

import java.util.Locale;

public class LeadingTrimSpanFormatter
  extends SpanFormatter
{
  public String format(String string, Locale locale)
  {
    return (string.replaceAll("^\\s+", ""));
  }
}
