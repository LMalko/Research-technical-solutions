package Model;

import Interface.Orderable;

public class Order implements Orderable {

        private int id;
        private String status;
        private Basket basket;
        private Double basketPrice = 0.0;

        private static Integer nextID = 100;

        public Order(Basket basket){
                this.id = getNextID();
                this.status = "NEW";
                this.basket = basket;
                this.getBasketPrice();
        }

        public String getStatus(){
                return this.status;
        }

        @Override
        public boolean checkout(){
                if(this.status.equals("NEW")){
                        this.status = "CHECKED";
                }
                return this.status.equals("CHECKED");
        }

        @Override
        public boolean pay(){
                if(this.status.equals("CHECKED")){
                        this.status = "PAYED";
                }
                return this.status.equals("PAYED");
        }

        private Integer getNextID() {
                Integer newID = nextID;
                nextID++;
                return newID;
        }

        public String toString(){
                return String.format("Order number: %s, the status is - %s, total price is %s",
                        this.id,
                        this.status,
                        this.basketPrice);
        }

        public int getOrderID(){
                return this.id;
        }

        public Basket getOrderBasket(){
                return this.basket;
        }

        public Double getBasketPrice(){
                int i;
                for(i = 0; i < this.basket.getBasketProductList().size(); i++){
                        this.basketPrice += this.basket.getBasketProductList().get(i).getDefaultPrice();
                }
                return this.basketPrice;
        }
}