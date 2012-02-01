package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class ShowRandomlySample1
{
  public ShowRandomlySample1()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "Welcome... {show-randomly:winner}Congratulations, " +
                      "You are the winner!{/show-randomly:winner}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new ShowRandomlySample1();
  }
}
