package jada.books.website;


public class SecretRegex{

    public String makeAwesome(String input) {

        if (input.matches(".{2,}")) {
            if (input.matches("(B).*.(n)")) {
                return "Banankakan";
            } else if (input.matches("(B).*")) {
                return "Banankaka";
            } else if (input.matches("(b).*.(n)")) {
                return "banankakan";
            } else if (input.matches("(b).*")) {
                return "banankaka";
            }
        }

        return input;
    }
}