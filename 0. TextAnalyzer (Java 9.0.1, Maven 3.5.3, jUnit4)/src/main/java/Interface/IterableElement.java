package Interface;

public interface IterableElement<String> {

        String next();
        boolean hasNext();
        void restartIterator();
}
