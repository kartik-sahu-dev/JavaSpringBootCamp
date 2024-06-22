package com.example.JsonToDatabase.service;

import com.example.JsonToDatabase.json.FlattenJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class DynamicTableService {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    // Example JSON array containing multiple objects
    private String jsonArrayString = """
        { 
         "elements":[
                {
                    "name": "John Doe",
                    "age": 25,
                    "email": "john.doe@example.com",
                    "isStudent": false,
                    "courses": ["Mathematics", "Physics", "Computer Science"],
                    "address": {
                        "street": "1234 Elm Street",
                        "city": "Springfield",
                        "zipcode": "98765"
                    }
                },
                {
                    "name": "Jane Smith",
                    "age": 30,
                    "email": "jane.smith@example.com",
                    "isStudent": true,
                    "courses": ["Biology", "Chemistry"],
                    "address": {
                        "street": "5678 Oak Street",
                        "city": "Shelbyville",
                        "zipcode": "54321"
                    }
                },
                {
                    "name": "Alice Johnson",
                    "age": 22,
                    "email": "alice.johnson@example.com",
                    "isStudent": false,
                    "courses": ["History", "Literature"],
                    "address": {
                        "street": "1357 Maple Street",
                        "city": "Ogdenville",
                        "zipcode": "67890"
                    }
                }
            ]
        }
        """;

    @GetMapping("/toFlattenJson")
    public ObjectNode toFlattenJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode result;
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonArrayString);
            System.out.println(jsonNode);
            JsonNodeFactory factory = objectMapper.getNodeFactory();
            result = new ObjectNode(factory);
            FlattenJson.flatten(jsonNode, result,"");
            System.out.println(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
        List<String> columns = getColumns(result);
        System.out.println(columns);
        return result;
    }

    private List<String> getColumns(ObjectNode result) {
        List<String> columns = new ArrayList<>();
        result.fieldNames().forEachRemaining(fieldName ->{
            if (fieldName.startsWith("elements[0].")){
                columns.add(fieldName.replace("elements[0].", ""));
            }
        });
        return columns;
    }
//
//    public void initializeDynamicTable() {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            // Read JSON array
//            JsonNode jsonArrayNode = objectMapper.readTree(jsonArrayString);
//
//            if (jsonArrayNode.isArray()) {
//                // Get the first JSON object to determine table structure
//                JsonNode firstObject = jsonArrayNode.get(0);
//                Set<String> allKeys = getAllKeys(firstObject);
//
//                // Create table SQL
//                createDynamicTable(allKeys);
//
//                // Insert data into the dynamic table
//                insertDataIntoDynamicTable(jsonArrayNode);
//            } else {
//                System.out.println("JSON array is empty or malformed.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Helper method to get all keys from a JSON object
//    private Set<String> getAllKeys(JsonNode node) {
//        Set<String> keys = new HashSet<>();
//        flattenJsonNode(node, "", keys);
//        return keys;
//    }
//
//    // Recursive method to flatten JSON and collect keys
//    private void flattenJsonNode(JsonNode node, String parentKey, Set<String> keys) {
//        if (node.isObject()) {
//            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
//            while (fields.hasNext()) {
//                Map.Entry<String, JsonNode> field = fields.next();
//                String fieldKey = parentKey.isEmpty() ? field.getKey() : parentKey + "_" + field.getKey();
//                flattenJsonNode(field.getValue(), fieldKey, keys);
//            }
//        } else if (node.isArray()) {
//            for (int i = 0; i < node.size(); i++) {
//                String arrayKey = parentKey + "_" + i;
//                flattenJsonNode(node.get(i), arrayKey, keys);
//            }
//        } else {
//            keys.add(parentKey);
//        }
//    }
//
//    // Method to create a dynamic table based on keys
//    private void createDynamicTable(Set<String> keys) {
//        String tableName = "DynamicJsonTable";
//        StringBuilder createTableSQL = new StringBuilder("CREATE TABLE " + tableName + " (id SERIAL PRIMARY KEY");
//
//        for (String key : keys) {
//            createTableSQL.append(", ").append(key).append(" VARCHAR(255)");
//        }
//
//        createTableSQL.append(");");
//
//        jdbcTemplate.execute(createTableSQL.toString());
//        System.out.println("Created dynamic table " + tableName);
//    }
//
//    // Method to insert data into the dynamic table\
//    public void insertDataIntoDynamicTable(JsonNode jsonArrayNode) {
//        String tableName = "DynamicJsonTable";
//
//        for (JsonNode jsonNode : jsonArrayNode) {
//            Map<String, Object> dataMap = new HashMap<>();
//            flattenJsonNodeForInsert(jsonNode, "", dataMap);
//
//            StringBuilder insertSQL = new StringBuilder("INSERT INTO " + tableName + " (");
//            StringBuilder valuesSQL = new StringBuilder("VALUES (");
//
//            dataMap.forEach((key, value) -> {
//                insertSQL.append(key).append(", ");
//                valuesSQL.append("'").append(value).append("', ");
//            });
//
//            insertSQL.setLength(insertSQL.length() - 2); // Remove last comma and space
//            valuesSQL.setLength(valuesSQL.length() - 2); // Remove last comma and space
//            insertSQL.append(") ").append(valuesSQL).append(");");
//
//            jdbcTemplate.execute(insertSQL.toString());
//        }
//
//        System.out.println("Inserted data into dynamic table " + tableName);
//    }
//
//    // Helper method to flatten JSON and prepare data for insertion
//    private void flattenJsonNodeForInsert(JsonNode node, String parentKey, Map<String, Object> dataMap) {
//        if (node.isObject()) {
//            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
//            while (fields.hasNext()) {
//                Map.Entry<String, JsonNode> field = fields.next();
//                String fieldKey = parentKey.isEmpty() ? field.getKey() : parentKey + "_" + field.getKey();
//                flattenJsonNodeForInsert(field.getValue(), fieldKey, dataMap);
//            }
//        } else if (node.isArray()) {
//            for (int i = 0; i < node.size(); i++) {
//                String arrayKey = parentKey + "_" + i;
//                flattenJsonNodeForInsert(node.get(i), arrayKey, dataMap);
//            }
//        } else {
//            dataMap.put(parentKey, node.asText());
//        }
//    }
}

