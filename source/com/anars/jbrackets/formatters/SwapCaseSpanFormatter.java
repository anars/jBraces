package com.anars.jbrackets.formatters;

import com.anars.jbrackets.SpanFormatter;

import java.util.Locale;

public class SwapCaseSpanFormatter
  extends SpanFormatter
{
  public String format(String string, Locale locale)
  {
    StringBuffer stringBuffer = new StringBuffer();
    for (int index = 0; index < string.length(); index++)
      {
        char character = string.charAt(index);
        if (Character.isUpperCase(character))
          stringBuffer.append(Character.toLowerCase(character));
        else
          stringBuffer.append(Character.toUpperCase(character));
      }
    return (stringBuffer.toString());
  }
}
