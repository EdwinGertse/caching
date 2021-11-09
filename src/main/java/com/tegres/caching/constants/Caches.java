package com.tegres.caching.constants;

public class Caches {
    private Caches() {
        throw new RuntimeException("Class should be used in a static context");
    }

    public static final String AUTHORS_CACHE = "authorsCache";
    public static final String AUTHOR_CACHE = "authorCache";
}
