package com.anars.jbrackets.samples;

import com.anars.jbrackets.TemplateProcessor;

public class IfSample2
{
  public IfSample2()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "{repeat:student:10:20}" +
                      "{set:mark}{random-number:50:100}{/set:mark}" +
                      "Math grade of the student {get:student} is {get:mark} / " +
                      "{if:fgrade:mark:less-than-or-equals:'59'}F{/if:fgrade}" +
                      "{if:Dmin:mark:greater-than-or-equals:'60'}{if:Dmax:mark:less-than-or-equals:'69'}D{/if:Dmax}{/if:Dmin}" +
                      "{if:Cmin:mark:greater-than-or-equals:'70'}{if:Cmax:mark:less-than-or-equals:'76'}C{/if:Cmax}{/if:Cmin}" +
                      "{if:Bmin:mark:greater-than-or-equals:'77'}{if:Bmax:mark:less-than-or-equals:'83'}B{/if:Bmax}{/if:Bmin}" +
                      "{if:BPmin:mark:greater-than-or-equals:'84'}{if:BPmax:mark:less-than-or-equals:'91'}B+{/if:BPmax}{/if:BPmin}" +
                      "{if:Amin:mark:greater-than-or-equals:'92'}{if:Amax:mark:less-than-or-equals:'98'}A{/if:Amax}{/if:Amin}" +
                      "{if:APmin:mark:greater-than-or-equals:'99'}{if:APmax:mark:less-than-or-equals:'100'}A+{/if:APmax}{/if:APmin}" +
                      "\n{/repeat:student}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new IfSample2();
  }
}
