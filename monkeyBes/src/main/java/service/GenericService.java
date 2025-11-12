package service;

public interface GenericService <K,E>{
    void create(E entity);
    E  update(E entity);
    E delete(K entityId);
    E find(K entityId);
}
