package eu.mrndesign.matned.servletDemo.shop.controller.allProductsList;

public class Validate {

    static String validateRequest(String name, String price, String description, String category, String quantity) {

        if (name == null || name.isBlank()) return "Invalid product name";
        if (price == null || !isNumeric(price)) return "Invalid price";
        if (description == null || description.isBlank()) return "Invalid description";
        if (category == null) return "Invalid category";
        if (quantity == null || !isNumeric(quantity)) return "Invalid quantity";
        return null;
    }

    private static boolean isNumeric(String price) {
        try {
            int temp = Integer.parseInt(price);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
