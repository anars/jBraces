package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class DrawSample1
{
  public DrawSample1()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "Is it a {draw:true:false} information that your are awesome?";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new DrawSample1();
  }
}
