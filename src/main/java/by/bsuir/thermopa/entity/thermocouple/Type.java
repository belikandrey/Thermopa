package by.bsuir.thermopa.entity.thermocouple;

public enum Type {
    TMK_M("ТМК-М"),
    TJK_J("ТЖК-J"),
    TPP13_R("ТПП13-R"),
    TPP10_S("ТПП10-S"),
    TPR_B("ТПР-B"),
    TMK_T("ТМК-T"),
    THH_N("THH-N"),
    TXA_K("TXA-K"),
    TXKH_E("TXKн-E"),
    TXK_L("TXK-L"),
    A1_A2_A3("A-1/A-2/A-3");

    private final String label;

    Type(String label) {
        this.label = label;
    }
}
