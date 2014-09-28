package array;

public class VariableArray {

    public static void main(String[] args) {

        Array intArr = new Array(new Integer(0), 0);
        System.out.println(intArr.toString());
        for (int i = 1; i < 10; i++) {
            intArr.add(i);
        }
        System.out.println(intArr.toString());
        System.out.println("Result of getElement by index " + (intArr.getArrayLength() - 1) +
                " is: " + intArr.getElementAt(intArr.getArrayLength() - 1));
        System.out.println("Result of array element by index changing operation is: " +
                intArr.change(new Integer(20), 1));
        System.out.println(intArr.toString());
        System.out.println("Array length is: " + intArr.getArrayLength());
        for (int i = 1; i < 23; i++) {
            intArr.delete(0);
        }
        for (int i = 1; i < 10; i++) {
            intArr.add(i);
        }
        System.out.println(intArr.toString()+"\n");

        Array strArr = new Array(new String(""), 10);
        strArr.insert(new String("asdf"), 1);
        System.out.println(strArr.toString());
        strArr.insert(new String("qwer"), 0);
        System.out.println(strArr.toString()+"\n");
    }
}


class Array {

    private static final double RESCALE_RATIO = 2;
    private int arrayLength;
    private int actualArrayLength;
    private Object arrayType;
    private Object[] array;

    public Array(Object arrayType, int arrayLength) {
        this.arrayType = arrayType;
        this.arrayLength = arrayLength;

        actualArrayLength = getNewActualArrayLength();
        array = new Object[actualArrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = arrayType;
        }
    }

    // CRUD
    public boolean insert(Object element, int index) {
        if (isSameObjectType(element) && isIndexInArrayRange(index)) {
            if (arrayLength + 1 > actualArrayLength) {
                changeActualArrayLength(true);
            }
            System.arraycopy(array, index, array, index + 1, arrayLength - index);
            Object[] elementToinsert = new Object[1];
            elementToinsert[0] = element;
            System.arraycopy(elementToinsert, 0, array, index, 1);
            arrayLength++;
            System.out.println("The value \"" + element + "\" has been inserted in array by index: " + index);
            return true;
        }
        return false;
    }

    public void add(Object element) {
        if (isSameObjectType(element)) {
            if (++arrayLength > actualArrayLength) {
                changeActualArrayLength(true);
            }
            array[arrayLength - 1] = element;
            System.out.println("The value \"" + element + "\" has been added in the end of array");
        }
    }

    private void changeActualArrayLength(boolean extendArray) {
        int newActualArrayLength = getNewActualArrayLength();
        Object[] newArray = new Object[newActualArrayLength];
        if (extendArray) {
            System.arraycopy(array, 0, newArray, 0, actualArrayLength);
        } else {
            System.arraycopy(array, 0, newArray, 0, newActualArrayLength);
        }
        actualArrayLength = newActualArrayLength;
        array = newArray;
        System.out.println("Actual length of array has been changed to: " + actualArrayLength);
    }

    private int getNewActualArrayLength() {
        return (int) (RESCALE_RATIO * (double) arrayLength);
    }

    public boolean delete(int index) {
        if (isIndexInArrayRange(index)) {
            System.arraycopy(array, index + 1, array, index, arrayLength - index - 1);
            arrayLength--;
            if (getNewActualArrayLength() < actualArrayLength) {
                changeActualArrayLength(false);
            }
            System.out.println("The element by index: " + index + " was deleted");
            System.out.println("Array length is: " + getArrayLength());
        }
        return true;
    }

    public Object getElementAt(int index) {
        if (isIndexInArrayRange(index)) {
            return array[index];
        }
        return null;
    }

    public boolean change(Object element, int index) {
        if (isSameObjectType(element) && isIndexInArrayRange(index)) {
            System.out.println("The value \"" + array[index] + "\" has been changed by new value \""
                    + element + "\" in index of: " + index);
            array[index] = element;
            return true;
        }
        return false;
    }

    private boolean isIndexInArrayRange(int chekingIndex) {
        return (chekingIndex >= 0 && chekingIndex < arrayLength) ? (true) : (false);
    }

    private boolean isSameObjectType(Object chekingElement) {
        return (chekingElement.getClass().getName() == arrayType.getClass().getName()) ? (true) : (false);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{ ");
        for (int i = 0; i < arrayLength; i++) {
            result.append(" ").append(array[i].toString()).append(",");
        }
        result.deleteCharAt(result.length() - 1).append(" }");
        return result.toString();
    }

    public int getArrayLength() {
        return arrayLength;
    }

    public Object getArrayType() {
        return arrayType;
    }
}