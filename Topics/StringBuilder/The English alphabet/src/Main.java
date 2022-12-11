class EnglishAlphabet {

    public static StringBuilder createEnglishAlphabet() {
        // write your code here
        int A = 'A';
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            int f = A + i;
            char c = (char) f;
            s.append(c).append(" ");
        }

        return s.deleteCharAt(s.length() - 1);
    }

}