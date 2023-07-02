package hello.item.service.pratice.repository;

import hello.item.service.pratice.domain.Item;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private static final Map<Long, Item> store = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(),item);
        return item;
    }

    @Override
    public Item findById(Long itemId) {
        return store.get(itemId);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Item update(Long itemId, Item updateParam) {
        Item item = store.get(itemId);
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
        return item;
    }

    @Override
    public void deleteItem(Long itemId) {
        Item item = store.get(itemId);
        store.remove(itemId,item);
    }

    public void clear(){
        store.clear();
    }

}
