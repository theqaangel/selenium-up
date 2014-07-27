package support.settings;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Settings {

  public Settings(String sourceFile) {
    try {

      Document settings = loadXMLStructure(sourceFile);
      NodeList nodes = settings.getElementsByTagName("settings");
      if(nodes.getLength() == 1)
      {
        Node node = nodes.item(0);
        Element element = (Element) node;
        String searchTimeoutValue = getValue("search-timeout", element);
        this.setSearchTimeout(Integer.parseInt(searchTimeoutValue));
      }
      else
      {
        System.out.println("Settings section does not exist in testconfig.xml");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String getValue(String tag, Element element) {
    NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
    Node node = (Node) nodes.item(0);
    return node.getNodeValue();
  }

  private Document loadXMLStructure(String sourceFile) throws ParserConfigurationException,
      SAXException, IOException {
    File file = new File(sourceFile);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(file);
    doc.getDocumentElement().normalize();

    return doc;
  }

  private int searchTimeout;

  public int getSearchTimeout() {
    return searchTimeout;
  }

  public void setSearchTimeout(int searchTimeout) {
    this.searchTimeout = searchTimeout;
  }
}
