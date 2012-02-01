package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class IfSample1
{
  public IfSample1()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "{set:hero}{draw:Superman:Batman}{/set:hero}My childhood hero is {get:hero}. " +
                      "{if:supermanpowers:hero:equals:'Superman'}Some of his superpowers are " +
                      "super strength, speed, stamina, endurance and durability{/if:supermanpowers}" +
                      "{if:batmanpowers:hero:equals:'Batman'}Unlike most superheroes, " +
                      "he does not possess any superpowers{/if:batmanpowers}.";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new IfSample1();
  }
}
