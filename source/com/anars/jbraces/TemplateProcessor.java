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
package com.anars.jbraces;

import com.anars.jbraces.exceptions.ArrayNotFoundException;
import com.anars.jbraces.exceptions.AttributeNotFoundException;
import com.anars.jbraces.exceptions.NotArrayException;
import com.anars.jbraces.exceptions.ObjectNotFoundException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.lang.reflect.Array;

import java.net.URL;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class TemplateProcessor
{
  /**
   */
  public static final double VERSION = 0.4;

  /**
   */
  public static final long BUILD = 20140120;
  private static final String[] LATIN_WORDS =
  {
    //
    "abduco", "accipio", "acer", "acquiro", "addo", "adduco", "adeo", "adepto", "adfero", "adficio", //
    "adflicto", "adhuc", "aequitas", "aequus", "affero", "affligo", "agnosco", "alo", "altum", "alui", //
    "amo", "amplexus", "amplitudo", "animus", "antepono", "aperio", "arcis", "arguo", "arx", "attero", //
    "attollo", "autem", "autus", "auxilium", "bellus", "Berlinmonte", "bibo", "carcer", "casso", "celer", //
    "celeritas", "celeriter", "censura", "cito", "clarus", "coerceo", "cogito", "cohibeo", "comminuo", //
    "commisceo", "commodum", "concedo", "condico", "conor", "conservo", "consilium", "conspicio", //
    "constituo", "consulo", "consumo", "contineo", "corrumpo", "crebro", "cresco", "cretum", "cum", //
    "cupido", "cursim", "dare", "datum", "decorus", "decretum", "dedi", "defendo", "defero", "defluo", //
    "deinde", "denuncio", "detrimentum", "dexter", "dico", "dignitas", "dimitto", "directus", "diripio", //
    "discedo", "discessum", "discrepo", "do", "donum", "duco", "effero", "egredior", "egressus", "elatum", //
    "eo", "equitas", "equus", "excellentia", "exhibeo", "exibeo", "exigo", "exitus", "expleo", "expletum", //
    "explevi", "expugno", "extuli", "facina", "facio", "facultas", "facundia", "facunditas", "fateor", //
    "fatigo", "fatum", "faveo", "felicis", "felix", "ferus", "festinatio", "fidelis", "flatus", "fors", //
    "fortis", "fortuna", "fortunatus", "frequento", "gaudium", "gens", "grassor", "gregatim", "haesito", //
    "haud", "hesito", "honor", "iacio", "iam", "impedio", "impetus", "inanis", "inritus", "invado", "ire", //
    "irritus", "itum", "iudicium", "iustus", "jaculum", "judicium", "laedo", "laetificus", "laevus", "lamia", //
    "laqueus", "laudo", "ledo", "letificus", "leviculus", "levis", "levo", "levus", "liber", "libera", //
    "libero", "liberum", "macero", "macto", "malum", "maneo", "mansuetus", "maxime", "mereo", "modo", //
    "moris", "mos", "mundus", "narro", "nequaquam", "nimirum", "non", "nota", "novitas", "novus", "numerus", //
    "nunc", "nusquam", "nutrio", "nutus", "obduro", "ocius", "officium", "ordinatio", "pendo", "penus", //
    "percepi", "perceptum", "percipio", "perdo", "perfectus", "perscitus", "pertinax", "pessum", "placide", //
    "placidus", "placitum", "plenus", "plerusque", "plurimus", "poema", "poematis", "praeclarus", "praeda", //
    "praesto", "preclarus", "preda", "prehendo", "presto", "pretium", "priscus", "pristinus", "probo", //
    "proficio", "progredior", "progressus", "prohibeo", "promptus", "prope", "proventus", "pulcher", //
    "pulchra", "pulchrum", "puto", "qualitas", "qualiter", "quasi", "quies", "quietis", "ratio", "recolo", //
    "recordatio", "rectum", "rectus", "reddo", "regina", "rei", "relaxo", "renuntio", "repens", "reperio", //
    "repeto", "repleo", "reprehendo", "requiro", "res", "retineo", "rideo", "risi", "risum", "rubor", "rumor", //
    "salus", "saluto", "salutor", "sanctimonia", "sane", "sanitas", "sapiens", "sapienter", "sceleris", //
    "scelus", "scisco", "securus", "secus", "seditio", "sentio", "sepelio", "servo", "sicut", "silentium", //
    "similis", "singularis", "sino", "siquidem", "solus", "solvo", "somnium", "speciosus", "strenuus", //
    "subiungo", "subvenio", "succurro", "suffragium", "summopere", "super", "suscipio", "tamen", "tamquam", //
    "tanquam", "tectum", "tego", "tendo", "teneo", "tergum", "terra", "tersus", "texi", "totus", "tribuo", //
    "turpis", "universitas", "universum", "us", "usus", "utilis", "utilitas", "valde", "valens", "velociter", //
    "velox", "velut", "venia", "vergo", "videlicet", "video", "vidi", "vindico", "vires", "vis", "visum", //
    "vocis", "voco", "volo", "voluntas", "vomica", "vox"
    //
  };
  //
  // Source http://en.wikipedia.org/wiki/List_of_pangrams
  private static final String[] PANGRAM_SENTENCES =
  {
    //
    "Nymphs blitz quick vex dwarf jog",
    //
    "DJs flock by when MTV ax quiz prog",
    //
    "Big fjords vex quick waltz nymph",
    //
    "Bawds jog, flick quartz, vex nymph",
    //
    "Waltz job vexed quick frog nymphs",
    //
    "Junk MTV quiz graced by fox whelps",
    //
    "Bawds jog, flick quartz, vex nymphs",
    //
    "Waltz, bad nymph, for quick jigs vex!",
    //
    "Fox nymphs grab quick-jived waltz",
    //
    "Brick quiz whangs jumpy veldt fox",
    //
    "Glib jocks quiz nymph to vex dwarf",
    //
    "Bright vixens jump; dozy fowl quack",
    //
    "Vexed nymphs go for quick waltz job",
    //
    "Quick wafting zephyrs vex bold Jim",
    //
    "Quick zephyrs blow, vexing daft Jim",
    //
    "Quick blowing zephyrs vex daft Jim",
    //
    "Sphinx of black quartz, judge my vow",
    //
    "Sex-charged fop blew my junk TV quiz",
    //
    "Both fickle dwarves jinx my pig quiz",
    //
    "Fat hag dwarves quickly zap jinx mob",
    //
    "Hick dwarves jam blitzing foxy quip",
    //
    "Fox dwarves chop my talking quiz job",
    //
    "Public junk dwarves quiz mighty fox",
    //
    "Jack fox bids ivy-strewn phlegm quiz",
    //
    "How quickly daft jumping zebras vex",
    //
    "Two driven jocks help fax my big quiz",
    //
    "\"Now fax quiz Jack!\" my brave ghost pled",
    //
    "Jack, love my big wad of sphinx quartz!",
    //
    "Fickle jinx bog dwarves spy math quiz",
    //
    "Big dwarves heckle my top quiz of jinx",
    //
    "Fickle bog dwarves jinx empathy quiz",
    //
    "Public junk dwarves hug my quartz fox",
    //
    "Jumping hay dwarves flock quartz box",
    //
    "Five jumping wizards hex bolty quick",
    //
    "Five hexing wizard bots jump quickly",
    //
    "Quick fox jumps nightly above wizard",
    //
    "Vamp fox held quartz duck just by wing",
    //
    "Five quacking zephyrs jolt my wax bed",
    //
    "The five boxing wizards jump quickly",
    //
    "Jackdaws love my big sphinx of quartz",
    //
    "My jocks box, get hard, unzip, quiver, flow",
    //
    "Kvetching, flummoxed by job, W. zaps Iraq",
    //
    "My ex pub quiz crowd gave joyful thanks",
    //
    "Cozy sphinx waves quart jug of bad milk",
    //
    "A very bad quack might jinx zippy fowls",
    //
    "Pack my box with five dozen liquor jugs",
    //
    "Few quips galvanized the mock jury box",
    //
    "Quick brown fox jumps over the lazy dog",
    //
    "Jumpy halfling dwarves pick quartz box",
    //
    "Vex quest wizard, judge my backflop hand",
    //
    "The jay, pig, fox, zebra and my wolves quack!",
    //
    "Blowzy red vixens fight for a quick jump",
    //
    "Sex prof gives back no quiz with mild joy",
    //
    "The quick brown fox jumps over a lazy dog",
    //
    "A quick brown fox jumps over the lazy dog",
    //
    "Quest judge wizard bonks foxy chimp love",
    //
    "Boxers had zap of gay jock love, quit women",
    //
    "Joaquin Phoenix was gazed by MTV for luck",
    //
    "JCVD might pique a sleazy boxer with funk",
    //
    "Quizzical twins proved my hijack-bug fix",
    //
    "The quick brown fox jumps over the lazy dog",
    //
    "Waxy and quivering, jocks fumble the pizza",
    //
    "When zombies arrive, quickly fax judge Pat",
    //
    "Heavy boxes perform quick waltzes and jigs",
    //
    "A quick chop jolted my big sexy frozen wives",
    //
    "A wizard's job is to vex chumps quickly in fog",
    //
    "Sympathizing would fix Quaker objectives",
    //
    "Pack my red box with five dozen quality jugs",
    //
    "Quads of blowzy fjord ignite map vex'd chicks",
    //
    "Fake bugs put in wax jonquils drive him crazy",
    //
    "Watch \"Jeopardy!\", Alex Trebek's fun TV quiz game",
    //
    "GQ jock wears vinyl tuxedo for showbiz promo",
    //
    "The quick brown fox jumped over the lazy dogs",
    //
    "Who packed five dozen old quart jars in my box?",
    //
    "Woven silk pyjamas exchanged for blue quartz",
    //
    "Brawny gods just flocked up to quiz and vex him",
    //
    "Twelve ziggurats quickly jumped a finch box",
    //
    "Prating jokers quizzically vexed me with fibs",
    //
    "My faxed joke won a pager in the cable TV quiz show",
    //
    "The quick onyx goblin jumps over the lazy dwarf",
    //
    "The lazy major was fixing Cupid's broken quiver",
    //
    "Amazingly few discotheques provide jukeboxes",
    //
    "Foxy diva Jennifer Lopez wasn't baking my quiche",
    //
    "Cozy lummox gives smart squid who asks for job pen",
    //
    "By Jove, my quick study of lexicography won a prize",
    //
    "Painful zombies quickly watch a jinxed graveyard",
    //
    "Fax back Jim's Gwyneth Paltrow video quiz",
    //
    "My girl wove six dozen plaid jackets before she quit",
    //
    "Six big devils from Japan quickly forgot how to waltz",
    //
    "\"Who am taking the ebonics quiz?\", the prof jovially axed",
    //
    "Why shouldn't a quixotic Kazakh vampire jog barefoot?",
    //
    "Grumpy wizards make a toxic brew for the jovial queen",
    //
    "Sixty zips were quickly picked from the woven jute bag",
    //
    "Big July earthquakes confound zany experimental vow",
    //
    "Foxy parsons quiz and cajole the lovably dim wiki-girl",
    //
    "Cute, kind, jovial, foxy physique, amazing beauty? Wowser!",
    //
    "Have a pick: twenty six letters ? no forcing a jumbled quiz!",
    //
    "A very big box sailed up then whizzed quickly from Japan",
    //
    "Jack quietly moved up front and seized the big ball of wax",
    //
    "Few black taxis drive up major roads on quiet hazy nights",
    //
    "Just poets wax boldly as kings and queens march over fuzz",
    //
    "Bored? Craving a pub quiz fix? Why, just come to the Royal Oak!",
    //
    "Grumpy wizards make toxic brew for the evil Queen and Jack",
    //
    "Crazy Fredericka bought many very exquisite opal jewels",
    //
    "The job of waxing linoleum frequently peeves chintzy kids",
    //
    "Back in June we delivered oxygen equipment of the same size",
    //
    "Just keep examining every low bid quoted for zinc etchings",
    //
    "How razorback-jumping frogs can level six piqued gymnasts!",
    //
    "A quick movement of the enemy will jeopardize six gunboats",
    //
    "All questions asked by five watched experts amaze the judge",
    //
    "The wizard quickly jinxed the gnomes before they vapourized",
    //
    "Every good cow, fox, squirrel, and zebra likes to jump over happy dogs"
    //
  };
  //
  private static final String VO_NAME_CLASS_VERSION = "jb_class_version";
  private static final String VO_NAME_CLASS_BUILD = "jb_class_build";
  private static final String VO_NAME_LOCALE_CODE = "jb_locale_code";
  private static final String VO_NAME_LOCALE_NAME = "jb_locale_name";
  private static final String VO_NAME_COUNTRY_CODE = "jb_country_code";
  private static final String VO_NAME_COUNTRY_NAME = "jb_country_name";
  private static final String VO_NAME_LANGUAGE_CODE = "jb_language_code";
  private static final String VO_NAME_LANGUAGE_NAME = "jb_language_name";
  //
  private static final String LOOP = "loop";
  private static final String SHOW_RANDOMLY = "show-randomly";
  private static final String REPEAT = "repeat";
  private static final String RANDOM_NUMBER = "random-number";
  private static final String DRAW = "draw";
  private static final String DATE = "date";
  private static final String TIME = "time";
  private static final String GET = "get";
  private static final String SET = "set";
  private static final String PROPERTY = "property";
  private static final String LOREM_IPSUM = "lorem-ipsum";
  private static final String PANGRAM = "pangram";
  private static final String FORMAT = "format";
  private static final String IF = "if";
  //
  private Logger _logger = Logger.getLogger(getClass().getCanonicalName());
  //
  private Pattern _pattern = Pattern.compile(
    //
    "\\{" + LOOP + ":(\\w+)(:\\-?\\d+)?\\}.*?\\{/" + LOOP + ":\\1\\}|" + //
    "\\{" + SHOW_RANDOMLY + ":(\\w+)\\}.*?\\{/" + SHOW_RANDOMLY + ":\\3\\}|" + //
    "\\{" + REPEAT + ":(\\w+):\\d+(:\\d+)?\\}.*?\\{/" + REPEAT + ":\\4\\}|" + //
    "\\{" + RANDOM_NUMBER + ":\\-?\\d+:\\-?\\d+\\}|" + //
    "\\{" + DRAW + ":\\w+:\\w+(:\\w+)*\\}|" + //
    "\\{" + DATE + ":[GyMwWDdFE]+(:\\w{2}){0,2}\\}|" + //
    "\\{" + TIME + ":[aHkKhmsSzZ]+(:\\w{2}){0,2}\\}|" + //
    "\\{" + GET + ":\\w+((\\[\\d+\\])?(\\.\\w+)?|(\\.\\-value|\\.\\-offset|\\.\\-length|\\.\\-first|\\.\\-last))?\\}|" + //
    "\\{" + PROPERTY + ":[^}]*\\}|" + //
    "\\{" + LOREM_IPSUM + ":\\d+:\\d+\\}|" + //
    "\\{" + PANGRAM + ":\\d+:\\d+\\}|" + //
    "\\{" + SET + ":(\\w+(\\[\\d+\\])?)\\}.*?\\{/" + SET + ":\\13\\}|" + //
    "\\{" + FORMAT + ":(\\w+)(:\\w{2}){0,2}\\}.*?\\{/" + FORMAT + ":\\15\\}|" + //
    "\\{" + IF + ":(\\w+):((\\w+((\\[\\d+\\])?(\\.\\w+)|(\\.\\-value|\\.\\-offset|\\.\\-length|\\.\\-first|\\.\\-last))?)|" + //
    "([\'][^\']*[\'])):(equals|equals-ignore-case|not-equals|not-equals-ignore-case|greater-than|greater-than-or-equals|" + //
    "less-than|less-than-or-equals|empty|not-empty|exists|not-exists|even-number|odd-number)(:((\\w+((\\[\\d+\\])?(\\.\\w+)|" + //
    "(\\.\\-value|\\.\\-offset|\\.\\-length|\\.\\-first|\\.\\-last))?)|([\'][^\']*[\'])))?\\}.*?\\{/" + IF + ":\\17\\}", //
    Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
  private Locale _locale = null;
  private Hashtable<String, Object> _valueObjects = new Hashtable<String, Object>();
  private Hashtable<String, SpanFormatter> _spanFormatters = new Hashtable<String, SpanFormatter>();

  /**
   */
  public TemplateProcessor()
  {
    super();
    putValueObject(VO_NAME_CLASS_VERSION, VERSION);
    putValueObject(VO_NAME_CLASS_BUILD, BUILD);
    setLocale(Locale.getDefault());
  }

  /**
   * @param locale
   * @param valueObjects
   */
  public TemplateProcessor(Locale locale, Hashtable<String, Object> valueObjects)
  {
    this();
    setLocale(locale);
    setValueObjects(valueObjects);
  }

  /**
   * @param locale
   */
  public TemplateProcessor(Locale locale)
  {
    this();
    setLocale(locale);
  }

  /**
   * @param valueObjects
   */
  public TemplateProcessor(Hashtable<String, Object> valueObjects)
  {
    this();
    setValueObjects(valueObjects);
  }

  /**
   * @param valueObjects
   */
  public synchronized void setValueObjects(Hashtable<String, Object> valueObjects)
  {
    _valueObjects = valueObjects;
  }

  /**
   * @return
   */
  public synchronized Hashtable<String, Object> getValueObjects()
  {
    return (_valueObjects);
  }

  /**
   */
  public synchronized void clearValueObjects()
  {
    _valueObjects.clear();
  }

  /**
   * @param name
   * @param valueObject
   * @return
   */
  public synchronized Object putValueObject(String name, Object valueObject)
  {
    if (valueObject == null)
      return (null);
    if (!valueObject.getClass().isArray())
      return (_valueObjects.put(name.toLowerCase(), valueObject));
    if (valueObject instanceof Object[])
      return (valueObject != null ? _valueObjects.put(name.toLowerCase(), valueObject) : null);
    int arrayLength = Array.getLength(valueObject);
    Object[] outputArray = new Object[arrayLength];
    for (int index = 0; index < arrayLength; index++)
      outputArray[index] = Array.get(valueObject, index);
    return (_valueObjects.put(name.toLowerCase(), outputArray));
  }

  /**
   * @param name
   * @return
   */
  public synchronized Object getValueObject(String name)
  {
    return (_valueObjects.get(name.toLowerCase()));
  }

  /**
   * @param name
   * @return
   */
  public synchronized Object removeValueObject(String name)
  {
    return (_valueObjects.remove(name.toLowerCase()));
  }

  /**
   * @return
   */
  public synchronized boolean hasAnyValueObjects()
  {
    return (!_valueObjects.isEmpty());
  }

  /**
   * @return
   */
  public synchronized int valueObjectCount()
  {
    return (_valueObjects.size());
  }

  /**
   * @param name
   * @return
   */
  public synchronized boolean containsValueObjectName(String name)
  {
    return (_valueObjects.containsKey(name.toLowerCase()));
  }

  /**
   * @param valueObject
   * @return
   */
  public synchronized boolean containsValueObject(Object valueObject)
  {
    return (_valueObjects.containsValue(valueObject));
  }

  /**
   * @return
   */
  public synchronized Enumeration<String> valueObjectNames()
  {
    return (_valueObjects.keys());
  }

  /**
   * @return
   */
  public synchronized Collection<Object> valueObjects()
  {
    return (_valueObjects.values());
  }

  /**
   */
  public synchronized void clearSpanFormatters()
  {
    _spanFormatters.clear();
  }

  /**
   * @param name
   * @param spanFormatter
   * @return
   */
  public synchronized SpanFormatter putSpanFormatter(String name, SpanFormatter spanFormatter)
  {
    return (_spanFormatters.put(name, spanFormatter));
  }

  /**
   * @param name
   * @return
   */
  public synchronized SpanFormatter getSpanFormatter(String name)
  {
    return (_spanFormatters.get(name));
  }

  /**
   * @param name
   * @return
   */
  public synchronized SpanFormatter removeSpanFormatter(String name)
  {
    return (_spanFormatters.remove(name));
  }

  /**
   * @return
   */
  public synchronized boolean hasAnySpanFormatters()
  {
    return (!_spanFormatters.isEmpty());
  }

  /**
   * @return
   */
  public synchronized int spanFormatterCount()
  {
    return (_spanFormatters.size());
  }

  /**
   * @param name
   * @return
   */
  public synchronized boolean containsSpanFormatterName(String name)
  {
    return (_spanFormatters.containsKey(name));
  }

  /**
   * @param spanFormatter
   * @return
   */
  public synchronized boolean containsSpanFormatter(SpanFormatter spanFormatter)
  {
    return (_spanFormatters.containsValue(spanFormatter));
  }

  /**
   * @return
   */
  public synchronized Enumeration<String> spanFormatterNames()
  {
    return (_spanFormatters.keys());
  }

  /**
   * @return
   */
  public synchronized Collection<SpanFormatter> spanFormatters()
  {
    return (_spanFormatters.values());
  }

  /**
   * @param locale
   */
  public void setLocale(Locale locale)
  {
    _locale = locale;
    putValueObject(VO_NAME_LOCALE_CODE, locale.toString());
    putValueObject(VO_NAME_LOCALE_NAME, locale.getDisplayName(locale));
    putValueObject(VO_NAME_COUNTRY_CODE, locale.getCountry());
    putValueObject(VO_NAME_COUNTRY_NAME, locale.getDisplayCountry(locale));
    putValueObject(VO_NAME_LANGUAGE_CODE, locale.getLanguage());
    putValueObject(VO_NAME_LANGUAGE_NAME, locale.getDisplayLanguage(locale));
  }

  /**
   * @return
   */
  public Locale getLocale()
  {
    return (_locale);
  }

  /**
   * @param templateFile
   * @return
   * @throws IOException
   * @throws FileNotFoundException
   */
  public String apply(File templateFile)
    throws IOException, FileNotFoundException
  {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(templateFile));
    StringBuffer stringBuffer = new StringBuffer();
    String lineSeparator = System.getProperty("line.separator");
    try
    {
      String lineString = bufferedReader.readLine();
      while (lineString != null)
      {
        stringBuffer.append(lineString);
        stringBuffer.append(lineSeparator);
        lineString = bufferedReader.readLine();
      }
    }
    finally
    {
      bufferedReader.close();
    }
    return (apply(stringBuffer.toString()));
  }

  /**
   * @param templateString
   * @return
   */
  public String apply(String templateString)
  {
    Matcher matcher = _pattern.matcher(templateString);
    StringBuffer stringBuffer = new StringBuffer();
    while (matcher.find())
    {
      String matchedString = substring(matcher.group(), "{", "}", false);
      String replacement = null;
      String[] pieces = matchedString.split(":");
      if (pieces[0].equals(RANDOM_NUMBER))
      {
        try
        {
          int startNumber = Integer.parseInt(pieces[1]);
          replacement = "" + ((int) (Math.random() * (Integer.parseInt(pieces[2]) - startNumber + 1)) + startNumber);
        }
        catch (Exception exception)
        {
          _logger.log(Level.SEVERE, "An error occurred while getting \"{" + matchedString + "}\".", exception);
        }
      }
      else if (pieces[0].equals(LOREM_IPSUM))
      {
        int minSentences = 1;
        int maxSentences = 1;
        try
        {
          minSentences = Integer.parseInt(pieces[1]);
        }
        catch (Exception exception)
        {
          _logger.log(Level.SEVERE, "An error occurred while getting \"{" + matchedString + "}\".", exception);
        }
        try
        {
          maxSentences = Integer.parseInt(pieces[2]);
        }
        catch (Exception exception)
        {
          maxSentences = minSentences;
          _logger.log(Level.SEVERE, "An error occurred while getting \"{" + matchedString + "}\".", exception);
        }
        int sentences = (int) (Math.random() * (maxSentences - minSentences + 1)) + minSentences;
        StringBuffer stringBufferParagraph = new StringBuffer("Lorem ipsum");
        for (int sentenceIndex = 0; sentenceIndex < sentences; sentenceIndex++)
        {
          int wordCount = (int) (Math.random() * 6) + 3;
          for (int wordIndex = 0; wordIndex < wordCount; wordIndex++)
          {
            String word = LATIN_WORDS[(int) (Math.random() * LATIN_WORDS.length)];
            if (wordIndex == 0 && sentenceIndex != 0)
              stringBufferParagraph.append(" " + word.substring(0, 1).toUpperCase() + word.substring(1));
            else
              stringBufferParagraph.append(" " + word);
          }
          switch ((int) Math.random() * 10)
          {
            case 8:
              stringBufferParagraph.append("!");
              break;
            case 9:
              stringBufferParagraph.append("?");
              break;
            default:
              stringBufferParagraph.append(".");
              break;
          }
        }
        replacement = stringBufferParagraph.toString();
      }
      else if (pieces[0].equals(PANGRAM))
      {
        int minSentences = 1;
        int maxSentences = 1;
        try
        {
          minSentences = Integer.parseInt(pieces[1]);
        }
        catch (Exception exception)
        {
          _logger.log(Level.SEVERE, "An error occurred while getting \"{" + matchedString + "}\".", exception);
        }
        try
        {
          maxSentences = Integer.parseInt(pieces[2]);
        }
        catch (Exception exception)
        {
          maxSentences = minSentences;
          _logger.log(Level.SEVERE, "An error occurred while getting \"{" + matchedString + "}\".", exception);
        }
        int sentences = (int) (Math.random() * (maxSentences - minSentences + 1)) + minSentences;
        StringBuffer stringBufferParagraph = new StringBuffer();
        for (int sentenceIndex = 0; sentenceIndex < sentences; sentenceIndex++)
        {
          int sentence = (int) (Math.random() * PANGRAM_SENTENCES.length);
          stringBufferParagraph.append(PANGRAM_SENTENCES[sentence]);
          if (!PANGRAM_SENTENCES[sentence].endsWith("!") && !PANGRAM_SENTENCES[sentence].endsWith("?"))
            stringBufferParagraph.append(".");
          if (sentenceIndex + 1 < sentences)
            stringBufferParagraph.append(" ");
        }
        replacement = stringBufferParagraph.toString();
      }
      else if (pieces[0].equals(DATE) || pieces[0].equals(TIME))
      {
        Locale locale = _locale;
        if (pieces.length == 3)
          locale = new Locale(pieces[2]);
        else if (pieces.length == 4)
          locale = new Locale(pieces[2], pieces[3]);
        try
        {
          replacement = (new SimpleDateFormat(pieces[1], locale)).format(new Date());
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
          _logger.log(Level.SEVERE, "Invalid date and time format; \"" + pieces[1] + "\".", illegalArgumentException);
        }
        catch (Exception exception)
        {
          _logger.log(Level.SEVERE, "An error occurred while getting \"{" + matchedString + "}\".", exception);
        }
      }
      else if (pieces[0].equals(GET))
      {
        try
        {
          Object object = getObjectValue(pieces[1]);
          replacement = object.toString();
        }
        catch (ObjectNotFoundException objectNotFoundException)
        {
          _logger.log(Level.SEVERE, "Object Not Found", objectNotFoundException);
        }
        catch (AttributeNotFoundException attributeNotFoundException)
        {
          _logger.log(Level.SEVERE, "Attribute Not Found", attributeNotFoundException);
        }
        catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException)
        {
          _logger.log(Level.SEVERE, "Array Index Out Of Bound", arrayIndexOutOfBoundsException);
        }
        catch (NotArrayException notArrayException)
        {
          _logger.log(Level.SEVERE, "Not An Array", notArrayException);
        }
        catch (ArrayNotFoundException arrayNotFoundException)
        {
          _logger.log(Level.SEVERE, "Array Not Found", arrayNotFoundException);
        }
        catch (NullPointerException nullPointerException)
        {
          _logger.log(Level.SEVERE, "Null Pointer", nullPointerException);
        }
        catch (Exception exception)
        {
          _logger.log(Level.SEVERE, "Exception", exception);
        }
      }
      else if (pieces[0].equals(LOOP))
      {
        try
        {
          int increment = 1;
          if (pieces.length == 3)
            increment = Integer.parseInt(pieces[2]);
          Object[] object = getArrayObject(pieces[1]);
          String loopTemplate = substring(matcher.group(), "}", "{/");
          replacement = "";
          if (increment > 0)
            for (int index = 0; index < object.length; index += increment)
            {
              putValueObject(pieces[1] + "-offset", index + 1);
              putValueObject(pieces[1] + "-first", (index == 0));
              putValueObject(pieces[1] + "-last", (index + 1 >= object.length));
              putValueObject(pieces[1] + "-value", object[index]);
              putValueObject(pieces[1], object[index]);
              replacement += apply(loopTemplate);
            }
          else if (increment < 0)
            for (int index = object.length - 1; index >= 0; index += increment)
            {
              putValueObject(pieces[1] + "-offset", index + 1);
              putValueObject(pieces[1] + "-first", (index == object.length - 1));
              putValueObject(pieces[1] + "-last", (index == 0));
              putValueObject(pieces[1] + "-value", object[index]);
              putValueObject(pieces[1], object[index]);
              replacement += apply(loopTemplate);
            }
          removeValueObject(pieces[1] + "-offset");
          removeValueObject(pieces[1] + "-first");
          removeValueObject(pieces[1] + "-last");
          removeValueObject(pieces[1] + "-value");
          putValueObject(pieces[1], object);
        }
        catch (NumberFormatException numberFormatException)
        {
          _logger.log(Level.SEVERE, "Invalid Number", numberFormatException);
        }
        catch (NotArrayException notArrayException)
        {
          _logger.log(Level.SEVERE, "Not An Array", notArrayException);
        }
        catch (ArrayNotFoundException arrayNotFoundException)
        {
          _logger.log(Level.SEVERE, "Array Not Found", arrayNotFoundException);
        }

        catch (Exception exception)
        {
          _logger.log(Level.SEVERE, "Exception", exception);
        }
      }
      else if (pieces[0].equals(IF))
      {
        boolean condition = false;
        String ifBlock = substring(matcher.group(), "}", "{/");
        if (pieces[3].equals("exists") || pieces[3].equals("not-exists"))
        {
          try
          {
            getObjectValue(pieces[2]);
            condition = pieces[3].equals("exists");
          }
          catch (Exception exception)
          {
            condition = pieces[3].equals("not-exists");
          }
        }
        else
        {
          try
          {
            String leftSideStr = "";
            if (pieces[2].startsWith("'") && pieces[2].endsWith("'"))
            {
              leftSideStr = pieces[2].substring(1).substring(0, pieces[2].length() - 2);
            }
            else
            {
              Object leftSideObj = getObjectValue(pieces[2]);
              leftSideStr = leftSideObj == null ? "" : leftSideObj.toString();
            }
            String rightSideStr = "";
            if (pieces.length == 5)
              if (pieces[4].startsWith("'") && pieces[4].endsWith("'"))
              {
                rightSideStr = pieces[4].substring(1).substring(0, pieces[4].length() - 2);
              }
              else
              {
                Object rightSideObj = getObjectValue(pieces[4]);
                rightSideStr = rightSideObj == null ? "" : rightSideObj.toString();
              }
            if (pieces[3].equals("equals"))
            {
              condition = leftSideStr.equals(rightSideStr);
            }
            else if (pieces[3].equals("not-equals"))
            {
              condition = !leftSideStr.equals(rightSideStr);
            }
            else if (pieces[3].equals("equals-ignore-case"))
            {
              condition = leftSideStr.equalsIgnoreCase(rightSideStr);
            }
            else if (pieces[3].equals("not-equals-ignore-case"))
            {
              condition = !leftSideStr.equalsIgnoreCase(rightSideStr);
            }
            else if (pieces[3].equals("empty"))
            {
              condition = leftSideStr.equals("");
            }
            else if (pieces[3].equals("not-empty"))
            {
              condition = !leftSideStr.equals("");
            }
            else
            {
              int leftSideInt = 0;
              int rightSideInt = 0;
              try
              {
                if (!leftSideStr.equals(""))
                  leftSideInt = Integer.parseInt(leftSideStr);
              }
              catch (NumberFormatException numberFormatException)
              {
                _logger.log(Level.SEVERE, "Invalid Number", numberFormatException);
              }
              catch (Exception exception)
              {
                _logger.log(Level.SEVERE, "Exception", exception);
              }
              try
              {
                if (!rightSideStr.equals(""))
                  rightSideInt = Integer.parseInt(rightSideStr);
              }
              catch (NumberFormatException numberFormatException)
              {
                _logger.log(Level.SEVERE, "Invalid Number", numberFormatException);
              }
              catch (Exception exception)
              {
                _logger.log(Level.SEVERE, "Exception", exception);
              }
              if (pieces[3].equals("greater-than"))
              {
                condition = leftSideInt > rightSideInt;
              }
              else if (pieces[3].equals("greater-than-or-equals"))
              {
                condition = leftSideInt >= rightSideInt;
              }
              else if (pieces[3].equals("less-than"))
              {
                condition = leftSideInt < rightSideInt;
              }
              else if (pieces[3].equals("less-than-or-equals"))
              {
                condition = leftSideInt <= rightSideInt;
              }
              else if (pieces[3].equals("even-number"))
              {
                condition = leftSideInt % 2 == 0 && leftSideInt != 0;
              }
              else if (pieces[3].equals("odd-number"))
              {
                condition = leftSideInt % 2 == 1 && leftSideInt != 0;
              }
            }
          }
          catch (ObjectNotFoundException objectNotFoundException)
          {
            _logger.log(Level.SEVERE, "Object Not Found", objectNotFoundException);
          }
          catch (AttributeNotFoundException attributeNotFoundException)
          {
            _logger.log(Level.SEVERE, "Attribute Not Found", attributeNotFoundException);
          }
          catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException)
          {
            _logger.log(Level.SEVERE, "Array Index Out Of Bound", arrayIndexOutOfBoundsException);
          }
          catch (NotArrayException notArrayException)
          {
            _logger.log(Level.SEVERE, "Not An Array", notArrayException);
          }
          catch (ArrayNotFoundException arrayNotFoundException)
          {
            _logger.log(Level.SEVERE, "Array Not Found", arrayNotFoundException);
          }
          catch (NullPointerException nullPointerException)
          {
            _logger.log(Level.SEVERE, "Null Pointer", nullPointerException);
          }
          catch (Exception exception)
          {
            _logger.log(Level.SEVERE, "Exception", exception);
          }
        }
        replacement = condition ? apply(ifBlock) : "";
      }
      else if (pieces[0].equals(SHOW_RANDOMLY))
      {
        String randomlyBlock = substring(matcher.group(), "}", "{/");
        replacement = (int) (Math.random() * 2.0) == 1 ? apply(randomlyBlock) : "";
      }
      else if (pieces[0].equals(DRAW))
      {
        replacement = pieces[1 + (int) (Math.random() * pieces.length - 1)];
      }
      else if (pieces[0].equals(REPEAT))
      {
        int repeatTimes = 1;
        try
        {
          if (pieces.length == 4)
          {
            int startNumber = repeatTimes = Integer.parseInt(pieces[2]);
            repeatTimes = ((int) (Math.random() * (Integer.parseInt(pieces[3]) - startNumber + 1)) + startNumber);
          }
          else
          {
            repeatTimes = Integer.parseInt(pieces[2]);
          }
        }
        catch (Exception exception)
        {
          _logger.log(Level.SEVERE, "An error occurred while getting \"{" + matchedString + "}\".", exception);
        }
        String repeatBlock = substring(matcher.group(), "}", "{/");
        replacement = "";
        putValueObject(pieces[1], "");
        putValueObject(pieces[1] + "-length", repeatTimes);
        for (int index = 0; index < repeatTimes; index++)
        {
          putValueObject(pieces[1] + "-offset", index + 1);
          putValueObject(pieces[1] + "-first", (index == 0));
          putValueObject(pieces[1] + "-last", (index == repeatTimes - 1));
          putValueObject(pieces[1] + "-value", index + 1);
          replacement += apply(repeatBlock);
        }
        removeValueObject(pieces[1] + "-length");
        removeValueObject(pieces[1] + "-offset");
        removeValueObject(pieces[1] + "-first");
        removeValueObject(pieces[1] + "-last");
        removeValueObject(pieces[1] + "-value");
        removeValueObject(pieces[1]);
      }
      else if (pieces[0].equals(PROPERTY))
      {
        replacement = System.getProperty(pieces[1], "");
      }
      else if (pieces[0].equals(FORMAT))
      {
        try
        {
          Locale locale = _locale;
          if (pieces.length == 3)
            locale = new Locale(pieces[2]);
          else if (pieces.length == 4)
            locale = new Locale(pieces[2], pieces[3]);
          SpanFormatter spanFormatter = _spanFormatters.get(pieces[1]);
          if (spanFormatter != null)
          {
            String spanTemplate = substring(matcher.group(), "}", "{/");
            replacement = spanFormatter.format(apply(spanTemplate), locale);
          }
          else
          {
            _logger.log(Level.SEVERE, "Unable to find array \"" + pieces[1] + "\".");
          }
        }
        catch (NumberFormatException numberFormatException)
        {
          _logger.log(Level.SEVERE, "Invalid Number", numberFormatException);
        }
        catch (Exception exception)
        {
          _logger.log(Level.SEVERE, "Exception", exception);
        }
      }
      else if (pieces[0].equals(SET))
      {
        if (!pieces[1].equalsIgnoreCase(VO_NAME_CLASS_VERSION) && !pieces[1].equalsIgnoreCase(VO_NAME_CLASS_BUILD) && //
          !pieces[1].equalsIgnoreCase(VO_NAME_LOCALE_CODE) && !pieces[1].equalsIgnoreCase(VO_NAME_LOCALE_NAME) && //
          !pieces[1].equalsIgnoreCase(VO_NAME_COUNTRY_CODE) && !pieces[1].equalsIgnoreCase(VO_NAME_COUNTRY_NAME) && //
          !pieces[1].equalsIgnoreCase(VO_NAME_LANGUAGE_CODE) && !pieces[1].equalsIgnoreCase(VO_NAME_LANGUAGE_NAME))
        {
          String setValue = apply(substring(matcher.group(), "}", "{/"));
          int leftBracket = pieces[1].indexOf("[");
          int rightBracket = pieces[1].indexOf("]", leftBracket);
          if (leftBracket != -1 && rightBracket != -1)
          {
            String arrayName = pieces[1].substring(0, leftBracket);
            int arrayOffset = 0;
            Object[] objectArray = null;
            try
            {
              arrayOffset = Integer.parseInt(pieces[1].substring(leftBracket + 1, rightBracket));
              try
              {
                objectArray = getArrayObject(arrayName);
              }
              catch (ArrayNotFoundException arrayNotFoundException)
              {
                objectArray = new Object[0];
              }
              if (arrayOffset == 0)
              {
                objectArray = expandArray(objectArray, 1);
                objectArray[objectArray.length - 1] = setValue;
              }
              else
              {
                if (arrayOffset > objectArray.length)
                  objectArray = expandArray(objectArray, arrayOffset - objectArray.length);
                objectArray[arrayOffset - 1] = setValue;
              }
              putValueObject(arrayName, objectArray);
            }
            catch (NumberFormatException numberFormatException)
            {
              _logger.log(Level.SEVERE, "Invalid Number", numberFormatException);
            }
            catch (NotArrayException notArrayException)
            {
              _logger.log(Level.SEVERE, "Not An Array", notArrayException);
            }
            catch (Exception exception)
            {
              _logger.log(Level.SEVERE, "Exception", exception);
            }
          }
          else
          {
            putValueObject(pieces[1], setValue);
          }

        }
        else if (pieces[1].equalsIgnoreCase(VO_NAME_LOCALE_CODE))
        {
          String localeString = substring(matcher.group(), "}", "{/").trim();
          if (localeString.length() == 0)
            setLocale(Locale.getDefault());
          else if (localeString.length() == 2)
            setLocale(new Locale(localeString));
          else if (localeString.length() == 5 && localeString.charAt(2) == '_')
            setLocale(new Locale(localeString.substring(0, 2), localeString.substring(3)));
        }
      }
      matcher.appendReplacement(stringBuffer, replacement == null ? "" : replacement);
      replacement = null;
    }
    matcher.appendTail(stringBuffer);
    matcher = null;
    String output = stringBuffer.toString();
    if (_pattern.matcher(output).find())
      output = apply(output);
    return (output);
  }

  private Object[] getArrayObject(String name)
    throws ArrayNotFoundException, NotArrayException
  {
    try
    {
      Object[] objArray = (Object[]) _valueObjects.get(name.toLowerCase());
      if (objArray == null)
        throw new ArrayNotFoundException(name);
      return (objArray);
    }
    catch (ClassCastException classCastException)
    {
      throw new NotArrayException(name);
    }
  }

  private Object getObjectValue(String name)
    throws ArrayNotFoundException, NotArrayException, ArrayIndexOutOfBoundsException, ObjectNotFoundException, AttributeNotFoundException
  {
    String[] pieces = name.split("[.]");
    Object object = null;
    if (pieces[0].endsWith("]"))
    {
      String arrayName = null;
      int index = 0;
      try
      {
        arrayName = pieces[0].substring(0, pieces[0].indexOf("["));
        index = Integer.parseInt(pieces[0].substring(pieces[0].indexOf("[") + 1, pieces[0].length() - 1)) - 1;
        Object[] objectArray = getArrayObject(arrayName);
        if (index < 0)
          index = (int) (Math.random() * objectArray.length);
        object = objectArray[index];
      }
      catch (IndexOutOfBoundsException indexOutOfBoundsException)
      {
        throw new ArrayIndexOutOfBoundsException("" + index);
      }
    }
    else
    {
      object = _valueObjects.get(pieces[0].toLowerCase());
    }
    if (object == null)
    {
      throw new ObjectNotFoundException(pieces[0]);
    }
    else if (pieces.length == 2)
    {
      if (pieces[1].toLowerCase().equals("-length"))
      {
        Object offsetValue = _valueObjects.get(pieces[0].toLowerCase() + "-length");
        return (offsetValue == null ? getArrayObject(pieces[0]).length : offsetValue);
      }
      else if (pieces[1].toLowerCase().equals("-offset") || pieces[1].toLowerCase().equals("-value"))
      {
        Object offsetValue = _valueObjects.get(pieces[0].toLowerCase() + pieces[1].toLowerCase());
        return (offsetValue == null ? 0 : offsetValue);
      }
      else if (pieces[1].toLowerCase().equals("-first") || pieces[1].toLowerCase().equals("-last"))
      {
        return (((Boolean) _valueObjects.get(pieces[0].toLowerCase() + pieces[1].toLowerCase())).booleanValue());
      }
      else
      {
        boolean found = false;
        try
        {
          if (object instanceof Properties)
          {
            Properties properties = (Properties) object;
            object = properties.getProperty(pieces[1]);
            found = object != null;
          }
          else
          {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (int index = 0; index < descriptors.length && !found; index++)
              if (descriptors[index].getName().equals(pieces[1]))
              {
                object = descriptors[index].getReadMethod().invoke(object);
                found = true;
              }
          }
        }
        catch (Exception exception)
        {
          _logger.log(Level.SEVERE, "Exception", exception);
        }
        if (!found)
          throw new AttributeNotFoundException("Attribute :" + pieces[1] + " in " + name);
      }
    }
    return (object);
  }

  /**
   */
  public void loadAllSpanFormatters()
  {
    loadAllSpanFormatters(null);
  }

  /**
   * @param rootPackageName
   */
  public void loadAllSpanFormatters(String rootPackageName)
  {
    try
    {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      if (rootPackageName == null)
        rootPackageName = "com.anars.jbraces.formatters";
      else
        rootPackageName = rootPackageName.trim();
      String path = rootPackageName.replace('.', '/');
      Enumeration<URL> resources = classLoader.getResources(path);
      List<File> directories = new ArrayList<File>();
      while (resources.hasMoreElements())
        directories.add(new File(resources.nextElement().getFile()));
      ArrayList<Class> classes = new ArrayList<Class>();
      for (File directory : directories)
        classes.addAll(findClasses(directory, rootPackageName));
      for (Class formatterClass : classes)
      {
        String formatterName = formatterClass.getSimpleName();
        if (formatterName.endsWith("SpanFormatter"))
          formatterName = formatterName.substring(0, formatterName.length() - 13);
        putSpanFormatter(formatterName, (SpanFormatter) formatterClass.newInstance());
      }
    }
    catch (Exception exception)
    {
      _logger.log(Level.SEVERE, "Exception", exception);
    }
  }

  private List<Class> findClasses(File directory, String packageName)
    throws ClassNotFoundException
  {
    List<Class> classList = new ArrayList<Class>();
    if (!directory.exists())
      return classList;
    File[] files = directory.listFiles();
    for (File file : files)
      if (file.isDirectory())
      {
        if (packageName.startsWith("."))
          packageName = packageName.substring(1);
        classList.addAll(findClasses(file, packageName + "." + file.getName()));
      }
      else if (file.getName().endsWith(".class"))
      {
        Class cls = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
        if (cls.getSuperclass().getName().equals("com.anars.jbraces.SpanFormatter"))
          classList.add(cls);
      }
    return (classList);
  }

  private String substring(String string, String start, String end)
  {
    return (substring(string, start, end, true));
  }

  private String substring(String string, String start, String end, boolean lastIndex)
  {
    String substring = string;
    substring = substring.substring(substring.indexOf(start) + 1);
    substring = substring.substring(0, lastIndex ? substring.lastIndexOf(end) : substring.indexOf(end));
    return (substring);
  }

  private Object[] expandArray(Object[] OriginalArray, int size)
  {
    Object[] newArray = new Object[OriginalArray.length + size];
    System.arraycopy(OriginalArray, 0, newArray, 0, OriginalArray.length);
    return (newArray);
  }
}
