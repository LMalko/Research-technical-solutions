package Model;

import Interface.Orderable;

abstract class AbstractProcess {

        private Orderable item;

        public void process(Orderable item) {
                this.item = item;
                stepBefore();
                action(item);
                stepAfter();
        }
        public void stepBefore(){
                System.out.println("ORDER STATUS WAS --> " + item.toString() + "\n");

        }
        protected abstract void action(Orderable item);

        public void stepAfter(){
                System.out.println("ORDER UPDATE --> " + item.toString());
        }

}