package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Member;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("ItemA", 2000, 1);

        //when
        Item saveItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(saveItem.getId());
        assertThat(saveItem).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        //given
        Item itemA = new Item("ItemA", 1000, 1);
        Item itemB = new Item("ItemB", 2000, 2);

        Item saveItemA = itemRepository.save(itemA);
        Item saveItemB = itemRepository.save(itemB);

        //when
        List<Item> itemList = itemRepository.findAll();

        //then
        assertThat(itemList.size()).isEqualTo(2);
        assertThat(itemList).contains(itemA, itemB);
    }

    @Test
    void update() {
        //given
        Item item = new Item("item1", 10000, 10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        //then
        assertThat(updateParam.getItemName()).isEqualTo(item.getItemName());
        assertThat(updateParam.getPrice()).isEqualTo(item.getPrice());
        assertThat(updateParam.getQuantity()).isEqualTo(item.getQuantity());

    }

}