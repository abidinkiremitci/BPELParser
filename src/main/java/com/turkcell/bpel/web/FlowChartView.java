package com.turkcell.bpel.web;

import com.turkcell.bpel.model.BPELNode;
import com.turkcell.bpel.services.BPELFileParserService;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component("flowChartView")
@Scope("request")
public class FlowChartView {

    @Autowired
    private BPELFileParserService bpelFileParserService;

    private UploadedFile file;

    List<BPELNode> bpelNodeList = new ArrayList<>();

    public List<BPELNode> getBpelNodeList()
    {
        return bpelNodeList;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if(file != null) {
            try
            {
                InputStream inputstream = file.getInputstream();
                bpelNodeList = bpelFileParserService.parseBPELParser(inputstream);
                FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


}