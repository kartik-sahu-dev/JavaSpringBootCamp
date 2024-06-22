package com.example.JsonToDatabase.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;

@Slf4j
public class FlattenJson {
    public static void flatten(JsonNode currentNode, ObjectNode flattenNode, String parentKey) {

        Iterator<Map.Entry<String,JsonNode>> fields = currentNode.fields();

        while (fields.hasNext()) {
            Map.Entry<String,JsonNode > field = fields.next();
            String key = parentKey.isEmpty() ? field.getKey() : parentKey + "." + field.getKey();
            JsonNode value = field.getValue();

            if(value.isObject()){
                flatten(value, flattenNode, key);
            } else if (value.isArray()) {
                for(int i = 0; i < value.size(); i++) {
                    flatten(value.get(i),flattenNode,key + "[" + i + "]");
                }
            }else{
                flattenNode.put(key,value.asText());
            }
        }

    }
}
