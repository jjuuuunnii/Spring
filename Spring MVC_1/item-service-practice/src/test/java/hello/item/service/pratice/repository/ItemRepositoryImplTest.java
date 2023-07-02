package hello.item.service.pratice.repository;

import hello.item.service.pratice.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class ItemRepositoryImplTest {

    private final ItemRepositoryImpl itemRepository = new ItemRepositoryImpl();


    @AfterEach
    void afterEach(){
        itemRepository.clear();
    }
    @Test
    void save() {
        //given
        Item item = new Item("kang",2000,20);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        assertThat(item.getId()).isEqualTo(savedItem.getId());
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        Item savedItem1 = itemRepository.save(item1);
        Item savedItem2 = itemRepository.save(item2);

        //when
        List<Item> items = itemRepository.findAll();

        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1,item2);
    }
}