package com.example.JsonToDatabase.service;

import org.h2.tools.Console;
import org.junit.jupiter.api.Test;

public class DynamicTableServiceTest {
    @Test
    void toFlattenJsonTest(){
        DynamicTableService dt = new DynamicTableService();
        dt.toFlattenJson();
    }

}
