package com.turkcell.bpel.model;

import java.util.ArrayList;
import java.util.List;

public enum BPELNodeType
{
    INVOKE("invoke"),
    ASSIGN("assign"),
    RECEIVE("receive");

    public String getXmlNodeName()
    {
        return xmlNodeName;
    }

    String xmlNodeName;

    BPELNodeType(String xmlNodeName)
    {
        this.xmlNodeName = xmlNodeName;
    }

    public static List<BPELNodeType> getAllTypes() {

        ArrayList<BPELNodeType> bpelNodeTypes = new ArrayList<BPELNodeType>();
        for (BPELNodeType value : BPELNodeType.values())
        {
            bpelNodeTypes.add(value);
        }
        return bpelNodeTypes;
    }

    public static BPELNodeType getBPELNodeType(String xmlNodeName) {
        for (BPELNodeType value : BPELNodeType.values())
        {
            if(value.getXmlNodeName().equals(xmlNodeName)) {
                return value;
            }
        }
        return null;
    }
}
