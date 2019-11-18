package tests.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

public class SoapClient {

	private String nameSpacePrefix;
	private String nameSpaceUri;
	private SOAPFactory soapFactory;
	private SOAPMessage soapMessage;
	private SOAPEnvelope soapEnvelope;
	private SOAPConnection soapConnection;
	
	public SoapClient(String nameSpace, String nameSpaceURI, boolean soap1_2) throws SOAPException {
		this.nameSpacePrefix = nameSpace;
		this.nameSpaceUri = nameSpaceURI;
		MessageFactory messageFactory;
		if(soap1_2)
			messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
		else
			messageFactory = MessageFactory.newInstance();
        this.soapFactory = SOAPFactory.newInstance();
        this.soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = this.soapMessage.getSOAPPart();
        this.soapEnvelope = soapPart.getEnvelope();
        soapEnvelope.addNamespaceDeclaration(this.nameSpacePrefix, this.nameSpaceUri);
	}
	
	public void setNameSpaceprefix(String nameSpacePrefix) {
		this.nameSpacePrefix = nameSpacePrefix;
	}
	
	public void setNameSpaceUri(String nameSpaceUri) {
		this.nameSpaceUri = nameSpaceUri;
	}
	
	public SOAPEnvelope getEnvelope() {
		return this.soapEnvelope;
	}
	
	public void addMimeHeaders(HashMap<String, String> soapMessageMimeHeaders) {
		MimeHeaders headers = this.soapMessage.getMimeHeaders();
		soapMessageMimeHeaders.forEach((headerName, headerValue) ->  headers.addHeader(headerName, headerValue));
		System.out.println("HEADERS="+soapMessageMimeHeaders);
	}
	
	public void addSoapHeaders(String name) throws SOAPException {
		SOAPHeader header = this.soapMessage.getSOAPHeader();
		Name headerName = this.soapFactory.createName(name, nameSpacePrefix, nameSpaceUri);
		header.addHeaderElement(headerName);
	}
	
	public SOAPBodyElement generateSoapBody(String soapBodyElementName) throws SOAPException {
		SOAPBody soapBody = soapMessage.getSOAPBody();
		Name bodyName = this.soapFactory.createName(soapBodyElementName, nameSpacePrefix, nameSpaceUri);
		return soapBody.addBodyElement(bodyName);
	}
	
	public SOAPElement generateChildElement(SOAPBodyElement soapBodyElement, String soapElementName) throws SOAPException {
		//Name childName = this.soapFactory.createName(soapElementName);
		//SOAPElement soapElement = soapBodyElement.addChildElement(childName);
		SOAPElement soapElement = soapBodyElement.addChildElement(soapElementName, nameSpacePrefix);
		//soapElement.setPrefix(nameSpacePrefix);
		return soapElement;
	}
	
	public void addFileAttachment(File uploadFile, String fileName) throws SOAPException, IOException {
		AttachmentPart attachment = this.soapMessage.createAttachmentPart();
		
		InputStream inputStream = new FileInputStream(uploadFile);
        attachment.setRawContent(inputStream, Files.probeContentType(uploadFile.toPath()));
        attachment.setContentId(fileName);
		
		this.soapMessage.addAttachmentPart(attachment);
	}

    public SOAPMessage callSoapWebService(String soapEndPointUrl) {
        try {
            this.soapMessage.saveChanges();
            System.out.println("Request SOAP Message has been generated");
            this.soapMessage.writeTo(System.out);
            System.out.println();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            this.soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapResponse = soapConnection.call(this.soapMessage, soapEndPointUrl);
    		System.out.println("SOAP Message has been sent and response has been received:\n" + soapResponse);
            return soapResponse;
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!"
            		+ "\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
            return null;
        }
    }
    
    public void closeConnection() {
    	try {
			this.soapConnection.close();
			System.out.println("Connection " + this.soapConnection + " is closed");
		} catch (SOAPException e) {
			System.out.println("Can't close connection " + this.soapConnection + " Application threw " + e);
		}
    }
}