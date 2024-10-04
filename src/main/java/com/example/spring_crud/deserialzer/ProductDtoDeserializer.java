package com.example.spring_crud.deserialzer;

import com.example.spring_crud.dto.ProductDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductDtoDeserializer extends JsonDeserializer<ProductDto> {

    @Override
    public ProductDto deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        List<String> unwantedFields = new ArrayList<>();

        // Check for unwanted fields
        node.fieldNames().forEachRemaining(fieldName -> {
            if (!fieldName.equals("name") && !fieldName.equals("price")) {
                unwantedFields.add(fieldName);
            }
        });

        if (!unwantedFields.isEmpty()) {
            throw new IOException("Unwanted fields detected: " + String.join(", ", unwantedFields));
        }

        // Map fields to ProductDto
        String name = node.get("name").asText();
        double price = node.get("price").asDouble();

        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setPrice(price);

        return productDto;
    }
}
