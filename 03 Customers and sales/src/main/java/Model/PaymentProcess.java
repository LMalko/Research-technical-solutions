package Model;

import Interface.Orderable;

public class PaymentProcess extends AbstractProcess{

        @Override
        protected void action(Orderable item){
                item.pay();
        }
}