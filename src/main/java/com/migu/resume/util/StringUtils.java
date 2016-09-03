package com.migu.resume.util;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils extends org.apache.commons.lang.StringUtils {
    public StringUtils() {
    	
    }
    //ID只能是32位或64位字符或者为"-1"
    public static boolean verifyID(String id)
    {
        if(StringUtils.isEmpty(id))
            return false;
        if(id.length()>64)
            return false;
        return true;
    }



    public static int[] toIntArray(String[] stringArr) {
        if(stringArr != null && stringArr.length > 0) {
            int[] arr = new int[stringArr.length];

            for(int i = 0; i < stringArr.length; ++i) {
                arr[i] = NumUtils.parseInt(stringArr);
            }

            return arr;
        } else {
            return null;
        }
    }

    public static long[] toLongArray(String[] stringArr) {
        if(stringArr != null && stringArr.length > 0) {
            long[] arr = new long[stringArr.length];

            for(int i = 0; i < stringArr.length; ++i) {
                arr[i] = NumUtils.parseLong(stringArr);
            }

            return arr;
        } else {
            return null;
        }
    }

    public static float[] toFloatArray(String[] stringArr) {
        if(stringArr != null && stringArr.length > 0) {
            float[] arr = new float[stringArr.length];

            for(int i = 0; i < stringArr.length; ++i) {
                arr[i] = NumUtils.parseFloat(stringArr);
            }

            return arr;
        } else {
            return null;
        }
    }

    public static String[] toStringArray(Collection<String> collection) {
        return collection == null?null:(String[])collection.toArray(new String[collection.size()]);
    }

    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    public static String deleteAny(String inString, String charsToDelete) {
        if(hasLength(inString) && hasLength(charsToDelete)) {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < inString.length(); ++i) {
                char c = inString.charAt(i);
                if(charsToDelete.indexOf(c) == -1) {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return inString;
        }
    }

    public static String[] delimitedListToStringArray(String str, String delimiter) {
        return delimitedListToStringArray(str, delimiter, (String)null);
    }

    public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
        if(str == null) {
            return new String[0];
        } else if(delimiter == null) {
            return new String[]{str};
        } else {
            ArrayList result = new ArrayList();
            int pos;
            if("".equals(delimiter)) {
                for(pos = 0; pos < str.length(); ++pos) {
                    result.add(deleteAny(str.substring(pos, pos + 1), charsToDelete));
                }
            } else {
                int delPos;
                for(pos = 0; (delPos = str.indexOf(delimiter, pos)) != -1; pos = delPos + delimiter.length()) {
                    result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
                }

                if(str.length() > 0 && pos <= str.length()) {
                    result.add(deleteAny(str.substring(pos), charsToDelete));
                }
            }

            return toStringArray(result);
        }
    }

    public static String[] commaDelimitedListToStringArray(String str) {
        return delimitedListToStringArray(str, ",");
    }

    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
        if(str == null) {
            return null;
        } else {
            StringTokenizer st = new StringTokenizer(str, delimiters);
            ArrayList tokens = new ArrayList();

            while(st.hasMoreTokens()) {
                String token = st.nextToken();
                if(trimTokens) {
                    token = token.trim();
                }

                if(!ignoreEmptyTokens || token.length() > 0) {
                    tokens.add(token);
                }
            }

            return toStringArray(tokens);
        }
    }
    public static int toInt(String str) {
    	return toInt(str,-1);
	}
    public static int toInt(String str, int defaultValue) {
		if (str == null) {
			return defaultValue;
		}

		try {
			int returnValue = Integer.parseInt(str);
			return returnValue;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return defaultValue;
	}
    public static long toLong(String str) {
		return toLong(str, -1);
	}

	public static long toLong(String str, long defaultValue) {
		if (str == null) {
			return defaultValue;
		}
		try {
			long returnValue = Long.parseLong(str);
			return returnValue;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return defaultValue;
	}
	/**
	 * 仿c语言的sprintf实现
	 * 
	 * @param format
	 *            字符串输出格弄17
	 * @param args
	 * @return
	 */
	public static String sprintf(String format, Object... args) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		writer.printf(format, args);
		writer.close();
		return stringWriter.toString();
	}

	private static final String REGEXP_FORMAT_STRING = "(\\{\\d\\})";
	private static final Pattern pattern = Pattern.compile(REGEXP_FORMAT_STRING, Pattern.CASE_INSENSITIVE);
	
	/**
	 * 
	 * @param format
	 *            aaaa{0}hello world{1}, welcome {0}
	 * @param args
	 * @return
	 */
	public static String buildString(String format, Object... args) {
		Matcher matcher = pattern.matcher(format);
		String result = format;
		if (args == null) {
			return result;
		}
		while (matcher.find()) {
			String token = matcher.group();
			int idx = Integer.parseInt(token.substring(1, token.length() - 1));
			result = result.replace(token, args[idx] == null ? "" : args[idx].toString());
		}
		return result;
	}
}
