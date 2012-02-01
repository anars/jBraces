package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class FormatSample2
{
  public FormatSample2()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    templateProcessor.loadAllSpanFormatters();
    String template = "{format:FormatDoubleNumber:tr:tr}1234567.89{/format:FormatDoubleNumber} and {format:FormatDoubleNumber:en:us}1234567.89{/format:FormatDoubleNumber} are same numbers in different countries.";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new FormatSample2();
  }
}
