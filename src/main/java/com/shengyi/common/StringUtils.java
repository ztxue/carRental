package com.shengyi.common;

import com.shengyi.common.exception.BasicRuntimeException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

//String工具类
public class StringUtils {
    /** ISO-8859-1编码(ISO-LATIN-1)，通常用于网络数据传输的默认编码 range 0000-00FF (0-255)  */
    public static final String ISO_8859_1 = "ISO-8859-1";
    /** Seven-bit ASCII, also known as ISO646-US, also known as the Basic Latin block of the Unicode character set. */
    public static final String US_ASCII = "US-ASCII";
    /** UTF-8编码 */
    public static final String UTF_8 = "UTF-8";
    /** UTF-16编码 */
    public static final String UTF_16 = "UTF-16";
    /** GBK编码 */
    public static final String GBK = "GBK";
    /** GB2312编码 */
    public static final String GB2312 = "GB2312";

    /** 全局默认编码 */
    public static final String DEFAULT_CHARSET = UTF_8;

    /** 文件分隔符'/'，用于Unix等系统 */
    public static final String FOLDER_SEPARATOR = "/";
    /** WIN系统文件分隔符'\\'用于MS Windows系统 */
    public static final String WINDOWS_FOLDER_SEPARATOR = "\\";

    /** 路径标识：当前目录 */
    public static final String CURRENT_PATH = ".";
    /** 路径标识：上一级目录 */
    public static final String TOP_PATH = "..";

    /**
     * Checks if a String is null or empty ("").
     *
     * <pre>
     * StringUtils.isNullOrEmpty(null) = true
     * StringUtils.isNullOrEmpty("") = true
     * StringUtils.isNullOrEmpty(" ") = false
     * StringUtils.isNullOrEmpty("12345") = false
     * StringUtils.isNullOrEmpty(" 12345 ") = false
     * </pre>
     */
    public static boolean isNullOrEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * Checks if a String is null or empty ("").
     */
    public static boolean isNotEmpty(CharSequence str) {
        return str != null && str.length() != 0;
    }

    /**
     * Checks if a String is empty (""), null or whitespace(e.g. " ", "\t", "\n").
     *
     * <pre>
     * StringUtils.isBlank(null) = true
     * StringUtils.isBlank("") = true
     * StringUtils.isBlank(" ") = true
     * StringUtils.isBlank("12345") = false
     * StringUtils.isBlank(" 12345 ") = false
     * </pre>
     */
    public static boolean isBlank(CharSequence str) {
        int strLen;
        // empty ("") or null
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        // length > 0 then check every character
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i)) == false) {
                return false; // find char is not whitespace
            }
        }
        return true;
    }

    /**
     * Checks if a String is empty (""), null or whitespace(e.g. " ", "\t", "\n").
     */
    public static boolean isNotBlank(CharSequence str) {
        int strLen;
        // empty ("") or null
        if (str == null || (strLen = str.length()) == 0) {
            return false;
        }
        // length > 0 then check every character
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i)) == false) {
                return true; // find char is not whitespace
            }
        }
        return false;
    }

    /**
     * 判断两个字符串是否相等，包含空字符串的情况
     */
    public static boolean equals(String str1, String str2) {
        return str1 != null && str1.equals(str2);
    }

    /**
     * 将null转换成空字符串""
     */
    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    public static String decideCharSet(String charSet) {
        if (StringUtils.isNullOrEmpty(charSet)) {
            return StringUtils.DEFAULT_CHARSET;
        }
        return charSet;
    }

    public static Charset toCharSet(String charSet) {
        if (StringUtils.isNullOrEmpty(charSet)) {
            return Charset.forName(StringUtils.DEFAULT_CHARSET);
        }
        return Charset.forName(charSet);
    }

    /**
     * 当前环境的默认编码(即file.encoding)
     */
    public static String getJvmEncoding() {
        return Charset.defaultCharset().displayName();
    }

    /**
     * 重新定义了String.getBytes()。默认用UTF-8编码，以便去除与平台的相关性。
     * <p>
     * 且将CheckedException转换成了RuntimeException。
     *
     * @param str 原字符串
     * @return str.getBytes("UTF-8")的结果
     */
    public static byte[] getBytes(String str) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes(StringUtils.DEFAULT_CHARSET);
        }
        catch (final UnsupportedEncodingException e) {
            throw new BasicRuntimeException(e);
        }
    }

    /**
     * String.getBytes()，将CheckedException转换成了RuntimeException。
     * <p>
     * 默认用UTF-8编码，以便去除与平台的相关性。
     *
     * @param str 原字符串
     * @return str.getBytes("UTF-8")的结果
     */
    public static byte[] getBytes(final String str, final String charSet) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes(decideCharSet(charSet));
        }
        catch (final UnsupportedEncodingException e) {
            throw new BasicRuntimeException(e);
        }
    }

    public static String newString(final byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            return new String(bytes, StringUtils.DEFAULT_CHARSET);
        }
        catch (final UnsupportedEncodingException e) {
            throw new BasicRuntimeException(e);
        }
    }

    public static String newString(final byte[] bytes, final String charSet) {
        if (bytes == null) {
            return null;
        }
        try {
            return new String(bytes, decideCharSet(charSet));
        }
        catch (final UnsupportedEncodingException e) {
            throw new BasicRuntimeException(e);
        }
    }

    /**
     * 字符串编码转换
     *
     * @param newCharset
     *            新编码，例如UTF-8
     */
    public static String changeEncode(final String str, final String newCharset) {
        if (str == null || str.length() == 0){
            return str;
        }
        try {
            String iso = new String(str.getBytes(newCharset), StringUtils.ISO_8859_1); // ISO-8859-1
            return new String(iso.getBytes(StringUtils.ISO_8859_1), newCharset);
        }
        catch (final UnsupportedEncodingException e) {
            throw new BasicRuntimeException(e);
        }
    }

    /**
     * Replace all occurences of a substring within a string with another string. <br>
     * [非正则表达式，区别于String.replace()方法]
     *
     * @param inString
     *            String to examine
     * @param oldPattern
     *            String to replace
     * @param newPattern
     *            String to insert
     * @return a String with the replacements
     */
    public static String replace(String inString, String oldPattern, String newPattern) {
        if (isNullOrEmpty(inString) || isNullOrEmpty(oldPattern) || newPattern == null) {
            return inString;
        }
        StringBuilder sb = new StringBuilder();
        int pos = 0; // our position in the old string
        int index = inString.indexOf(oldPattern);
        // the index of an occurrence we've found, or -1
        int patLen = oldPattern.length();
        while (index >= 0) {
            sb.append(inString.substring(pos, index));
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sb.append(inString.substring(pos));
        // remember to append any characters to the right of a match
        return sb.toString();
    }

    /**
     * 从路径（url或者目录都可以）中获取文件名称（带后缀，形如 abc.txt） <br>
     * Extract the filename from the given path, e.g. "mypath/myfile.txt" -> "myfile.txt".
     *
     * @param path
     *            the file path (may be <code>null</code>)
     * @return the extracted filename, or <code>null</code> if none
     */
    public static String getFilenameFromPath(String path) {
        if (null == path){
            return null;
        }

        String path2 = path.replace(StringUtils.WINDOWS_FOLDER_SEPARATOR, StringUtils.FOLDER_SEPARATOR);
        // path = path.replace(WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);
        int separatorIndex = path2.lastIndexOf(StringUtils.FOLDER_SEPARATOR);
        return (separatorIndex != -1 ? path2.substring(separatorIndex + 1) : path2);
    }

    /**
     * 从路径（url或者目录都可以）中剥去文件名，获得文件所在的目录。 <br>
     * Strip the filename from the given path, e.g. "mypath/myfile.txt" -> "mypath/".
     *
     * @param path
     *            the file path (may be <code>null</code>)
     * @return the path with stripped filename, or <code>null</code> if none
     */
    public static String stripFilenameFromPath(String path) {
        if (null == path){
            return null;
        }
        String path2 = path.replace(StringUtils.WINDOWS_FOLDER_SEPARATOR, StringUtils.FOLDER_SEPARATOR);
        int folderIndex = path2.lastIndexOf(StringUtils.FOLDER_SEPARATOR);
        if (folderIndex == -1) {
            return path2;
        }
        return path2.substring(0, folderIndex + 1);
    }

    private static final char EXTENSION_SEPARATOR = '.';

    /**
     * 从路径（url或者目录都可以）中获取文件名称（不带后缀，形如 abc） Extract the filename without it's extension from the given path, e.g. "mypath/myfile.txt" -> "myfile".
     *
     * @param path
     *            the file path (may be <code>null</code>)
     * @return the extracted filename, or <code>null</code> if none
     */
    public static String getFilenameWithoutExtension(String path) {
        if (null == path){
            return null;
        }
        int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        if (extIndex == -1) {
            return path.substring(path.lastIndexOf(StringUtils.FOLDER_SEPARATOR) + 1, path.length());
            // return null;
        }
        String path2 = path.replace(StringUtils.WINDOWS_FOLDER_SEPARATOR, StringUtils.FOLDER_SEPARATOR);
        int folderIndex = path2.lastIndexOf(StringUtils.FOLDER_SEPARATOR);
        if (folderIndex > extIndex) {
            // throw new RuntimeException("the path form is not correct!--"+path);
            return null;
        }
        return path2.substring(folderIndex + 1, extIndex);
    }

    /**
     * 从路径（url或者目录都可以）中获取文件后缀（比如 txt）<br>
     * Extract the filename extension from the given path, e.g. "mypath/myfile.txt" -> "txt".
     *
     * @param path
     *            the file path (may be <code>null</code>)
     * @return the extracted filename extension, or <code>null</code> if none
     */
    public static String getFilenameExtension(String path) {

        if(null==path){
            return null;

        }
        int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        if (extIndex == -1) {
            return null;
        }
        String path2 = path.replace(StringUtils.WINDOWS_FOLDER_SEPARATOR, StringUtils.FOLDER_SEPARATOR);
        int folderIndex = path2.lastIndexOf(StringUtils.FOLDER_SEPARATOR);
        if (folderIndex > extIndex) {
            return null;
        }
        return path2.substring(extIndex + 1);
    }

    /**
     * Apply the given relative path to the given path, assuming standard Java folder separation (i.e. "/" separators).
     *
     * @param path
     *            the path to start from (usually a full file path)
     * @param relativePath
     *            the relative path to apply (relative to the full file path above)
     * @return the full file path that results from applying the relative path
     */
    public static String applyRelativePath(String path, String relativePath) {
        String path2 = path.replace(StringUtils.WINDOWS_FOLDER_SEPARATOR, StringUtils.FOLDER_SEPARATOR);
        String relativePath2 = relativePath.replace(StringUtils.WINDOWS_FOLDER_SEPARATOR, StringUtils.FOLDER_SEPARATOR);

        int separatorIndex = path2.lastIndexOf(StringUtils.FOLDER_SEPARATOR);
        if (separatorIndex != -1) {
            String newPath = path.substring(0, separatorIndex);
            if (!relativePath2.startsWith(StringUtils.FOLDER_SEPARATOR)) {
                newPath += StringUtils.FOLDER_SEPARATOR;
            }
            return newPath + relativePath2;
        }
        else {
            return relativePath2;
        }
    }

    /**
     * Count the occurrences of the substring in string s.
     *
     * @param str
     *            string to search in. Return 0 if this is null.
     * @param sub
     *            string to search for. Return 0 if this is null.
     */
    public static int countOccurrencesOf(String str, String sub) {
        if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
            return 0;
        }
        int count = 0;
        int pos = 0;
        int idx;
        while ((idx = str.indexOf(sub, pos)) != -1) {
            ++count;
            pos = idx + sub.length();
        }
        return count;
    }

    private static final String REPLACE_LABEL = "{}";

    /**
     * 用objs[]的值去替换字符串s中的{}符号
     */
    public static String replaceParams(String s, Object... objs) {
        if (s == null){
            return s;
        }

        if (objs == null || objs.length == 0){
            return s;
        }

        if (s.indexOf(REPLACE_LABEL) == -1) {
            StringBuilder result = new StringBuilder();
            result.append(s);
            for (Object obj : objs) {
                result.append(" ").append(obj);
            }
            return result.toString();
        }

        String[] stra = new String[objs.length];
        int len = s.length();
        for (int i = 0; i < objs.length; i++) {
            if (objs[i] != null){
                stra[i] = objs[i].toString();
            }else {
                stra[i] = "null";
            }
            len += stra[i].length();
        }

        StringBuilder result = new StringBuilder(len);
        int cursor = 0;
        int index = 0;
        for (int start; (start = s.indexOf(REPLACE_LABEL, cursor)) != -1;) {
            result.append(s.substring(cursor, start));
            if (index < stra.length){
                result.append(stra[index]);
            }else{
                result.append(REPLACE_LABEL);
            }

            cursor = start + 2;
            index++;
        }
        result.append(s.substring(cursor, s.length()));
        if (index < objs.length) {
            for (int i = index; i < objs.length; i++) {
                result.append(" ").append(objs[i]);
            }
        }
        return result.toString();
    }

    /**
     * @see #replaceParams(String, Object...)
     */
    public static String replaceParams(String s, String... objs) {
        return StringUtils.replaceParams(s, (Object[]) objs);
    }

    /**
     * 转换html里面的5个特殊字符：<code>&, <, >, ', and "</code>,
     */
    public static String simpleHtmlEscape(String str) {
        if (str == null){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            switch (c) {
                case '&':
                    sb.append("&amp;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '\"':
                    sb.append("&quot;");
                    break;
                case '\'':
                    sb.append("&apos;");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

}
