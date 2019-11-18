package tests.api;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.testng.annotations.Test;

import helpers.Read_Excel;

public class SoapTests {

	private static final String nameSpacePrefix = "prod";
	private static final String nameSpaceURI = "http://application.com/soap/products";
	private static final String soapEndpointUrl = "http://localhost:4448/ws/product.wsdl";
	private HashMap<String, String> soapMessageHeaders;
	private Read_Excel readExcel;
	
	public SoapTests(){        
		this.readExcel = new Read_Excel(System.getProperty("user.dir") + "\\resources\\Test_Cases.xlsx");
		this.soapMessageHeaders = new HashMap<String, String>(readExcel.excelToMap("soapMimeHeaders"));
	}
	
	@Test(description="Test Add new product request", priority = 0)
	public void addProductRequest() throws SOAPException {
		SoapClient soapBase = new SoapClient(nameSpacePrefix, nameSpaceURI, false);
		System.out.println("soapMessageHeaders=" + soapMessageHeaders +  "\n size=" + soapMessageHeaders.size());
		soapBase.addMimeHeaders(soapMessageHeaders);
		SOAPBodyElement soapBodyElement = soapBase.generateSoapBody("addProductRequest");
		
		SOAPElement soapElement_Name = soapBase.generateChildElement(soapBodyElement, "name");
		soapElement_Name.addTextNode("mouse");
		
		SOAPElement soapElement_Description = soapBase.generateChildElement(soapBodyElement, "description");
		soapElement_Description.addTextNode("mouseDescription");
		
		SOAPElement soapElement_Price = soapBase.generateChildElement(soapBodyElement, "price");
		soapElement_Price.addTextNode("10.15");
		
		SOAPMessage soapResponse = soapBase.callSoapWebService(soapEndpointUrl);
		try {
			soapResponse.writeTo(System.out);
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(soapResponse.getSOAPBody().getTextContent().contains("mouse"));
		assertTrue(soapResponse.getSOAPBody().getTextContent().contains("mouseDescription"));
		assertTrue(soapResponse.getSOAPBody().getTextContent().contains("10.15"));
		soapBase.closeConnection();
	}
	
	@Test(description="Test Get product by ID request", priority = 1)
	public void getProductRequest() throws SOAPException {
		SoapClient soapBase = new SoapClient(nameSpacePrefix, nameSpaceURI, false);
		System.out.println("soapMessageHeaders=" + soapMessageHeaders +  "\n size=" + soapMessageHeaders.size());
		soapBase.addMimeHeaders(soapMessageHeaders);
		SOAPBodyElement soapBodyElement = soapBase.generateSoapBody("GetProductRequest");
		
		SOAPElement soapElement_Name = soapBase.generateChildElement(soapBodyElement, "id");
		soapElement_Name.addTextNode("1");
		
		SOAPMessage soapResponse = soapBase.callSoapWebService(soapEndpointUrl);
		try {
			soapResponse.writeTo(System.out);
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(soapResponse.getSOAPBody().getTextContent().contains("mouse"));
		assertTrue(soapResponse.getSOAPBody().getTextContent().contains("mouseDescription"));
		assertTrue(soapResponse.getSOAPBody().getTextContent().contains("10.15"));
		soapBase.closeConnection();
	}
	
	@Test(description="Test Update product by ID request", priority = 2)
	public void updateProductRequest() throws SOAPException {
		SoapClient soapBase = new SoapClient(nameSpacePrefix, nameSpaceURI, false);
		System.out.println("soapMessageHeaders=" + soapMessageHeaders +  "\n size=" + soapMessageHeaders.size());
		soapBase.addMimeHeaders(soapMessageHeaders);
		SOAPBodyElement soapBodyElement = soapBase.generateSoapBody("updateProductRequest");
		
		SOAPElement soapElement_ID = soapBase.generateChildElement(soapBodyElement, "id");
		soapElement_ID.addTextNode("1");
		
		SOAPElement soapElement_Name = soapBase.generateChildElement(soapBodyElement, "name");
		soapElement_Name.addTextNode("keyboard");
		
		SOAPElement soapElement_Description = soapBase.generateChildElement(soapBodyElement, "description");
		soapElement_Description.addTextNode("keyboardDescription");
		
		SOAPElement soapElement_Price = soapBase.generateChildElement(soapBodyElement, "price");
		soapElement_Price.addTextNode("27.33");
		
		SOAPMessage soapResponse = soapBase.callSoapWebService(soapEndpointUrl);
		try {
			soapResponse.writeTo(System.out);
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(soapResponse.getSOAPBody().getTextContent().contains("keyboard"));
		assertTrue(soapResponse.getSOAPBody().getTextContent().contains("keyboardDescription"));
		assertTrue(soapResponse.getSOAPBody().getTextContent().contains("27.33"));
		soapBase.closeConnection();
	}
	
	@Test(description="Test Delete product by ID request", priority = 3)
	public void deleteProductRequest() throws SOAPException {
		SoapClient soapBase = new SoapClient(nameSpacePrefix, nameSpaceURI, false);
		System.out.println("soapMessageHeaders=" + soapMessageHeaders +  "\n size=" + soapMessageHeaders.size());
		soapBase.addMimeHeaders(soapMessageHeaders);
		SOAPBodyElement soapBodyElement = soapBase.generateSoapBody("deleteProductRequest");
		
		SOAPElement soapElement_Name = soapBase.generateChildElement(soapBodyElement, "id");
		soapElement_Name.addTextNode("1");
		
		SOAPMessage soapResponse = soapBase.callSoapWebService(soapEndpointUrl);
		try {
			soapResponse.writeTo(System.out);
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(soapResponse.getSOAPBody().getTextContent().contains("SUCCESS"));
		soapBase.closeConnection();
	}
}