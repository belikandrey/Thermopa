package by.bsuir.thermopa.entity.resistancetermometers;

public enum Type {
    PLATINUM_385("Платиновые ТС, 385"),
    PLATINUM_391("Платиновые ТС, 391"),
    COPPER("Медные ТС"),
    NICKEL("Никелевые ТС");


    private String label;

    Type(String label) {
        this.label = label;
    }
}
