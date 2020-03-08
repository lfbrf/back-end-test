package br.com.escriba.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	private Util() {
		throw new IllegalStateException("Utility class");
	}
	
}
