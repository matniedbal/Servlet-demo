package eu.mrndesign.matned.servletDemo.shop.controller;

import javax.servlet.http.HttpServletRequest;

public class Validate {

    static String validateRequest(HttpServletRequest req) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String description = req.getParameter("description");
        String category = req.getParameter("category");
        String quantity = req.getParameter("quantity");
        if (name == null || name.isBlank()) return "Invalid product name for id: "+id;
        if (price == null || !isNumeric(price)) return "Invalid price for id: "+id;
        if (description == null || description.isBlank()) return "Invalid description for id: "+id;
        if (category == null) return "Invalid category for id: "+id;
        if (quantity == null || !isNumeric(quantity)) return "Invalid quantity for id: "+id;
        return null;
    }
    private static boolean isNumeric(String price) {
        try {
            int temp = Integer.parseInt(price);
            return false;
        } catch (NumberFormatException ex) {
            return true;
        }
    }
}
