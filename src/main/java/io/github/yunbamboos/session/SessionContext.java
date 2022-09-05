package io.github.yunbamboos.session;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 当前回话session
 */
public class SessionContext {

    private static final ThreadLocal<Map<String, String>> tl = new ThreadLocal<>();

    private SessionContext() {

    }

    public static void set(String key, String value) {
        Map<String, String> m = tl.get();
        if (m == null) {
            m = new HashMap<>();
            tl.set(m);
        }
        m.put(key, value);
    }

    public static String get(String key) {
        Map<String, String> m = tl.get();
        if (m == null)
            return null;
        else
            return m.get(key);
    }

    public static Integer getInt(String key) {
        return Integer.parseInt(Objects.requireNonNull(get(key)));
    }

    public static boolean containsKey(String key) {
        Map<String, String> m = tl.get();
        return m != null && m.containsKey(key);
    }

    public static void clean() {
        Map<String, String> m = tl.get();
        if (m != null) m.clear();
        tl.remove();
    }

    public static Map<String, String> get() {
        Map<String, String> m = tl.get();
        if (m == null) {
            m = new HashMap<>();
            tl.set(m);
        }
        return m;
    }

}
