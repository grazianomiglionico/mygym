package it.corso.mygym.model.enums;

public enum Type {

    SALA_PESI("SALA_PESI"),
    OPEN("OPEN");

    private String type;

    Type(String type){
        this.type = type;
    }

    @Override
    public String toString() { return type; }

}
