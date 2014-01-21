/**
 * Copyright (c) 2012-2014 Anar Software LLC <http://anars.com>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal 
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is furnished 
 * to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.anars.jbraces.samples.functions;

import com.anars.jbraces.TemplateProcessor;

public class IfSample2
{
  public IfSample2()
  {
    TemplateProcessor templateProcessor = new TemplateProcessor();
    String template = "{repeat:student:10:20}" + "{set:mark}{random-number:50:100}{/set:mark}" + "Math grade of the student {get:student.-value} is {get:mark} / " + "{if:fgrade:mark:less-than-or-equals:'59'}F{/if:fgrade}" + "{if:Dmin:mark:greater-than-or-equals:'60'}{if:Dmax:mark:less-than-or-equals:'69'}D{/if:Dmax}{/if:Dmin}" + "{if:Cmin:mark:greater-than-or-equals:'70'}{if:Cmax:mark:less-than-or-equals:'76'}C{/if:Cmax}{/if:Cmin}" + "{if:Bmin:mark:greater-than-or-equals:'77'}{if:Bmax:mark:less-than-or-equals:'83'}B{/if:Bmax}{/if:Bmin}" + "{if:BPmin:mark:greater-than-or-equals:'84'}{if:BPmax:mark:less-than-or-equals:'91'}B+{/if:BPmax}{/if:BPmin}" + "{if:Amin:mark:greater-than-or-equals:'92'}{if:Amax:mark:less-than-or-equals:'98'}A{/if:Amax}{/if:Amin}" + "{if:APmin:mark:greater-than-or-equals:'99'}{if:APmax:mark:less-than-or-equals:'100'}A+{/if:APmax}{/if:APmin}" + "\n{/repeat:student}";
    System.out.println(template);
    template = templateProcessor.apply(template);
    System.out.println(template);
  }

  public static void main(String[] args)
  {
    new IfSample2();
  }
}
