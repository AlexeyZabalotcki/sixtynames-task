package com.sixtynames;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DataValidator {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("src/main/resources/data.xml");

        validateAge(document);
        validateLoanAmountAndTerm(document);
        validateINN(document);
    }

    private static void validateAge(Document document) {
        NodeList applicants = document.getElementsByTagName("ApplicantDetails");
        for (int i = 0; i < applicants.getLength(); i++) {
            Element applicant = (Element) applicants.item(i);
            int age = Integer.parseInt(applicant.getElementsByTagName("Age").item(0).getTextContent());
            if (age > 85) {
                System.out.println("Applicant age exceeds 85 years.");
            }
        }
    }

    private static void validateLoanAmountAndTerm(Document document) {
        NodeList loanProducts = document.getElementsByTagName("LoanProduct");
        for (int i = 0; i < loanProducts.getLength(); i++) {
            Element loanProduct = (Element) loanProducts.item(i);
            int minAmount = Integer.parseInt(loanProduct.getElementsByTagName("MinAmount").item(0).getTextContent());
            int maxAmount = Integer.parseInt(loanProduct.getElementsByTagName("MaxAmount").item(0).getTextContent());
            int minTerm = Integer.parseInt(loanProduct.getElementsByTagName("MinTerm").item(0).getTextContent());
            int maxTerm = Integer.parseInt(loanProduct.getElementsByTagName("MaxTerm").item(0).getTextContent());

            NodeList applications = document.getElementsByTagName("ApplicationData");
            for (int j = 0; j < applications.getLength(); j++) {
                Element application = (Element) applications.item(j);
                int requestedAmount = Integer.parseInt(application.getElementsByTagName("RequestedAmount").item(0).getTextContent());
                int requestedTerm = Integer.parseInt(application.getElementsByTagName("RequestedTerm").item(0).getTextContent());

                if (requestedAmount < minAmount || requestedAmount > maxAmount) {
                    System.out.println("Requested amount is out of range.");
                }
                if (requestedTerm < minTerm || requestedTerm > maxTerm) {
                    System.out.println("Requested term is out of range.");
                }
            }
        }
    }

    private static void validateINN(Document document) {
        NodeList applicants = document.getElementsByTagName("ApplicationData");
        for (int i = 0; i < applicants.getLength(); i++) {
            Element applicant = (Element) applicants.item(i);
            String inn = applicant.getElementsByTagName("INN").item(0).getTextContent();
            if (!isValidINN(inn)) {
                System.out.println("Invalid INN: " + inn);
            }
        }
    }

    private static boolean isValidINN(String inn) {
        return inn.matches("\\d{10}");
    }
}
