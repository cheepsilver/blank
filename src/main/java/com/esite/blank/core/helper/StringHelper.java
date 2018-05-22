package com.esite.blank.core.helper;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;


public class StringHelper {

    public static boolean isNotEmpty(String string) {
        return string != null && string.length() > 0;
    }

    public static boolean isEmpty(String string) {
        return string == null || string.trim().length() == 0;
    }

    /**
     * MD5鍔犲瘑
     * @param original
     * @return
     */
    public static String getMd5(String original){
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = original.getBytes();
            // 鑾峰緱MD5鎽樿绠楁硶鐨�MessageDigest 瀵硅薄
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 浣跨敤鎸囧畾鐨勫瓧鑺傛洿鏂版憳瑕�
            mdInst.update(btInput);
            // 鑾峰緱瀵嗘枃
            byte[] md = mdInst.digest();
            // 鎶婂瘑鏂囪浆鎹㈡垚鍗佸叚杩涘埗鐨勫瓧绗︿覆褰㈠紡
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static final int ALIAS_TRUNCATE_LENGTH = 10;
    public static final String WHITESPACE = " \n\r\f\t";
    private static final int[] W = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
    private static final String[] A = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
    private StringHelper() { /* static methods only - hide constructor */
    }

	/*public static boolean containsDigits(String string) {
		for ( int i=0; i<string.length(); i++ ) {
			if ( Character.isDigit( string.charAt(i) ) ) return true;
		}
		return false;
	}*/

    public static int lastIndexOfLetter(String string) {
        for ( int i=0; i<string.length(); i++ ) {
            char character = string.charAt(i);
            if ( !Character.isLetter(character) /*&& !('_'==character)*/ ) {
                return i - 1;
            }
        }
        return string.length()-1;
    }

    public static String join(String seperator, String[] strings) {
        int length = strings.length;
        if ( length == 0 ) {
            return "";
        }
        int capability = isNotEmpty(strings[0])?strings[0].length():12;
        StringBuffer buf = new StringBuffer( length * capability );
        //.append( strings[0] );

        for ( int i = 0; i < length; i++ ) {
            if(isNotEmpty(strings[i])) {
                buf.append(seperator).append(strings[i]);
            }
        }
        if(buf.length()>0) {
            return buf.substring(seperator.length());
        } else {
            return "";
        }
    }

    public static String join(String seperator, Iterator objects) {
        StringBuffer buf = new StringBuffer();
        //if ( objects.hasNext() ) buf.append( objects.next() );
        while ( objects.hasNext() ) {
            String str = (String)objects.next();
            if(isNotEmpty(str)) {
                buf.append(seperator).append(str);
            }
        }
        if (buf.length() > 0) {
            return buf.substring(seperator.length());
        } else {
            return "";
        }
    }

    public static String join(String seperator, List objects) {
        StringBuffer buf = new StringBuffer();
        //if ( objects.hasNext() ) buf.append( objects.next() );
        for(int i=0;i<objects.size();i++) {
            String str = (String)objects.get(i);
            if(isNotEmpty(str)) {
                buf.append(seperator).append(str);
            }
        }
        if (buf.length() > 0) {
            return buf.substring(seperator.length());
        } else {
            return "";
        }
    }

    public static String[] add(String[] x, String sep, String[] y) {
        String[] result = new String[x.length];
        for ( int i = 0; i < x.length; i++ ) {
            result[i] = x[i] + sep + y[i];
        }
        return result;
    }

    public static String repeat(String string, int times) {
        StringBuffer buf = new StringBuffer( string.length() * times );
        for ( int i = 0; i < times; i++ ) {
            buf.append(string);
        }
        return buf.toString();
    }


    public static String replace(String template, String placeholder, String replacement) {
        return replace( template, placeholder, replacement, false );
    }

    public static String[] replace(String templates[], String placeholder, String replacement) {
        String[] result = new String[templates.length];
        for ( int i =0; i<templates.length; i++ ) {
            result[i] = replace( templates[i], placeholder, replacement );;
        }
        return result;
    }

    public static String replace(String template, String placeholder, String replacement, boolean wholeWords) {
        int loc = template == null ? -1 : template.indexOf( placeholder );
        if ( loc < 0 ) {
            return template;
        }
        else {
            final boolean actuallyReplace = !wholeWords ||
                    loc + placeholder.length() == template.length() ||
                    !Character.isJavaIdentifierPart( template.charAt( loc + placeholder.length() ) );
            String actualReplacement = actuallyReplace ? replacement : placeholder;
            return new StringBuffer( template.substring( 0, loc ) )
                    .append( actualReplacement )
                    .append( replace( template.substring( loc + placeholder.length() ),
                            placeholder,
                            replacement,
                            wholeWords ) ).toString();
        }
    }


    public static String replaceOnce(String template, String placeholder, String replacement) {
        int loc = template == null ? -1 : template.indexOf( placeholder );
        if ( loc < 0 ) {
            return template;
        }
        else {
            return new StringBuffer( template.substring( 0, loc ) )
                    .append( replacement )
                    .append( template.substring( loc + placeholder.length() ) )
                    .toString();
        }
    }

    public static int countTokens(String seperators,String list,boolean ignoreCase){
        String localList = list;
        String localSeperators = seperators;
        if(list==null||list.length()==0) {
            return 0;
        }
        if(ignoreCase){
            localList = list.toLowerCase();
            localSeperators = seperators.toLowerCase();
        }
        return countOccurrencesOf(list,seperators);
    }

    public static int countOccurrencesOf(String str, String sub) {
        if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
            return 0;
        }
        int count = 0, pos = 0, idx = 0;
        while ((idx = str.indexOf(sub, pos)) != -1) {
            ++count;
            pos = idx + sub.length();
        }
        return count;
    }


    public static String[] split(String seperators, String list) {
        return split( seperators, list, false );
    }

    public static String[] split(String seperators, String list, boolean include) {
        StringTokenizer tokens = new StringTokenizer( list, seperators, include );
        String[] result = new String[ tokens.countTokens() ];
        int i = 0;
        while ( tokens.hasMoreTokens() ) {
            result[i++] = tokens.nextToken();
        }
        return result;
    }

    public static String unqualify(String qualifiedName) {
        int loc = qualifiedName.lastIndexOf(".");
        return ( loc < 0 ) ? qualifiedName : qualifiedName.substring( qualifiedName.lastIndexOf(".") + 1 );
    }

    public static String qualifier(String qualifiedName) {
        int loc = qualifiedName.lastIndexOf(".");
        return ( loc < 0 ) ? "" : qualifiedName.substring( 0, loc );
    }

    public static String[] suffix(String[] columns, String suffix) {
        if ( suffix == null ) {
            return columns;
        }
        String[] qualified = new String[columns.length];
        for ( int i = 0; i < columns.length; i++ ) {
            qualified[i] = suffix( columns[i], suffix );
        }
        return qualified;
    }

    private static String suffix(String name, String suffix) {
        return ( suffix == null ) ? name : name + suffix;
    }

    public static String root(String qualifiedName) {
        int loc = qualifiedName.indexOf( "." );
        return ( loc < 0 ) ? qualifiedName : qualifiedName.substring( 0, loc );
    }

    public static String unroot(String qualifiedName) {
        int loc = qualifiedName.indexOf( "." );
        return ( loc < 0 ) ? qualifiedName : qualifiedName.substring( loc+1, qualifiedName.length() );
    }

    public static boolean booleanValue(String tfString) {
        String trimmed = tfString.trim().toLowerCase();
        return trimmed.equals( "true" ) || trimmed.equals( "t" );
    }

    public static String toString(Object[] array) {
        int len = array.length;
        if ( len == 0 ) {
            return "";
        }
        StringBuffer buf = new StringBuffer( len * 12 );
        for ( int i = 0; i < len - 1; i++ ) {
            buf.append( array[i] ).append(", ");
        }
        return buf.append( array[len - 1] ).toString();
    }

    public static String[] multiply(String string, Iterator placeholders, Iterator replacements) {
        String[] result = new String[]{string};
        while ( placeholders.hasNext() ) {
            result = multiply( result, ( String ) placeholders.next(), ( String[] ) replacements.next() );
        }
        return result;
    }

    private static String[] multiply(String[] strings, String placeholder, String[] replacements) {
        String[] results = new String[replacements.length * strings.length];
        int n = 0;
        for ( int i = 0; i < replacements.length; i++ ) {
            for ( int j = 0; j < strings.length; j++ ) {
                results[n++] = replaceOnce( strings[j], placeholder, replacements[i] );
            }
        }
        return results;
    }

    public static int countUnquoted(String string, char character) {
        if ( '\'' == character ) {
            throw new IllegalArgumentException( "Unquoted count of quotes is invalid" );
        }
        if (string == null) {
            return 0;
        }
        // Impl note: takes advantage of the fact that an escpaed single quote
        // embedded within a quote-block can really be handled as two seperate
        // quote-blocks for the purposes of this method...
        int count = 0;
        int stringLength = string.length();
        boolean inQuote = false;
        for ( int indx = 0; indx < stringLength; indx++ ) {
            char c = string.charAt( indx );
            if ( inQuote ) {
                if ( '\'' == c ) {
                    inQuote = false;
                }
            }
            else if ( '\'' == c ) {
                inQuote = true;
            }
            else if ( c == character ) {
                count++;
            }
        }
        return count;
    }



    public static String qualify(String prefix, String name) {
        if ( name == null || prefix == null ) {
            throw new NullPointerException();
        }
        return new StringBuffer( prefix.length() + name.length() + 1 )
                .append(prefix)
                .append('.')
                .append(name)
                .toString();
    }

    public static String[] qualify(String prefix, String[] names) {
        if ( prefix == null ) {
            return names;
        }
        int len = names.length;
        String[] qualified = new String[len];
        for ( int i = 0; i < len; i++ ) {
            qualified[i] = qualify( prefix, names[i] );
        }
        return qualified;
    }

    public static int firstIndexOfChar(String sqlString, String string, int startindex) {
        int matchAt = -1;
        for ( int i = 0; i < string.length(); i++ ) {
            int curMatch = sqlString.indexOf( string.charAt( i ), startindex );
            if ( curMatch >= 0 ) {
                if ( matchAt == -1 ) { // first time we find match!
                    matchAt = curMatch;
                }
                else {
                    matchAt = Math.min( matchAt, curMatch );
                }
            }
        }
        return matchAt;
    }

    public static String truncate(String string, int length) {
        if ( string.length() <= length ) {
            return string;
        }
        else {
            return string.substring( 0, length );
        }
    }

    /**
     * Generate a nice alias for the given class name or collection role
     * name and unique integer. Subclasses of Loader do <em>not</em> have
     * to use aliases of this form.
     * @return an alias of the form <tt>foo1_</tt>
     */
    public static String generateAlias(String description, int unique) {
        return generateAliasRoot(description) +
                Integer.toString(unique) +
                '_';
    }

    private static String generateAliasRoot(String description) {
        final String result = truncate( unqualifyEntityName(description), ALIAS_TRUNCATE_LENGTH )
                .toLowerCase()
                .replace( '/', '_' ) // entityNames may now include slashes for the representations
                .replace( '$', '_' ); //classname may be an inner class
        if ( Character.isDigit( result.charAt(result.length()-1) ) ) {
            return result + "x"; //ick!
        }
        else {
            return result;
        }
    }

    public static String unqualifyEntityName(String entityName) {
        String result = unqualify(entityName);
        int slashPos = result.indexOf( '/' );
        if ( slashPos > 0 ) {
            result = result.substring( 0, slashPos - 1 );
        }
        return result;
    }

    public static String generateAlias(String description) {
        return generateAliasRoot(description) + '_';
    }

    public static String toUpperCase(String str) {
        return str==null ? null : str.toUpperCase();
    }

    public static String toLowerCase(String str) {
        return str==null ? null : str.toLowerCase();
    }

    public static String moveAndToBeginning(String filter) {
        if ( filter.trim().length()>0 ){
            filter += " and ";
            if ( filter.startsWith(" and ") ) {
                filter = filter.substring(4);
            }
        }
        return filter;
    }

    public static String generateParamterExpress(String token,String seperators, int count){
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<count;i++){
            buffer.append(seperators).append(token);
        }
        if(buffer.length()>0) {
            return buffer.substring(seperators.length());
        } else {
            return "";
        }
    }

    public static String get18idCard(String idCard15) {
        try {
            int i, j, s = 0;
            String newid = idCard15;
            newid = newid.substring(0, 6) + "19" + newid.substring(6, 15); //寰�7浣�
            for (i = 0; i < newid.length(); i++) {
                j = Integer.parseInt(newid.substring(i, i + 1)) * W[i];
                s = s + j;
            }
            s = s % 11;
            newid = newid + A[s];  //鍙栨渶鍚庝竴浣嶆牎楠岀爜
            return newid;
        } catch (NumberFormatException e) {
            return idCard15;
        }
    }

    /** * 妫�煡鏍￠獙浣� * * @param certiCode * @return */
    private static boolean checkIDParityBit(String certiCode) {
        boolean flag = false;
        if (certiCode == null || "".equals(certiCode)) {
            return false;
        }
        int ai[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
        if (certiCode.length() == 18) {
            int i = 0;
            for (int k = 0; k < 18; k++) {
                char c = certiCode.charAt(k);
                int j;
                if (c == 'X') {
                    j = 10;
                } else if (c <= '9' || c >= '0') {
                    j = c - 48;
                } else {
                    return flag;
                }
                i += j * ai[k];
            }
            if (i % 11 == 1) {
                flag = true;
            }
        }
        return flag;
    }

    /** * 妫�煡鏃ユ湡鏍煎紡 * * @param year * @param month * @param day * @return */
    private static boolean checkDate(String year, String month, String day) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
        try {
            String s3 = year + month + day;
            simpledateformat.setLenient(false);
            simpledateformat.parse(s3);
        } catch (java.text.ParseException parseexception) {
            return false;
        }
        return true;
    }

    public static String createUUID() {
        //return new UUIDHexGenerator().generate().toString();
        UUID uuid  =  UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(StringHelper.createUUID());
    }

    /**
     * 瀵硅薄杞瓧绗︿覆
     *
     * @param obj
     * @return
     */
    public static String objToString(Object obj) {
        if (null != obj) {
            return obj.toString();
        }
        return "";
    }

    /**
     * 棣栧瓧姣嶅ぇ鍐�
     * @param value
     * @return
     */
    public static String capitalize(String value){
        byte[] items = value.getBytes();
        items[0] = (byte)((char)items[0]-'a'+'A');
        return new String(items);
    }
}

