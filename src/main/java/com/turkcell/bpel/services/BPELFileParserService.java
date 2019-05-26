package com.turkcell.bpel.services;

import com.turkcell.bpel.model.BPELNode;

import java.io.InputStream;
import java.util.List;

public interface BPELFileParserService
{
    List<BPELNode> parseBPELParser(InputStream inputstream);
}
