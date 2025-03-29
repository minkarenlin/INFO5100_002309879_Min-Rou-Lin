package com.example;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BookParser {


    public static void parseXML(String xmlFilePath) {
        try {
            File inputFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList bookList = doc.getElementsByTagName("Book");
            for (int i = 0; i < bookList.getLength(); i++) {
                Node bookNode = bookList.item(i);
                if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element bookElement = (Element) bookNode;
                    String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                    String publishedYear = bookElement.getElementsByTagName("publishedYear").item(0).getTextContent();
                    String numberOfPages = bookElement.getElementsByTagName("numberOfPages").item(0).getTextContent();

                    System.out.println("Title: " + title);
                    System.out.println("Published Year: " + publishedYear);
                    System.out.println("Number of Pages: " + numberOfPages);

                    NodeList authorList = bookElement.getElementsByTagName("author");
                    for (int j = 0; j < authorList.getLength(); j++) {
                        System.out.println("Author: " + authorList.item(j).getTextContent());
                    }
                    System.out.println();
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
        // Handle exceptions more specifically
            System.err.println("Error occurred while processing the XML file: " + e.getMessage());
            e.printStackTrace();
        }
    }

   
    public static void parseJSON(String jsonFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            JSONObject jsonObject = new JSONObject(jsonContent.toString());
            JSONObject bookShelf = jsonObject.getJSONObject("BookShelf");
            JSONArray books = bookShelf.getJSONArray("Books");

            for (int i = 0; i < books.length(); i++) {
                JSONObject book = books.getJSONObject(i);
                String title = book.getString("title");
                int publishedYear = book.getInt("publishedYear");
                int numberOfPages = book.getInt("numberOfPages");

                System.out.println("Title: " + title);
                System.out.println("Published Year: " + publishedYear);
                System.out.println("Number of Pages: " + numberOfPages);

                JSONArray authors = book.getJSONArray("authors");
                for (int j = 0; j < authors.length(); j++) {
                    System.out.println("Author: " + authors.getString(j));
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void addNewBookToXML(String xmlFilePath) {
        try {
            File inputFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Element bookshelf = (Element) doc.getElementsByTagName("BookShelf").item(0);
            Element newBook = doc.createElement("Book");
            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode("The Rising Sun"));
            newBook.appendChild(title);

            Element publishedYear = doc.createElement("publishedYear");
            publishedYear.appendChild(doc.createTextNode("2024"));
            newBook.appendChild(publishedYear);

            Element numberOfPages = doc.createElement("numberOfPages");
            numberOfPages.appendChild(doc.createTextNode("280"));
            newBook.appendChild(numberOfPages);

            Element authors = doc.createElement("authors");
            Element author1 = doc.createElement("author");
            author1.appendChild(doc.createTextNode("Alice Green"));
            authors.appendChild(author1);
            newBook.appendChild(authors);

            bookshelf.appendChild(newBook);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.transform(source, result);

            System.out.println("New Book added to XML!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addNewBookToJSON(String jsonFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            JSONObject jsonObject = new JSONObject(jsonContent.toString());
            JSONObject bookShelf = jsonObject.getJSONObject("BookShelf");
            JSONArray books = bookShelf.getJSONArray("Books");

            // 创建新的图书条目
            JSONObject newBook = new JSONObject();
            newBook.put("title", "The Rising Sun");
            newBook.put("publishedYear", 2023);
            newBook.put("numberOfPages", 280);
            newBook.put("authors", new JSONArray().put("Alice Green"));

            books.put(newBook);

            // 保存更新后的 JSON 文件
            try (FileWriter writer = new FileWriter(jsonFilePath)) {
                writer.write(jsonObject.toString(4)); // 美化输出，带缩进
            }

            System.out.println("New Book added to JSON!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String xmlFilePath = "resources/books.xml"; // XML 
        String jsonFilePath =  "resources/books.json"; // JSON 

      
        System.out.println("XML Parser Output:");
        parseXML(xmlFilePath);

        System.out.println("JSON Parser Output:");
        parseJSON(jsonFilePath);

    
        addNewBookToXML(xmlFilePath);
        addNewBookToJSON(jsonFilePath);

        System.out.println("--------------------------------------------");
        System.out.println("Reprinted XML after adding new book:");
        parseXML(xmlFilePath);

        System.out.println("--------------------------------------------");
        System.out.println("Reprinted JSON after adding new book:");
        parseJSON(jsonFilePath);
        System.out.println("--------------------------------------------");
    }
}
