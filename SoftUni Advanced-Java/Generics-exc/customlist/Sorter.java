package customlist;

public class Sorter {

    private Sorter() {

    }

    public static <T extends  Comparable<T>> void sort(CustomList<T> customList) {
        for (int i = 0; i < customList.getSize() - 1; i++) {
            T currentElement = customList.get(i);
            for (int j = 1; j < customList.getSize(); j++) {
                if(currentElement.compareTo(customList.get(j)) > 0) {
                    customList.swap(i, j);
                }
            }
        }
    }
}
