package slogo.configuration;

import java.io.File;
import javafx.scene.paint.Color;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import slogo.view.MainView;

public class XMLWriter {
  private int numFile;

  private Color backgroundColor, penColor;
  private int numTurtles;
  private String turtleImageName;
  private String codeFileName;

  private DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
  private DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
  private Document document = documentBuilder.newDocument();

  private static final String xmlFilePath = "data/savedPreferenceFiles/";


  public XMLWriter(MainView mainView) throws Exception {
    backgroundColor = mainView.getDefaultBackgroundColor();
    penColor = mainView.getDefaultPenColor();
    numTurtles = mainView.getDefaultNumTurtles();
    turtleImageName = mainView.getDefaultTurtleFileName();
    codeFileName = mainView.getDefaultCodeFileName();
    numFile = 0;
  }

  private void addElements(){
    Element root = document.createElement("data");
    document.appendChild(root);
    Attr attr = document.createAttribute("media");
    attr.setValue("sim");
    root.setAttributeNode(attr);

    Element name = document.createElement("background");
    name.appendChild(document.createTextNode(backgroundColor.toString()));
    root.appendChild(name);

    Element myChoice = document.createElement("pen");
    myChoice.appendChild(document.createTextNode(penColor.toString()));
    root.appendChild(myChoice);

    Element author = document.createElement("numTurtles");
    author.appendChild(document.createTextNode(Integer.toString(numTurtles)));
    root.appendChild(author);

    Element islayout = document.createElement("turtleName");
    islayout.appendChild(document.createTextNode(turtleImageName));
    root.appendChild(islayout);

    Element rows = document.createElement("fileName");
    rows.appendChild(document.createTextNode(codeFileName));
    root.appendChild(rows);
  }

  private void writeFile() {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource domSource = new DOMSource(document);
      StreamResult streamResult = new StreamResult(new File(xmlFilePath+numFile+"_saved.xml"));
      transformer.transform(domSource, streamResult);
      System.out.println("Done creating XML File");
      numFile++;
    }
    catch (TransformerException tfe) {
      throw new XMLException("Transformation fault");
    }
  }

  /***
   * Call both the above methods to, first initialize
   * the xml tree structure, and then write it out to a file
   * @throws ParserConfigurationException : exceptions
   */
  public void outputFile() throws ParserConfigurationException {
    addElements();
    writeFile();  }





}
