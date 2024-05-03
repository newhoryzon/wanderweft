package com.nhoryzon.mc.wanderweft.registry;

public interface IBaseRegistry<E> {

    E get();

    String getId();

    int getRawId();
    
}
