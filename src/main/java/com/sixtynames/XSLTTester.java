package com.sixtynames;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XSLTTester {

    public static void main(String[] args) {
        try {
            // Apply the first XSLT transformation
            transformXML("src/main/resources/data.xml",
                    "src/main/resources/addRequestedAmount.xsl",
                    "src/main/resources/result/output1.xml");

            // Apply the second XSLT transformation
            transformXML("src/main/resources/data.xml",
                    "src/main/resources/addGuarantorDetails.xsl",
                    "src/main/resources/result/output2.xml");

            // Apply the third XSLT transformation
            transformXML("src/main/resources/data.xml",
                    "src/main/resources/addINNToLoaners.xsl",
                    "src/main/resources/result/output3.xml");

            System.out.println("Transformations completed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void transformXML(String xmlFile, String xsltFile, String outputFile) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File(xsltFile));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File(xmlFile));
        transformer.transform(text, new StreamResult(new File(outputFile)));
    }
}