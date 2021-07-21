//Создание и запись в файл xml-документа из поготовленного объекта DocumentContent

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datawriter.xml.dom;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author TitarX
 */
public class DomDataWriter
{

    public DomDataWriter()
    {
    }

    public void writeData(DocumentContent documentContent,String filePath) throws ParserConfigurationException, TransformerConfigurationException, TransformerException
    {
        String rootElementName=documentContent.getRootElementName();
        ElementContent rootElementContent=documentContent.getRootElementContent();

        Document xmlDocument=DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element rootElement=xmlDocument.createElement(rootElementName);
        xmlDocument.appendChild(rootElement);
        fillElement(xmlDocument,rootElement,rootElementContent);

        Transformer transformer=TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.setOutputProperty(OutputKeys.METHOD,"xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
        transformer.transform(new DOMSource(xmlDocument),new StreamResult(new File(filePath)));
    }

    private void fillElement(Document xmlDocument,Element element,ElementContent elementContent)
    {
        HashMap<String,Object> elementsHashMap=elementContent.getElementsHashMap();
        HashMap<String,String> attributesHashMap=elementContent.getAttributesHashMap();
        String textString=elementContent.getTextString();

        if(elementsHashMap!=null)
        {
            for(String elementKey:elementsHashMap.keySet())
            {
                Object newElementContent=elementsHashMap.get(elementKey);
                if(newElementContent instanceof ArrayList)
                {
                    for(Object newElemetContentItem:(ArrayList)newElementContent)
                    {
                        Element newElement=xmlDocument.createElement(elementKey);
                        element.appendChild(newElement);
                        fillElement(xmlDocument,newElement,(ElementContent)newElemetContentItem);
                    }
                }
                else
                {
                    Element newElement=xmlDocument.createElement(elementKey);
                    element.appendChild(newElement);
                    fillElement(xmlDocument,newElement,(ElementContent)newElementContent);
                }
            }
        }

        if(attributesHashMap!=null)
        {
            for(String attributeKey:attributesHashMap.keySet())
            {
                element.setAttribute(attributeKey,attributesHashMap.get(attributeKey));
            }
        }

        if(textString!=null)
        {
            Text newText=xmlDocument.createTextNode(textString);
            element.appendChild(newText);
        }
    }
}
