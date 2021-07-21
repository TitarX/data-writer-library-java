//Создание и подготовка объекта, для создания xml-документа,
//содержащего объект ElementContent соответствующий корневому элементу xml-документа и его имя

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datawriter.xml.dom;

/**
 *
 * @author TitarX
 */
public final class DocumentContent
{

    private String rootElementName=null;
    private ElementContent rootElementContent=null;

    public DocumentContent(String rootElementName,ElementContent rootElementContent)
    {
        this.rootElementName=rootElementName;
        this.rootElementContent=rootElementContent;
    }

    public String getRootElementName()
    {
        return rootElementName;
    }

    public ElementContent getRootElementContent()
    {
        return rootElementContent;
    }
}
