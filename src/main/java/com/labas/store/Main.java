package com.labas.store;

import com.labas.store.exception.ServiceException;
import com.labas.store.model.entity.Order;
import com.labas.store.model.entity.OrderStatus;
import com.labas.store.model.entity.User;
import com.labas.store.service.IOrderService;
import com.labas.store.service.IOrderStatusService;
import com.labas.store.service.IUserService;
import com.labas.store.service.impl.OrderServiceImpl;
import com.labas.store.util.ServiceFactory;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {
        // Get the service instance for working with orders
        IOrderService orderService = ServiceFactory.getService(OrderServiceImpl.class);
        IUserService userService = ServiceFactory.getUserService();
        IOrderStatusService IOrderStatusService = ServiceFactory.getOrderStatusService();

        try {
            // 1. Create a new order
            System.out.println("Creating a new order...");
            Order newOrder = new Order();
            newOrder.setDiscount(10.0f);
            newOrder.setTotal(90.0f);
            newOrder.setCreatedAt("2025-01-27 10:00:00");
            newOrder.setUpdatedAt("2025-01-27 10:00:00");

            // Set related entities (OrderStatus and User)
            OrderStatus orderStatus = new OrderStatus(1L, "Pending"); // Example: Order status with ID = 1
            User user = new User(
                    1L,
                    "John",
                    "Doe",
                    "john.doe@example.com",
                    "123456789",
                    "hashedPassword"
            ); // Example: User with ID = 1

            newOrder.setOrderStatus(orderStatus);
            newOrder.setUser(user);

            boolean userSaved = IUserService.save(user);
            boolean orderStatusSaved = IOrderStatusService.save(orderStatus);
            boolean orderSaved = IOrderService.save(newOrder);
            System.out.println("New order saved: " + orderSaved);

            // 2. Retrieve all orders
            System.out.println("\nRetrieving all orders...");
            List<Order> orders = IOrderService.findAll();
            orders.forEach(order -> System.out.println("Order: " + order));

            // 3. Retrieve an order by ID
            System.out.println("\nRetrieving order with ID = 1...");
            Optional<Order> foundOrder = IOrderService.findById(1L);
            foundOrder.ifPresent(order -> System.out.println("Found order: " + order));

            // 4. Update an order
            System.out.println("\nUpdating order with ID = 1...");
            foundOrder.ifPresent(order -> {
                order.setDiscount(15.0f); // Change discount
                order.setTotal(85.0f);   // Update total
                try {
                    boolean updated = IOrderService.update(order);
                    System.out.println("Order update completed: " + updated);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            });

            // 5. Delete an order
            System.out.println("\nDeleting order with ID = 1...");
            boolean deleted = IOrderService.delete(1L);
            System.out.println("Order deletion completed: " + deleted);

        } catch (ServiceException e) {
            System.err.println("An error occurred while working with orders:");
            e.printStackTrace();
        }

        String xmlFilePath = "src/main/resources/onlineStore.xml";
        String xsdFilePath = "src/main/resources/onlineStore.xsd";

        // Validate XML against XSD
        if (validateXML(xmlFilePath, xsdFilePath)) {
            System.out.println("XML is valid according to XSD.");

            // Parse XML using StAX
            parseXMLWithStax(xmlFilePath);
        } else {
            System.out.println("XML validation failed.");
        }

    }

    public static boolean validateXML(String xmlFile, String xsdFile) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdFile));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFile)));
            return true;
        } catch (Exception e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        }
    }

    public static void parseXMLWithStax(String xmlFile) {
        try {
            InputStream inputStream = new FileInputStream(xmlFile);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(inputStream);

            String currentElement = "";
            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        System.out.println("Start Element: " + reader.getLocalName());
                        for (int i = 0; i < reader.getAttributeCount(); i++) {
                            System.out.println(
                                    "  Attribute: "
                                            + reader.getAttributeLocalName(i) + " = " + reader.getAttributeValue(i)
                            );
                        }
                    }
                    case XMLStreamConstants.CHARACTERS -> {
                        String text = reader.getText().trim();
                        if (!text.isEmpty()) {
                            System.out.println("  Text: " + text);
                        }
                    }
                    case XMLStreamConstants.END_ELEMENT -> System.out.println("End Element: " + reader.getLocalName());
                }
            }
            reader.close();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Error while parsing XML: " + e.getMessage());
        }
    }
}

