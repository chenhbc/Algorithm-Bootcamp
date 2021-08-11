class Solution {
    public String toLowerCase(String s) {
        int len = s.length();
        // 新建一个数组存储转换后的字符数组
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            // a-z：97-122
	        // A-Z：65-90
            // 大写字母与小写字母的 ASCII 码相差 32，所以如果是大写字母的话，加上 32 就是小写字母
            if (c >= 'A' && c <= 'Z') c += 32;
            chars[i] = c;
        }
        return new String(chars);
    }
}
