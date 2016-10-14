package com.n26;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Code adapted from Stackoverflow: http://stackoverflow.com/users/776546/nyxz
 *
 * http://stackoverflow.com/questions/17143116/integration-testing-posting-an-entire-object-to-spring-mvc-controller
 */
public class IntegrationTestUtils {

    /**
     * This method converts an object into JSON
     *
     * @param obj: POJO to be converted
     * @return String representation of the POJO in JSON
     */
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
