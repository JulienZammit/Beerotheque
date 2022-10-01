package fr.noconsulting.restcrudapplication.entities;

public enum TypeSort {
    A("ascending"),
    D("descending");

    public final String label;

    private TypeSort(String label) {
        this.label = label;
    }

    public static boolean isAscending(String type) {
        if (type.equals(A.label)) {
            return true;
        }
        return false;
    }

    public static boolean isDescending(String type) {
        if (type.equals(D.label)) {
            return true;
        }
        return false;
    }

    public static boolean isValueType(String label) {
        for (TypeSort e : values()) {
            if (e.label.equals(label)) {
                return true;
            }
        }
        return false;
    }
}

