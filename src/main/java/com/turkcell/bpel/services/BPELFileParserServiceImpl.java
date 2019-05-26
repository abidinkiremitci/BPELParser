package com.turkcell.bpel.services;

import com.turkcell.bpel.model.BPELNode;
import com.turkcell.bpel.model.BPELNodeType;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class BPELFileParserServiceImpl implements BPELFileParserService
{
    @Override
    public List<BPELNode> parseBPELParser(InputStream inputstream)
    {
        List<BPELNode> bpelNodeList = new ArrayList<>();
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputstream);


            NodeList nodeList = doc.getElementsByTagName("process");
            parseBPELFileRecursively(nodeList, bpelNodeList);

        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return bpelNodeList;
    }

    private void parseBPELFileRecursively(NodeList childNodes, List<BPELNode> bpelNodeList)
    {
        if (childNodes != null)
        {
            for (int childTemp = 0; childTemp < childNodes.getLength(); childTemp++)
            {
                Node childNode = childNodes.item(childTemp);
                if (childNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element childElement = (Element) childNode;
                    String childNodeElement = childElement.getNodeName();
                    String childNodeElementName = childElement.getAttribute("name");

                    addBPELNode(bpelNodeList, childNodeElement, childNodeElementName);

                    parseBPELFileRecursively(childNode.getChildNodes(), bpelNodeList);
                }
            }
        }
    }

    private void addBPELNode(List<BPELNode> bpelNodeList, String nodeElement, String nodeElementName)
    {
        BPELNodeType bpelNodeType = BPELNodeType.getBPELNodeType(nodeElement);
        if (BPELNodeType.getAllTypes().contains(bpelNodeType))
        {
            BPELNode bpelNode = new BPELNode();
            bpelNode.setName(nodeElementName);
            bpelNode.setType(bpelNodeType);
            bpelNodeList.add(bpelNode);
        }
    }


}
