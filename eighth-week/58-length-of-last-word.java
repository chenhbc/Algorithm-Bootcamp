class Solution {
    public int lengthOfLastWord(String s) {
        int len = s.length();
        // 从字符串末尾往前遍历
        int index = len - 1;
        char c = s.charAt(index);
        // 去除末尾的空格
        while (c == ' ') {
            index--;
            c = s.charAt(index);
        }
        // 计算单词的长度
        int result = 0;
        while (c != ' ') {
            result++;
            index--;
            if (index < 0) break;
            c = s.charAt(index);
        }
        return result;
    }
}
