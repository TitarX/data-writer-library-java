//Создание и подготовка объекта, для создания элемента xml-документа,
//содержащего объекты соответствующие его содержимому

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datawriter.xml.dom;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author TitarX
 */
public final class ElementContent
{

    //Ключ String - имя дочернего элемента;
    //Значение Object:
    //      ElementContent - объект дочернего элемета
    //      или
    //      ArrayList - массив дочерних элементов с одним именем
    private HashMap<String,Object> elementsHashMap=null;
    
    //Ключ String - имя атрибута;
    //Значение String - значение атрибута
    private HashMap<String,String> attributesHashMap=null;
    
    //Текстовое содержимое
    private String textString=null;

    //Установка коллекции объектов дочерних элементов,
    //с проверкой соответствия типов
    public void setElementsHashMap(HashMap<String,Object> elementsHashMap) throws ClassCastException
    {
        for(Object key:elementsHashMap.keySet())
        {
            if(!(key instanceof String))
            {
                throw new ClassCastException("ElementsHashMap key should be the type of String");
            }
        }

        for(Object value:elementsHashMap.values())
        {
            if(!(value instanceof ElementContent)&&!(value instanceof ArrayList))
            {
                throw new ClassCastException("ElementsHashMap value should be the type of ElementContent or ArrayList");
            }

            if(value instanceof ArrayList)
            {
                ArrayList valueArrayList=(ArrayList)value;
                for(Object element:valueArrayList)
                {
                    if(!(element instanceof ElementContent))
                    {
                        throw new ClassCastException("ArrayList value should be the type of ElementContent");
                    }
                }
            }
        }

        this.elementsHashMap=elementsHashMap;
    }

    public HashMap<String,Object> getElementsHashMap()
    {
        return elementsHashMap;
    }

    //Установка коллекции объектов атрибутов,
    //с проверкой соответствия типов
    public void setAttributesHashMap(HashMap<String,String> attributesHashMap) throws ClassCastException,Exception
    {
        for(Object key:attributesHashMap.keySet())
        {
            if(!(key instanceof String))
            {
                throw new ClassCastException("AttributesHashMap key should be the type of String");
            }
        }

        for(Object value:attributesHashMap.values())
        {
            if(!(value instanceof String))
            {
                throw new ClassCastException("AttributesHashMap value should be the type of String");
            }
        }

        this.attributesHashMap=attributesHashMap;
    }

    public HashMap<String,String> getAttributesHashMap()
    {
        return attributesHashMap;
    }

    public void setTextString(String textString)
    {
        this.textString=textString;
    }

    public String getTextString()
    {
        return textString;
    }
}
