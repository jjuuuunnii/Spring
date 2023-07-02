package hello.item.service.pratice.repository;

import hello.item.service.pratice.domain.Item;

import java.util.List;

public interface ItemRepository {

    public Item save(Item item);
    public Item findById(Long itemId);
    public List<Item> findAll();
    public void deleteItem(Long itemId);
    Item update(Long itemId, Item updateParam);
}
