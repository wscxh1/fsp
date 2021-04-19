package com.foodshare.utils;

public class StringUtils {
    public static boolean isInt(String str) {
        return str.matches("[0-9]+");
    }

    /**
     * sql模糊搜索时，对查询字段作特殊处理
     * 通配符转义处理后，like 语句后面加上 ESCAPE '/'
     * @param s 需要转义的字符串
     * @return 返回转义后的字符串
     */
    public static String escapeQueryChars(String s) {
        // 只需要转义/%_
        s = s.replaceAll("/", "//");
        s = s.replaceAll("%", "/%");
        s = s.replaceAll("_", "/_");
        return s;
        // 遍历每个字符，如果是特殊字符则在前面拼接/
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (c == '%' || c == '_' || c == '/'
//                    || c == '\\' || c == '+' || c == '-' || c == '(' || c == ')'
//                    || c == ':' || c == '^'	|| c == '[' || c == ']' || c == '\"'
//                    || c == '{' || c == '}' || c == '~' || c == '*' || c == '?'
//                    || c == '|' || c == '&' || c == ';' || c == '.'|| c == '!'
//                    || c == '$' || Character.isWhitespace(c)
//            ) {
//                sb.append('/');
//            }
//            sb.append(c);
//        }
//        return sb.toString();
    }
}
