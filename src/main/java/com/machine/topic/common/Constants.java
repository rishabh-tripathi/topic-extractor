package com.machine.topic.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rishabh.tripathi on 9/25/15.
 */
public class Constants {

    public static class Tables {
        public static final String PROMOTION_TABLE = "promotion_table";
    }

    // Enums
    public enum PromotionType {
        discount("discount", "Discount"),
        cashback("cashback", "Cashback"),
        freebie("freebie", "Freebie");

        private String id;
        private String name;

        PromotionType(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }

    public enum PromotionClass {
        product("product", "Product"),
        category("category", "Category"),
        subcategory("subcategory", "Subcategory"),
        catalog("catalog", "Catalog"),
        cart("cart", "Cart");

        private String id;
        private String name;

        PromotionClass(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }

    public enum PromotionStatus {
        created("created", "Created"),
        scheduled("scheduled", "Scheduled"),
        active("active", "Active"),
        expired("expired", "Expired"),
        deleted("deleted", "Deleted");

        private String id;
        private String name;

        PromotionStatus(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }

    public enum queryOperator {
        and("&&", "AND"),
        or("||", "OR");

        private String id;
        private String name;

        queryOperator(String id, String name) {
            this.id = id;
            this.name = name;
        }
        public String getId() {
            return id;
        }
        public String getName() {
            return name;
        }
    }

}

