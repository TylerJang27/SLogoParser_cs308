package slogo.backendexternal.parser;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Translator {
  private static final String RESOURCES_PACKAGE = Translator.class.getPackageName() + ".resources.";

  private String currentLanguage;
  private Map<String, List<String>> currentCommands;

  public Translator(){ this("English"); }

  public Translator(String language){
    currentLanguage = language;
    currentCommands = this.setLanguage(language);
  }

  public Map getCurrentCommands(){ return currentCommands; }

  public Map<String, List<String>> setLanguage(String language){
    Map translatedCommands = new HashMap<String, List<String>>();
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + language);
    for (String key : Collections.list(resources.getKeys())) {
      String translation = resources.getString(key);
      translatedCommands.put(key, Arrays.asList(translation.split("\\|")));
    }
    return translatedCommands;
  }

  public String translateCommand(String command, String language){
    Map<String, List<String>> newCommands = this.setLanguage(language);
    String formalKey = "";
    for (String key : currentCommands.keySet()) {
      if (currentCommands.get(key).contains(command)) {
        formalKey = key;
      }
    }
    try{
      return newCommands.get(formalKey).get(0);
    }catch(Exception e){
      return command;
    }
  }

}
