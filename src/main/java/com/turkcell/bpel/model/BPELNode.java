package com.turkcell.bpel.model;

import java.util.Objects;
import java.util.UUID;

public class BPELNode
{
    UUID id;

    BPELNodeType type;

    String name;

    Integer callCount = null;

    public BPELNode()
    {
        id = UUID.randomUUID();
    }

    public UUID getId()
    {
        return id;
    }

    public BPELNodeType getType()
    {
        return type;
    }

    public void setType(BPELNodeType type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getCallCount()
    {
        return callCount;
    }

    public void setCallCount(Integer callCount)
    {
        this.callCount = callCount;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BPELNode bpelNode = (BPELNode) o;
        return type == bpelNode.type &&
                name.equals(bpelNode.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(type, name);
    }
}
