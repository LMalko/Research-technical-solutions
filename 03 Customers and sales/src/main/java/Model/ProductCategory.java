package Model;

public class ProductCategory{

        private String name;
        private Integer ID;

        private static Integer nextID = 0;
        private final String defaultExpirationDate = "Unspecified";

        public ProductCategory(){
        }

        public ProductCategory(String name){
                this.name = name;
                this.ID = getNextID();

        }
        public String getName(){
                return name;
        }
        public Integer getID(){
                return ID;
        }
        private Integer getNextID() {
                Integer newID = nextID;
                nextID++;
                return newID;
        }
        public String getExpirationDateString(){
                return this.defaultExpirationDate;
        }
}