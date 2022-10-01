package fr.noconsulting.restcrudapplication.entities;

public enum NameSort {
    AL("alpha"),
    P("priceTTC");

    public final String label;

    private NameSort(String label) {
        this.label = label;
    }

    public static boolean isAlpha(String type) {
        if (type.equals(AL.label)) {
            return true;
        }
        return false;
    }

    public static boolean isPrice(String type) {
        if (type.equals(P.label)) {
            return true;
        }
        return false;
    }

    public static boolean isValueName(String label) {
        for (NameSort e : values()) {
            if (e.label.equals(label)) {
                return true;
            }
        }
        return false;
    }
}

