package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class DrawSample2
{
  public DrawSample2()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "Sheldon Cooper says: {draw:Rock:Paper:Scissors:Lizard:Spock}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new DrawSample2();
  }
}
