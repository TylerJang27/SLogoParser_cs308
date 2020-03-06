package slogo.configuration;

import javafx.scene.paint.Color;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import slogo.view.MainView;

public class XMLWriter {

  private Color backgroundColor, penColor;
  private int numTurtles;
  private String turtleImageName;
  private String codeFileName;

  private DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
  //private DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
  //private Document document = documentBuilder.newDocument();

  private static final String xmlFilePath = "data/savedfiles/";

  /*
  public XMLWriter(MainView mainView) throws Exception {
    backgroundColor = mainView.getBackground();
    myGame = game;
    simName = game.getSimulationName();
    authName = game.getAuthor();
    choice = game.getMyChoice();
    myRows = game.getMyRows();
    myCols = game.getMyCols();
    myShape = game.getMyShape();
    try{
      myProb = game.getMyProb();
    }
    catch(Exception e){
      myProb = (float) BASE_VALUE;
      throw new Exception("Not a fire type simulation");
    }
    try{
      myThreshold = game.getMyThreshold();
    }
    catch(Exception e){
      myThreshold = BASE_VALUE;
      throw new Exception("Not a segregation or threshold type");
    }
    getLayout();
  }

   */



}
